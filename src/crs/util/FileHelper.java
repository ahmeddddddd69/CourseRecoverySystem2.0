package crs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

public class FileHelper {

    
    public static String fixPath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }

      
        String p = path.replace("\\", "/");

        
        int lastSlash = p.lastIndexOf('/');
        if (lastSlash != -1) {
            p = p.substring(lastSlash + 1);
        }

      
        return "data/" + p;
    }

   
    
    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        String full = fixPath(path);

        try (BufferedReader br = new BufferedReader(new FileReader(full))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + full + " → " + e.getMessage());
        }

        return lines;
    }
}
