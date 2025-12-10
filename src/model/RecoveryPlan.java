package crs.model;

import java.util.ArrayList;

public class RecoveryPlan {

    private String studentId;
    private String courseCode;
    private ArrayList<RecoveryTask> tasks = new ArrayList<>();
    private String status = "Ongoing";

    // 🔥 NEW: Attempt tracking (starts at 1)
    private int attemptCount = 1;

    public RecoveryPlan(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    // ------------------------
    // Task Management
    // ------------------------
    public void addTask(RecoveryTask t) {
        tasks.add(t);
    }

    public ArrayList<RecoveryTask> getTasks() {
        return tasks;
    }

    // ------------------------
    // Status
    // ------------------------
    public void completePlan() {
        status = "Completed";
    }

    public String getStatus() {
        return status;
    }

    // ⭐ FIX: Add this method so the controller can update status
    public void setStatus(String status) {
        this.status = status;
    }

    // ------------------------
    // Basic Info
    // ------------------------
    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    // ------------------------
    // 🔥 Attempt Logic
    // ------------------------
    public int getAttemptCount() {
        return attemptCount;
    }

    public void incrementAttempt() {
        if (attemptCount < 3) {
            attemptCount++;
        }
    }

    public boolean isFinalAttempt() {
        return attemptCount == 3;
    }
}
