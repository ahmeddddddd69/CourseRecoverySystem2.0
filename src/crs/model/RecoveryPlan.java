package crs.model;

import java.util.ArrayList;

public class RecoveryPlan {

    private String studentId;
    private String courseCode;
    private ArrayList<RecoveryTask> tasks = new ArrayList<>();
    private String status = "Ongoing";

    
    private int attemptCount = 1;

    public RecoveryPlan(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

   
    public void addTask(RecoveryTask t) {
        tasks.add(t);
    }

    public ArrayList<RecoveryTask> getTasks() {
        return tasks;
    }

   
    public void completePlan() {
        status = "Completed";
    }

    public String getStatus() {
        return status;
    }

  
    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    
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
