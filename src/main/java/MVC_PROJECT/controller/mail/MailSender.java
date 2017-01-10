package MVC_PROJECT.controller.mail;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * Created by innopolis on 02.01.2017.
 */
public class MailSender {

    private static Logger LOGGER = LoggerFactory.getLogger(MailSender.class);

    private final String MAIL_SUPPORT_PARAM = "mail.SUPPORT";
    private static String message = "Здравствуйте, ...! Вам предоставлен доступ к системе... Логин: пароль: можете изменить";
    private static String subject = "Вам предоставлен доступ к системе ...";

    public static String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public static void sendMail(String messageText, String subject, String sendTo){
        try {
            String SMTP_AUTH_USER = "caramelka2413@gmail.com";
            String SMTP_AUTH_PWD = "lfdktnufhftd";

            Properties props = new Properties();

            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", SMTP_AUTH_USER);
            props.put("mail.smtps.auth", "true");

            //Session session = Session.getDefaultInstance(props);
            Session session = Session.getDefaultInstance(props, new GMailAuthenticator(SMTP_AUTH_USER, SMTP_AUTH_PWD));

            session.setDebug(true);
            Transport transport = session.getTransport(); //с его помощью отправляются сообщения
            transport.connect("smtp.gmail.com", 465, SMTP_AUTH_USER, SMTP_AUTH_PWD);

            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject);
            message.setText(messageText);

            InternetAddress address = new InternetAddress(sendTo);

            message.addRecipient(Message.RecipientType.TO, address); //получатели
            message.setSentDate(new Date());
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

        } catch(MessagingException e){
            LOGGER.info(e.getMessage());
        }
    }

    //public static void main(String[] args) {
        //изначально не было файла log4j.properties
        //ошибки
        /*log4j:WARN No appenders could be found for logger (MailSender).
                log4j:WARN Please initialize the log4j system properly.
                log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.*/

        //sendMail(getMessage(), getSubject(), "aisinakukmor@mail.ru");
    //}

}
