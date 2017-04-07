package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConfigManager implements IConfigManager{

    // Properties
    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private List<Person>witnessToCC = new ArrayList<>();

    // victims and messages to send
    private List<Person> victims = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public ConfigManager(){
        loadVictimsFromFile("config/victims.utf8");
        loadMessagesFromFile("config/messages.utf8");
        loadProperties("config/config.properties");
    }

    public void loadVictimsFromFile(String filename){
        BufferedReader victimsReader = null;
        try {
            victimsReader = new BufferedReader(new FileReader(filename));
            String victim;
            while ((victim = victimsReader.readLine()) != null) {
                victims.add(new Person(victim));
            }
        }catch(IOException e ){
            e.printStackTrace();
        }finally {
            try {
                victimsReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void loadMessagesFromFile(String filename){
// Load messages and victims to prank from respective files
        BufferedReader messagesReader = null;

        try {
            messagesReader = new BufferedReader(new FileReader("config/messages.utf8"));

            // Load prank messages
            String line;
            String message = "";

            while ((line = messagesReader.readLine()) != null){
                if (line.equals("==")) {
                    messages.add(message);
                    message = "";
                } else {
                    message += line + "\r\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                messagesReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadProperties(String filename){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filename));
            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            witnessToCC = new ArrayList<>();
            String witness = prop.getProperty("witnessToCC");
            String [] witnessAdress = witness.split(",");
            for(String email : witnessAdress)
                witnessToCC.add(new Person(email));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNbGroups() {
        return numberOfGroups;
    }

    public List<Person> getWitnessesToCc() {
        return witnessToCC;
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<String> getMessages() {
        return messages;
    }

}

