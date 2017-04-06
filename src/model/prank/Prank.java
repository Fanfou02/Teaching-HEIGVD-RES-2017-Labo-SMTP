package model.prank;

import model.mail.Mail;
import model.mail.Person;

import java.util.List;

/**
 * Created by pierre-samuelrochat on 06.04.17.
 */
public class Prank {

    private Person from;
    private List<Person> to;
    private Person cC;
    private Mail mail;

    public Prank(Person sender, List<Person> receivers, Person witness, Mail message){
        this.from = sender;
        this.to = receivers;
        this.cC = witness;
        this.mail = message;
    }

    public Person getSender() {
        return from;
    }

    public List<Person> getReceivers() {
        return to;
    }

    public Person getWitness() {
        return cC;
    }

    public Mail getMessage() {
        return mail;
    }
}