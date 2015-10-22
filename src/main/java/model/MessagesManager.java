package model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import services.HttpService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samjunior on 10/8/15.
 */
public class MessagesManager {
    HttpService service=new HttpService();
    String status;

    public ObservableList<Message> messages= FXCollections.observableArrayList();
    private static MessagesManager ourInstance = new MessagesManager();

    public static MessagesManager getInstance() {
        return ourInstance;
    }

    private MessagesManager() {
    }

    public ObservableList<Message> getMessages(){


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
    public String sendSms(String course, String intake,String title, String message ) {

        String jsonString=service.serviceGet("messages/send/"+course+"/"+intake+"/"+title+"/"+message);


        System.out.println(course+" "+intake+" "+title+" "+message);

//        String response= null;
//        try {
//            response = new JSONObject(jsonString).getString("response");
//            System.out.println(response);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return jsonString;

    }


    public JSONArray getJSONFromUrl(String url) {
        JSONArray jsonArray = null;
        HttpURLConnection httpURLConnection=null;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        String line;
        String jsonString=null;

        try {
            URL u = new URL(url);
            httpURLConnection = (HttpURLConnection) u.openConnection();
            httpURLConnection.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            jsonString = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

}

