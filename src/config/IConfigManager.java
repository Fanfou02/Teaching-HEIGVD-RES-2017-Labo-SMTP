package config;

import model.mail.Person;

import java.util.List;

public interface IConfigManager {
    List<String> getMessages();
    List<Person> getVictims();
    List<Person> getWitnessesToCc();
    int getNbGroups();
}
