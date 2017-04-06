package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francoisquellec on 06.04.17.
 */
public class Group {
    List<Person> members = new ArrayList<>();

    public void addMember(Person p){
        members.add(p);
    }

    public List<Person> getMembers(){
        return members;
    }
}
