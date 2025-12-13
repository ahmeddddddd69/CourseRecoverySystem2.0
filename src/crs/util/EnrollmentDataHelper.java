package crs.util;

import crs.model.Enrollment;
import crs.model.Grade;
import java.util.ArrayList;
import java.util.HashSet;

public class EnrollmentDataHelper {

    
    public static ArrayList<Enrollment> generateEnrollments(ArrayList<Grade> grades) {

        ArrayList<Enrollment> enrollments = new ArrayList<>();
        HashSet<String> uniquePairs = new HashSet<>();

        for (Grade g : grades) {

           
            String courseCode = g.getCourseCode();
            String studentId = g.getStudentId();

            String key = studentId + "-" + courseCode;

        
            if (!uniquePairs.contains(key)) {

               
                enrollments.add(new Enrollment(
                        studentId,
                        courseCode
                ));

                uniquePairs.add(key);
            }
        }

        return enrollments;
    }
}
