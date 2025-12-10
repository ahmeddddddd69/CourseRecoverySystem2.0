/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author ejaz
 */
public class EmailSender {
     public static void sendEmail(String to, String subject, String body) {
        try {
            Properties props = EmailConfigHelper.loadconfig();

            String username = props.getProperty("username");
            String password = props.getProperty("password");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );

            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);

            System.out.println("Email sent successfully to: " + to);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
