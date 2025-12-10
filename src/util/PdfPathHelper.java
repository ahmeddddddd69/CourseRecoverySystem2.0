/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;

import java.io.File;

/**
 *
 * @author ejaz
 */
public class PdfPathHelper {
    
    public static String generateReportPath(String studentId, int semester, int year){
        File folder = new File("reports");
        if (!folder.exists()){
            folder.mkdir();
        }
        return "reports/"+ studentId + "_Sem"+ semester+"_"+ year+ ".pdf";
    }
    
}
