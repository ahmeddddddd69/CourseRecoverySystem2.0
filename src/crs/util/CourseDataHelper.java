package crs.util;

import crs.model.Course;
import java.io.*;
import java.util.ArrayList;

public class CourseDataHelper {

    public static ArrayList<Course> loadCourses(String filename) {

        ArrayList<Course> list = new ArrayList<>();

       
        String full = FileHelper.fixPath(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(full))) {

            String line = br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

             
                if (p.length < 7) continue;

                try {
                    String courseId = p[0].trim();
                    String courseName = p[1].trim();
                    int credits = Integer.parseInt(p[2].trim());
                    String semester = p[3].trim();
                    String instructor = p[4].trim();
                    int examWeight = Integer.parseInt(p[5].trim());
                    int assignmentWeight = Integer.parseInt(p[6].trim());

                    Course c = new Course(
                            courseId,
                            courseName,
                            credits,
                            semester,
                            instructor,
                            examWeight,
                            assignmentWeight
                    );

                    list.add(c);

                } catch (Exception ex) {
                   
                    continue;
                }
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Cannot read: " + full);
            e.printStackTrace();
        }

        return list;
    }
}

