package crs.util;

import crs.model.Course;
import crs.model.Grade;
import java.util.ArrayList;

public class CgpaHelper {

    /**
     * CGPA calculation 
     * @param studentId 
     * @param courses   
     * @param grades    
     
     */
    public static double calculateCgpa(String studentId,
                                       ArrayList<Grade> grades,
                                       ArrayList<Course> courses) {

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade g : grades) {

       
            if (!g.getStudentId().equals(studentId))
                continue;

            
            String courseCode = g.getCourseCode();  // FIXED

      
            Course course = findCourse(courses, courseCode);
            if (course == null)
                continue;

            int credits = course.getCredits();
            double gradePoint = g.getGradePoint();

            
            if (gradePoint < 0)
                continue;

            totalPoints += gradePoint * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0)
            return 0.0;

        return totalPoints / totalCredits;
    }

    
    private static Course findCourse(ArrayList<Course> courses, String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {  
                return c;
            }
        }
        return null;
    }
}
