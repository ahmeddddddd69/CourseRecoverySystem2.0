package crs.controller;

import crs.model.*;
import crs.util.*;

import java.util.ArrayList;
import java.util.List;

public class AcademicReportController {

    public AcademicReportController() {}

    public boolean generateReport(Student student, String semester, int year) {

        if (student == null) {
            System.out.println("[ERROR] Student is null.");
            return false;
        }

        
        ArrayList<Course> allCourses =
                CourseDataHelper.loadCourses("course_assessment_information.csv");

        
        ArrayList<Grade> allGrades =
                GradeDataHelper.loadGrades("student_course_grades.csv");

        if (allCourses.isEmpty()) {
            System.out.println("[ERROR] No course data found.");
            return false;
        }

        if (allGrades.isEmpty()) {
            System.out.println("[ERROR] No grade data found.");
            return false;
        }

        
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade g : allGrades) {
            if (g.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                studentGrades.add(g);
            }
        }

        
        List<Course> studentCourses = new ArrayList<>();
        for (Grade g : studentGrades) {
            for (Course c : allCourses) {
                if (c.getCourseId().equalsIgnoreCase(g.getCourseId())) {

                    if (!studentCourses.contains(c)) {
                        studentCourses.add(c);
                    }

                }
            }
        }

       
        
        double cgpa = CgpaHelper.calculateCgpa(
                student.getStudentId(),
                new ArrayList<>(studentGrades),     
                new ArrayList<>(studentCourses)     
        );

       
        AcademicReport report = new AcademicReport(
                student,
                semester,
                year,
                studentCourses,
                studentGrades,
                cgpa
        );

        
        PdfGenerator.generateAcademicReport(
                report,
                parseSemesterNumber(semester),
                year
        );

        System.out.println("[SUCCESS] Academic Report generated for " + student.getStudentId());
        return true;
    }

    private int parseSemesterNumber(String raw) {

    if (raw == null) return 1;
    raw = raw.trim();

    if (raw.isEmpty()) return 1;

   
    try {
      
        raw = raw.replaceAll("[^0-9]", "");
        if (raw.isEmpty()) return 1; 
        return Integer.parseInt(raw);
    } catch (Exception e) {
        return 1; 
    }
}
}
