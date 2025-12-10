package crs.util;

import crs.model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseDataHelper {

    public static ArrayList<Course> loadCourses(String path) {

        ArrayList<Course> courses = new ArrayList<>();
        List<String[]> rows = FileHelper.readCSV(path);

        for (String[] r : rows) {
            if (r.length < 7) continue;

            int credits, examWeight, assignmentWeight;

            try {
                credits = Integer.parseInt(r[2].trim());
                examWeight = Integer.parseInt(r[5].trim());
                assignmentWeight = Integer.parseInt(r[6].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            courses.add(new Course(
                    r[0].trim(),   // CourseID
                    r[1].trim(),   // CourseName
                    credits,
                    r[3].trim(),   // Semester
                    r[4].trim(),   // Instructor
                    examWeight,
                    assignmentWeight
            ));
        }

        return courses;
    }
}

