/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;

import java.io.FileInputStream;
import java.util.Properties;
        

/**
 *
 * @author ejaz
 */
public class EmailConfigHelper {
    public static Properties loadconfig(){
        Properties p = new Properties();
        try{
            p.load(new FileInputStream("config/email.properties"));
        }catch(Exception e){
            System.out.println("Error Loading email settings!");
        }
        return p;
    }
    
}
