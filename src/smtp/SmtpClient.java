package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String smtpServerAddress;
    private int smtpServerPort = 25;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerAddress, int port){
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = port;
    }

    @Override
    public void sendMail(Mail mail) throws IOException{
        LOG.info("Sending Message ...");
        socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        String line = reader.readLine();
        LOG.info(line);

        writer.printf("EHLO localhost");
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        if(!line.startsWith("250"))
            throw new IOException("SMTP Error : " + line);

        while(line.startsWith("250-")){
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("MAIL FROM:");
        writer.write(mail.getFrom());
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        for (String to : mail.getTo())
            addRCPT(to);

        for (String to : mail.getCc())
            addRCPT(to);

        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        writer.write("Content-Type: text/plain; charset=\"UTF-8\"");

        writer.write("\r\n");
        writer.write("From: " + mail.getFrom());
        writer.write("\r\n");

        writer.write("To: " + mail.getTo()[0]);
        for (int i = 1; i < mail.getTo().length; i++){
            writer.write(", " + mail.getTo()[i]);
        }
        writer.write("\r\n");

        System.out.println("cc : " + mail.getCc().length);
        if(mail.getCc().length > 0) {
            writer.write("Cc: " + mail.getCc()[0]);
            for (int i = 1; i < mail.getCc().length; i++) {
                writer.write(", " + mail.getCc()[i]);
            }
            writer.write("\r\n");

            writer.flush();
        }

        LOG.info(mail.getBody());

        writer.write(mail.getBody());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        writer.write("QUIT\r\n");
        writer.flush();

        reader.close();
        writer.close();
        socket.close();
    }

    private void addRCPT(String to) throws IOException{
        writer.write("RCPT TO:");
        writer.write(to + "\r\n");
        writer.flush();

        LOG.info(reader.readLine());
    }

}
