package crs.controller;

import crs.model.RecoveryPlan;
import crs.model.RecoveryTask;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class RecoveryPlanController {

    private final String FILE_NAME = "recovery_plans.txt";
    private List<RecoveryPlan> recoveryPlans = new ArrayList<>();

    public RecoveryPlanController() {
        loadFromFile();
    }

    // ========================================================
    // CREATE RECOVERY PLAN (ATTEMPT STARTS AT 1)
    // ========================================================
    public boolean createRecoveryPlan(String studentId, String courseCode) {

        // Prevent duplicates
        RecoveryPlan existing = getRecoveryPlan(studentId, courseCode);
        if (existing != null) return false;

        RecoveryPlan plan = new RecoveryPlan(studentId, courseCode);
        recoveryPlans.add(plan);
        saveToFile();
        return true;
    }

    // ========================================================
    // GET ALL PLANS FOR A STUDENT
    // ========================================================
    public List<RecoveryPlan> getRecoveryPlansByStudent(String studentId) {
        List<RecoveryPlan> result = new ArrayList<>();
        for (RecoveryPlan plan : recoveryPlans) {
            if (plan.getStudentId().equals(studentId)) {
                result.add(plan);
            }
        }
        return result;
    }

    // ========================================================
    // UPDATE PLAN STATUS
    // ========================================================
    public boolean updatePlanStatus(String studentId, String courseCode, String status) {
        RecoveryPlan plan = getRecoveryPlan(studentId, courseCode);
        if (plan != null) {
            if (status.equalsIgnoreCase("Completed"))
                plan.completePlan();
            else
                plan.setStatus(status);

            saveToFile();
            return true;
        }
        return false;
    }

    // ========================================================
    // ADD TASK TO A PLAN
    // ========================================================
    public boolean addTask(String studentId, String courseCode, int week, String description) {
        RecoveryPlan plan = getRecoveryPlan(studentId, courseCode);
        if (plan == null) return false;

        plan.addTask(new RecoveryTask(week, description));
        saveToFile();
        return true;
    }

    // ========================================================
    // INCREMENT ATTEMPT (MAX 3)
    // ========================================================
    public boolean incrementPlanAttempt(String studentId, String courseCode) {
        RecoveryPlan plan = getRecoveryPlan(studentId, courseCode);
        if (plan == null) return false;

        plan.incrementAttempt();
        saveToFile();
        return true;
    }

    // ========================================================
    // GET PLAN
    // ========================================================
    private RecoveryPlan getRecoveryPlan(String studentId, String courseCode) {
        for (RecoveryPlan plan : recoveryPlans) {
            if (plan.getStudentId().equals(studentId) &&
                plan.getCourseCode().equals(courseCode)) {
                return plan;
            }
        }
        return null;
    }
        // ========================================================
    // REMOVE TASK FROM A PLAN
    // ========================================================
    public boolean removeTask(String studentId, String courseCode, int week) {
        RecoveryPlan plan = getRecoveryPlan(studentId, courseCode);
        if (plan == null) return false;

        List<RecoveryTask> tasks = plan.getTasks();
        RecoveryTask toRemove = null;

        for (RecoveryTask t : tasks) {
            if (t.getWeek() == week) {
                toRemove = t;
                break;
            }
        }

        if (toRemove != null) {
            tasks.remove(toRemove);
            saveToFile();   // persist changes
            return true;
        }

        return false;
    }


    // ========================================================
    // SAVE FULL PLAN DATA
    // (FORMAT: studentId,courseCode,status,attempt,taskCount,task1Week|task1Desc|task1Status;task2... )
    // ========================================================
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (RecoveryPlan plan : recoveryPlans) {

                StringBuilder tasksData = new StringBuilder();
                for (RecoveryTask t : plan.getTasks()) {
                    tasksData.append(t.getWeek()).append("|")
                             .append(t.getDescription()).append("|")
                             .append(t.getStatus()).append(";");
                }

                String line = plan.getStudentId() + "," +
                              plan.getCourseCode() + "," +
                              plan.getStatus() + "," +
                              plan.getAttemptCount() + "," +
                              tasksData;

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving recovery plans: " + e.getMessage());
        }
    }

    // ========================================================
    // LOAD FULL PLAN DATA BACK INTO OBJECTS
    // ========================================================
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",", 5);

                if (p.length >= 4) {
                    RecoveryPlan plan = new RecoveryPlan(p[0], p[1]);
                    plan.setStatus(p[2]);
                    plan.incrementAttempt();  // attempt starts at 1, so set proper count
                    int attempt = Integer.parseInt(p[3]);
                    while (plan.getAttemptCount() < attempt)
                        plan.incrementAttempt();

                    // Load tasks
                    if (p.length == 5 && !p[4].isEmpty()) {
                        String[] tasksArr = p[4].split(";");
                        for (String t : tasksArr) {
                            if (t.trim().isEmpty()) continue;

                            String[] taskParts = t.split("\\|");
                            int week = Integer.parseInt(taskParts[0]);
                            String desc = taskParts[1];
                            String status = taskParts[2];

                            RecoveryTask task = new RecoveryTask(week, desc);
                            task.setStatus(status);
                            plan.addTask(task);
                        }
                    }

                    recoveryPlans.add(plan);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading recovery plans: " + e.getMessage());
        }
    }
}
