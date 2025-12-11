package crs.util;

import crs.model.Course;
import crs.model.Grade;
import java.util.ArrayList;

public class CgpaHelper {

    /**
     * Calculates CGPA for a given student.
     *
     * @param studentId ID of the student
     * @param grades    All loaded grade records
     * @param courses   All loaded course records
     * @return CGPA value
     */
    public static double calculateCgpa(String studentId,
                                       ArrayList<Grade> grades,
                                       ArrayList<Course> courses) {

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade g : grades) {

            // Only calculate for this student
            if (!g.getStudentId().equals(studentId))
                continue;

            // Grade.java uses COURSE CODE, not courseId
            String courseCode = g.getCourseCode();  // FIXED

            // Match course by courseCode
            Course course = findCourse(courses, courseCode);
            if (course == null)
                continue;

            int credits = course.getCredits();
            double gradePoint = g.getGradePoint();

            // Ignore invalid grade points
            if (gradePoint < 0)
                continue;

            totalPoints += gradePoint * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0)
            return 0.0;

        return totalPoints / totalCredits;
    }

    /**
     * Finds a course by courseCode (NOT courseId)
     */
    private static Course findCourse(ArrayList<Course> courses, String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {  // FIXED
                return c;
            }
        }
        return null;
    }
}
