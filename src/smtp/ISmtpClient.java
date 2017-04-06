package smtp;

import model.mail.Mail;

import java.io.IOException;

/**
 * Created by francoisquellec on 06.04.17.
 */
public interface ISmtpClient {
    void sendMail(Mail mail) throws IOException;
}
