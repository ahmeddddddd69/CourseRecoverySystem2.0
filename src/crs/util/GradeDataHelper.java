package crs.util;

import crs.model.Grade;
import java.io.*;
import java.util.ArrayList;

public class GradeDataHelper {

    public static ArrayList<Grade> loadGrades(String filename) {

        ArrayList<Grade> list = new ArrayList<>();

        String full = FileHelper.fixPath(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(full))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                // Your CSV has EXACTLY 4 columns:
                // StudentID, CourseID, GradeLetter, GradePoint
                if (p.length < 4) continue;

                double gp;
                try {
                    gp = Double.parseDouble(p[3].trim());
                } catch (Exception e) {
                    continue;
                }

                // Grade constructor requires 5 parameters
                list.add(new Grade(
                        p[0].trim(),   // studentId
                        p[1].trim(),   // courseId
                        p[2].trim(),   // gradeLetter
                        gp,            // gradePoint
                        ""             // filler value because constructor needs 5 params
                ));
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Cannot read: " + full);
            e.printStackTrace();
        }

        return list;
    }
}


