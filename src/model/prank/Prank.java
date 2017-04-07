package model.prank;

import model.mail.Mail;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {

    private Person victimSender;
    private List<Person> victimRecipients = new ArrayList<>();
    private List<Person> witnessRecipients = new ArrayList<>();
    private String message;

    public void setVictimSender(Person victimSender){
        this.victimSender = victimSender;
    }

    public Person getVictimSender() {
        return victimSender;
    }

    public void addVictimRecipients(List<Person> victims){
        victimRecipients = victims;
    }

    public List<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public void addWitnessRecipients(List <Person> witness){
        witnessRecipients = witness;
    }

    public List<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mail generateMail(){
        Mail mail = new Mail();
        mail.setBody(this.message + "\r\n" + victimSender.getFirstName());

        String [] to = new String[victimRecipients.size()];
        for (int i = 0; i < victimRecipients.size(); i++) {
            to[i] = victimRecipients.get(i).getEmail();
            System.out.println("to :" +  to[i]);
        }
        mail.setTo(to);

        String [] cc = new String[witnessRecipients.size()];
        for (int i = 0; i < witnessRecipients.size(); i++) {
            cc[i] = witnessRecipients.get(i).getEmail();
            System.out.println("cc :" +  cc[i]);
        }
        mail.setCc(cc);

        mail.setFrom(victimSender.getEmail());

        System.out.println("nbTo : " + mail.getTo().length);

        System.out.println("genrate Mail : ");
        System.out.println("From : " + mail.getFrom());
        System.out.print("To : ");
        for(String p : mail.getTo())
            System.out.print(p);
        System.out.print("\nCc : ");
        for(String p : mail.getCc())
            System.out.print(p);
        System.out.println("\nBody: " + mail.getBody());

        return mail;
    }
}