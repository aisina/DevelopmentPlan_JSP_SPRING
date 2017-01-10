package MVC_PROJECT.controller.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by innopolis on 02.01.2017.
 */
public class GMailAuthenticator extends Authenticator {

    String user;
    String pw;

    public GMailAuthenticator (String username, String password)
    {
        super();
        this.user = username;
        this.pw = password;
    }

    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, pw);
    }
}
