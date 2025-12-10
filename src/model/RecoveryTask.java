package crs.model;

public class RecoveryTask {

    private int week;              // Week number of recovery plan
    private String description;    // e.g., "Review lectures", "Meet instructor"
    private String status;         // Pending / Completed

    public RecoveryTask(int week, String description) {
        this.week = week;
        this.description = description;
        this.status = "Pending";   // Default status
    }

    // --- Getters & Setters ---
    public int getWeek() { return week; }
    public void setWeek(int week) { this.week = week; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        if (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Completed")) {
            this.status = status;
        }
    }

    // --- Helper Methods ---
    public boolean isCompleted() {
        return status.equalsIgnoreCase("Completed");
    }

    public void markCompleted() {
        this.status = "Completed";
    }

    @Override
    public String toString() {
        return "Week " + week + ": " + description + " [" + status + "]";
    }
}
