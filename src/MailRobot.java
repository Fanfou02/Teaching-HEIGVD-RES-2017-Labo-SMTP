import config.ConfigManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;

/**
 * Created by francoisquellec on 06.04.17.
 */
public class MailRobot {
    public static void main(String... args){
        ConfigManager configs = new ConfigManager();
        PrankGenerator pg = new PrankGenerator(configs);

        SmtpClient smtpClient = new SmtpClient(configs.getSmtpServerAddress(), configs.getSmtpServerPort());

        try {
            for (Prank p : pg.generatePrank())
                smtpClient.sendMail(p.generateMail());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
