package crs.util;

import java.io.*;
import java.util.*;

public class FileHelper {

    // ============================================================
    //  ALWAYS read/write inside the data/ folder
    // ============================================================
    private static String fixPath(String path) {
        return path.startsWith("data/") ? path : "data/" + path;
    }

    // ============================================================
    // 1. READ ALL LINES (for .txt files)
    // ============================================================
    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();

        String fullPath = fixPath(path);

        try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + fullPath + " → " + e.getMessage());
        }

        return lines;
    }

    // ============================================================
    // 2. WRITE A LINE (for .txt files)
    // ============================================================
    public static void writeLine(String path, String data) {

        String fullPath = fixPath(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath, true))) {
            bw.write(data);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error writing file: " + fullPath + " → " + e.getMessage());
        }
    }

    // ============================================================
    // 3. READ CSV (for all .csv datasets)
    // ============================================================
    public static List<String[]> readCSV(String path) {
        List<String[]> rows = new ArrayList<>();

        String fullPath = fixPath(path);

        try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {

                // Skip CSV header row
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                rows.add(line.split(","));
            }

        } catch (Exception e) {
            System.out.println("Error reading CSV: " + fullPath + " → " + e.getMessage());
        }

        return rows;
    }
}

