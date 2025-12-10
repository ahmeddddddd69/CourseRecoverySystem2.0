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

            String key = g.getStudentId() + "-" + g.getCourseId();

            // Prevent duplicate enrollments such as if grades repeat
            if (!uniquePairs.contains(key)) {

                enrollments.add(new Enrollment(
                        g.getStudentId(),
                        g.getCourseId()
                ));

                uniquePairs.add(key);
            }
        }

        return enrollments;
    }
}
