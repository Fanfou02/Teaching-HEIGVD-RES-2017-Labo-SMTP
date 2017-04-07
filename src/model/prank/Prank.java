package model.prank;

import model.mail.Mail;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pierre-samuelrochat on 06.04.17.
 */
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

        String [] to = victimRecipients.stream().map(person -> person.getEmail())
                                                .collect(Collectors.toList()).toArray(new String[]{});
        mail.setTo(to);

        String [] cc = witnessRecipients.stream().map(person -> person.getEmail())
                .collect(Collectors.toList()).toArray(new String[]{});
        mail.setTo(cc);

        mail.setFrom(victimSender.getEmail());

        return mail;
    }
}