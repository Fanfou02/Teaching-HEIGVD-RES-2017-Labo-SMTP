package config;

import model.mail.Mail;
import model.mail.Person;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


/**
 * Created by pierre-samuelrochat on 06.04.17.
 */
public class ConfigManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private Person witnessToCC;
    private List<Person> victims = new LinkedList<Person>();
    private List<Mail> messages = new LinkedList<Mail>();

    public ConfigManager(){

        // Load properties from config.properties file
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config/config.properties"));
            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            witnessToCC = new Person(prop.getProperty("witnessToCC"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load messages and victims to prank from respective files
        BufferedReader victimsReader = null;
        BufferedReader messagesReader = null;

        try {
            victimsReader = new BufferedReader(new FileReader("config/victims.utf8"));
            messagesReader = new BufferedReader(new FileReader("config/messages.utf8"));

            // Load victims
            String victim;
            while ((victim = victimsReader.readLine()) != null){
                victims.add(new Person(victim));
            }

            // Load prank messages
            String line;
            String subject = "";
            String message = "";
            boolean isFirstLine = true;

            while ((line = messagesReader.readLine()) != null){
                if (line.equals("==")) {
                    messages.add(new Mail(subject, message));
                    isFirstLine = true;
                    subject = "";
                    message = "";
                } else {
                    if (isFirstLine) {
                        subject = line.substring(8);
                        isFirstLine = false;
                    } else {
                        message += "\n" + line;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                messagesReader.close();
                victimsReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public Person getWitnessToCC() {
        return witnessToCC;
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<Mail> getMessages() {
        return messages;
    }
}

