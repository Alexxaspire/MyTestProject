package SendEmail;

/**
 * Created by Alex on 16.05.2017.
 */
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

public class MailTest {

    private String emailAddressTo;
    private String msgSubject;
    private String msgText;

    final String user_name = "EMAIL_INFO";
    final String pass = "PASS_INFO";
    final String from_info = "EMAIL_INFO";

    public void sendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.emailAddressTo = emailAddressTo;
        this.msgSubject = msgSubject;
        this.msgText = msgText;

        Send();
    }

    public void Send() {
        Properties prop = new Properties();

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user_name, pass);
            }
        });

        try {
            Message message = new MimeMessage(session); //создали объект, который авторизируется по настройкам заданным в session

            // заполняем инфу
            message.setFrom(new InternetAddress(from_info));
            message.setContent(msgText, "text/html");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddressTo));
            message.setSubject(msgSubject);

            Transport.send(message); // отправляет email со всей инфой

            System.out.println("Sent email successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }

    public static void main(String[] args) {
        MailTest email = new MailTest();

        email.sendEmail("EMAIL_INFO", "Test email", "Congratulations !!! \nThis is test email sent by java class.");

    }

}
