package crs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


public class EmailConfigHelper {

    public static Properties loadconfig() {
        Properties p = new Properties();

        try {
           
            FileInputStream fis = new FileInputStream("config/email.properties");
            p.load(fis);
            System.out.println("✔ Email configuration loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("❌ ERROR: email.properties file NOT FOUND at: config/email.properties");
            System.out.println("Make sure the folder looks like: /config/email.properties");
        } catch (Exception e) {
            System.out.println("❌ ERROR: Failed to load email configuration.");
            e.printStackTrace();
        }

        return p;
    }
}
