package crs.util;

import crs.model.Enrollment;
import crs.model.Grade;
import java.util.ArrayList;
import java.util.HashSet;

public class EnrollmentDataHelper {

    /**
     * Generates enrollment records automatically from the grades list.
     * If a student has a grade for a course, they are considered enrolled.
     */
    public static ArrayList<Enrollment> generateEnrollments(ArrayList<Grade> grades) {

        ArrayList<Enrollment> enrollments = new ArrayList<>();
        HashSet<String> uniquePairs = new HashSet<>();

        for (Grade g : grades) {

            // FIXED: Grade uses courseCode, not courseId
            String courseCode = g.getCourseCode();
            String studentId = g.getStudentId();

            String key = studentId + "-" + courseCode;

            // Prevent duplicate enrollments such as if grades repeat
            if (!uniquePairs.contains(key)) {

                // Assuming Enrollment has a constructor (studentId, courseCode)
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
