package lk.jiat.ee.mail;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;

import java.util.Base64;

public class VerificationMail extends Mailable{

    private String to;
    private String password;

    public VerificationMail(String to, String password) {
        this.to = to;
        this.password = password;
    }

    @Override
    public void build(Message message) throws Exception {

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Verification Mail");

        String link ="http://localhost:8080/Banking-System/index.jsp";

        String html = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <style>" +
                "        body { font-family: 'Segoe UI', sans-serif; background-color: #f0f8ff; margin: 0; padding: 0; }" +
                "        .container { background-color: #ffffff; max-width: 600px; margin: 30px auto; border-radius: 10px;" +
                "                     box-shadow: 0 0 10px rgba(0,0,0,0.1); overflow: hidden; }" +
                "        .header { background-color: #007BFF; color: white; padding: 20px; text-align: center; }" +
                "        .content { padding: 30px; color: #333333; }" +
                "        .content p { font-size: 16px; }" +
                "        .button { display: inline-block; padding: 12px 25px; margin-top: 20px; background-color: #007BFF;" +
                "                  color: white; text-decoration: none; border-radius: 5px; font-weight: bold; }" +
                "        .footer { text-align: center; padding: 15px; font-size: 13px; color: #999999; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='container'>" +
                "        <div class='header'>" +
                "            <h2>Welcome to Our Platform</h2>" +
                "        </div>" +
                "        <div class='content'>" +
                "            <p>Hello <strong>User</strong>,</p>" +
                "            <p>Thank you for registering with us. Below are your login credentials:</p>" +
                "            <p><strong>Email:</strong> "+to+"</p>" +
                "            <p><strong>Password:</strong> "+password+"</p>" +
                "            <p>To verify your account, please click the button below:</p>" +
                "            <a class='button' href='" + link + "'>Login to Account</a>" +
                "        </div>" +
                "        <div class='footer'>" +
                "            &copy; 2025 Your Company. All rights reserved." +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";

        message.setContent(html, "text/html; charset=utf-8");

//        message.setContent(html, "text/html; charset=utf-8");

    }
}
