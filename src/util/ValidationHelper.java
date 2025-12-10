/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;

/**
 *
 * @author ejaz
 */
public class ValidationHelper {
    public static boolean isEmpty(String text){
        return text== null || text.trim().isEmpty();
    }
    
    public static boolean  isValidEmail(String email){
        return email.contains("@") && email.contains(".");
    }
    public static boolean isValidId(String id) {
        return id.matches("[0-9A-Za-z]+");
    }
}
