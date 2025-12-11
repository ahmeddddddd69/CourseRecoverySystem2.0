package crs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

public class FileHelper {

    /**
     * Always resolve a path to the top-level "data" folder.
     *
     * No matter what you pass:
     *  - "student_course_grades.csv"
     *  - "src/data/student_course_grades.csv"
     *  - "data/student_course_grades.csv"
     *  - "data/src/data/student_course_grades.csv"
     *
     * It will ALWAYS become:
     *  - "data/student_course_grades.csv"
     */
    public static String fixPath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }

        // Normalise slashes
        String p = path.replace("\\", "/");

        // Keep ONLY the filename (strip all folders)
        int lastSlash = p.lastIndexOf('/');
        if (lastSlash != -1) {
            p = p.substring(lastSlash + 1);
        }

        // Final path: top-level data folder
        return "data/" + p;
    }

    /**
     * Optional helper if you want to read all lines from a file.
     */
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
