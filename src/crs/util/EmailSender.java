package crs.util;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    // Simple email without attachment (keep if used elsewhere)
    public static void sendEmail(String to, String subject, String body) {
        try {
            Properties props = EmailConfigHelper.loadconfig();
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
            System.out.println("Email sent successfully to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Email WITH attachment
    public static void sendEmailWithAttachment(String to,
                                               String subject,
                                               String body,
                                               String attachmentPath) throws Exception {

        System.out.println("✔ Email configuration loaded successfully.");

        Properties props = EmailConfigHelper.loadconfig();
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        if (username == null || password == null) {
            throw new Exception("Email username/password not found in config.");
        }

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // ✅ Use the path EXACTLY as passed in (e.g. "reports/S001_Sem1_2.pdf")
        System.out.println("EmailSender: attachmentPath = " + attachmentPath);
        File file = new File(attachmentPath);
        System.out.println("EmailSender: absolute = " + file.getAbsolutePath());

        if (!file.exists()) {
            throw new Exception("Attachment file not found: " + file.getAbsolutePath());
        }

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        // Text part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);

        // Attachment part
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.setDataHandler(new DataHandler(new FileDataSource(file)));
        attachmentPart.setFileName(file.getName());

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("📧 Email with attachment sent to: " + to);
    }
}
