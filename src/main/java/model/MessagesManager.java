package model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import services.HttpService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        HttpService service=new HttpService();

        JSONArray arr;

        String jsonString=service.serviceGet("messages/all");


        if (jsonString!=null){

            try {
                JSONObject jsonObject=new JSONObject(jsonString);
                arr=jsonObject.getJSONArray("messages");
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                JSONObject userArray=new JSONObject();

                for (int i=0;i<arr.length();i++){

                    JSONObject c=arr.getJSONObject(i);
                    userArray=c.getJSONObject("user");
                    Message message=new Message();
                    message.setBody(c.getString("body"));
                    message.setRecepient(c.getString("recepient"));
                    message.setTitle(c.getString("title"));
                    message.setDate(LocalDate.parse(c.getString("date")));
                    message.setSender(userArray.getString("name"));
                    messages.add(message);
                }
                //arr=jsonObject.getJSONArray();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Platform.runLater(() -> {
               System.out.println("adgdfgdfgddfgsdfgddfgdf");
            });
        }
        return messages;
    }

}
