package crs.model;

import java.util.List;

public class AcademicReport {

    private Student student;          // Student information
    private String semester;          // Semester (e.g., "Semester 1")
    private int year;                 // Academic year
    private List<Course> courses;     // Courses taken in this semester
    private List<Grade> grades;       // Grades for those courses

    private double cgpa;              // Cumulative GPA

    // ---------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------
    public AcademicReport(Student student, String semester, int year,
                          List<Course> courses, List<Grade> grades, double cgpa) {

        this.student = student;
        this.semester = semester;
        this.year = year;
        this.courses = courses;
        this.grades = grades;
        this.cgpa = cgpa;
    }

    // ---------------------------------------------------------
    // Getters
    // ---------------------------------------------------------
    public Student getStudent() {
        return student;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
