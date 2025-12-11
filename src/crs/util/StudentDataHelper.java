package crs.util;

import crs.model.Student;
import java.io.*;
import java.util.ArrayList;

public class StudentDataHelper {

    public static ArrayList<Student> loadStudents(String filename) {

        ArrayList<Student> list = new ArrayList<>();

        // Always resolve path using FileHelper so it works on Mac/Windows
        String full = FileHelper.fixPath(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(full))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                // Your CSV has 6 columns:
                // studentId, firstName, lastName, major, year, email
                if (p.length < 6) continue;

                Student s = new Student(
                        p[0].trim(), // studentId
                        p[1].trim(), // firstName
                        p[2].trim(), // lastName
                        p[3].trim(), // major
                        p[4].trim(), // year
                        p[5].trim()  // email
                );

                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Cannot read: " + full);
            e.printStackTrace();
        }

        return list;
    }
}
