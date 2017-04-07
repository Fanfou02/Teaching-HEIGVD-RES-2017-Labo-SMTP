package config;

import model.mail.Person;

import java.util.List;

/**
 * Created by francoisquellec on 07.04.17.
 */
public interface IConfigManager {
    List<String> getMessages();
    List<Person> getVictims();
    List<Person> getWitnessesToCc();
    int getNbGroups();
}
