package crs.util;

import crs.model.Grade;
import java.util.ArrayList;
import java.util.List;

public class GradeDataHelper {

    public static ArrayList<Grade> loadGrades(String path) {

        ArrayList<Grade> grades = new ArrayList<>();
        List<String[]> rows = FileHelper.readCSV(path);

        for (String[] r : rows) {
            if (r.length < 5) continue;

            double gradePoint;
            try {
                gradePoint = Double.parseDouble(r[3].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            grades.add(new Grade(
                    r[0].trim(),  // StudentID
                    r[1].trim(),  // CourseID
                    r[2].trim(),  // GradeLetter
                    gradePoint,   // GradePoint
                    r[4].trim()   // Semester
            ));
        }

        return grades;
    }
}

