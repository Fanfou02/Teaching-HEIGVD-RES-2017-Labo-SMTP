package model.prank;

import config.IConfigManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by francoisquellec on 06.04.17.
 */
public class PrankGenerator {
    private IConfigManager configManager;
    public PrankGenerator(IConfigManager configManager){
        this.configManager = configManager;
    }
    public List<Prank> generatePrank(){
        List<Prank> pranks = new ArrayList<>();
        List<String> messages = configManager.getMessages();
        int msgIndex = 0;
        int nbGroups = configManager.getNbGroups();
        int nbVictims = configManager.getVictims().size();

        if(nbVictims/nbGroups < 3){
            nbGroups = nbVictims/3;
        }

        List<Group> groups = generateGroups(configManager.getVictims(), nbGroups);
        for(Group group : groups){
            Prank prank = new Prank();
            List<Person> victims = group.getMembers();

            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            prank.setVictimSender(sender);
            prank.addVictimRecipients(victims);

            prank.addWitnessRecipients(configManager.getWitnessesToCc());

            String message = messages.get(msgIndex);
            msgIndex = (msgIndex + 1) % messages.size();
            prank.setMessage(message);

            pranks.add(prank);
        }

        return pranks;
    }

    public List<Group> generateGroups(List<Person> victims, int nbGroups){
        List<Person> availableVictims = new ArrayList<>(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();

        for(int i = 0; i<nbGroups; i++){
            Group group = new Group();
            groups.add(group);
        }

        int turn = 0;

        Group targetGroup;

        while(!availableVictims.isEmpty()){
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = availableVictims.remove(0);
            targetGroup.addMember(victim);
        }
        return groups;
    }
}
