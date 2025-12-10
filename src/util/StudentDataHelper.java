/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;

import crs.model.Student;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author ejaz
 */
public class StudentDataHelper {

    public static ArrayList<Student> loadStudents(String fileName) {
        ArrayList<Student> students = new ArrayList<>();

        File file = new File("data/" + fileName);
        if (!file.exists()) {
            System.out.println("[ERROR] Student file not found: " + file.getAbsolutePath());
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                if (p.length < 6) {
                    System.out.println("[WARN] Invalid student row: " + line);
                    continue;
                }

                String id        = p[0].trim();
                String firstName = p[1].trim();
                String lastName  = p[2].trim();
                String major     = p[3].trim();
                String year      = p[4].trim();
                String email     = p[5].trim();

                Student s = new Student(id, firstName, lastName, major, year, email);

                students.add(s);
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to read students: " + e.getMessage());
        }

        return students;
    }
}