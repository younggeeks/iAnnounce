package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Created by samjunior on 10/8/15.
 */
public class Message {
    private StringProperty title;
    private StringProperty body;
    private ObjectProperty<LocalDate> date;
    private StringProperty recepient;
    private StringProperty sender;

    public Message() {
        this(null,null,null);
    }

    public Message(String title, String body,String recepient) {
        this.title = new SimpleStringProperty(title);
        this.body = new SimpleStringProperty(body);
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.recepient = new SimpleStringProperty(recepient);
        this.sender = new SimpleStringProperty("Hassan Karuhanga");
    }


    /**
     * Getter for property 'title'.
     *
     * @return Value for property 'title'.
     */
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Setter for property 'title'.
     *
     * @param title Value to set for property 'title'.
     */
    public void setTitle(String title) {
        this.title.set(title);
    }

    /**
     * Getter for property 'body'.
     *
     * @return Value for property 'body'.
     */
    public String getBody() {
        return body.get();
    }

    public StringProperty bodyProperty() {
        return body;
    }

    /**
     * Setter for property 'body'.
     *
     * @param body Value to set for property 'body'.
     */
    public void setBody(String body) {
        this.body.set(body);
    }

    /**
     * Getter for property 'date'.
     *
     * @return Value for property 'date'.
     */
    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    /**
     * Setter for property 'date'.
     *
     * @param date Value to set for property 'date'.
     */
    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    /**
     * Getter for property 'recepient'.
     *
     * @return Value for property 'recepient'.
     */
    public String getRecepient() {
        return recepient.get();
    }

    public StringProperty recepientProperty() {
        return recepient;
    }

    /**
     * Setter for property 'recepient'.
     *
     * @param recepient Value to set for property 'recepient'.
     */
    public void setRecepient(String recepient) {
        this.recepient.set(recepient);
    }

    /**
     * Getter for property 'sender'.
     *
     * @return Value for property 'sender'.
     */
    public String getSender() {
        return sender.get();
    }

    public StringProperty senderProperty() {
        return sender;
    }

    /**
     * Setter for property 'sender'.
     *
     * @param sender Value to set for property 'sender'.
     */
    public void setSender(String sender) {
        this.sender.set(sender);
    }
}
