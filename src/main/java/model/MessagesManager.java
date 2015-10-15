package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samjunior on 10/8/15.
 */
public class MessagesManager {

    public ObservableList<Message> messages= FXCollections.observableArrayList();
    private static MessagesManager ourInstance = new MessagesManager();

    public static MessagesManager getInstance() {
        return ourInstance;
    }

    private MessagesManager() {
    }

    public ObservableList<Message> getMessages(){
        messages.add(new Message("Supplementary","Special Exams for Finalist is to be done in 12-23-2009","All SHS Students"));
        messages.add(new Message("New Students Registration","All New Students should report to Their Respective Departments","New Students"));
        messages.add(new Message("Graduation Clearance","Graduation Clearance Forms are Now Available In Our Website","Graduands"));
        messages.add(new Message("Retakes","Retake exams are cancelled please wait for the new Info","Retakes"));
        return messages;
    }

}
