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

             
                if (p.length < 4) continue;

                double gp;
                try {
                    gp = Double.parseDouble(p[3].trim());
                } catch (Exception e) {
                    continue;
                }

               
                list.add(new Grade(
                        p[0].trim(),  
                        p[1].trim(),   
                        p[2].trim(),  
                        gp,           
                        ""             
                ));
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Cannot read: " + full);
            e.printStackTrace();
        }

        return list;
    }
}


