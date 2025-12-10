package crs.util;

import crs.model.Course;
import crs.model.Grade;
import java.util.ArrayList;

public class CgpaHelper {

    
    public static double calculateCgpa(String studentId,
                                       ArrayList<Grade> grades,
                                       ArrayList<Course> courses) {

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade g : grades) {

            // Only calculate CGPA for this specific student
            if (!g.getStudentId().equals(studentId)) continue;

            // Find matching course to get credits
            Course course = findCourse(courses, g.getCourseId());
            if (course == null) continue;

            int credits = course.getCredits();
            double points = g.getGradePoint();

            totalPoints += points * credits;   // Weighted grade
            totalCredits += credits;
        }

        if (totalCredits == 0) return 0.0;

        return totalPoints / totalCredits;
    }


    /**
     * Helper: Find course by ID
     */
    private static Course findCourse(ArrayList<Course> courses, String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                return c;
            }
        }
        return null;
    }
}
