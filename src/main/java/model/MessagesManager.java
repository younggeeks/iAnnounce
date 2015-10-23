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
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    public String sendSms(String course, String intake,String title, String message ) throws Exception {
        JSONArray students;

        String jsonString=service.serviceGet("messages/send/"+course+"/"+intake);

        if (jsonString!=null){
            try {
                JSONObject jsonObject=new JSONObject(jsonString);
                students=jsonObject.getJSONArray("students");

                for (int i=0;i<students.length();i++){
                    JSONObject object=students.getJSONObject(i);
                    String ujumbe="Hello "+object.getString("first_name")+", "+message;
                    sendGet(object.getString("phone"),ujumbe);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonString;

    }

    // HTTP GET request
    private void sendGet(String number,String message) throws Exception {

        // Required Information
        String url = "http://www.bongolive.co.tz/api/sendSMS.php?";
        String sendername = "255714095262";
        String username = "samjunior";
        String password = "Codeigniter1";
        String apikey = "8907e9aa-73df-11e5-bb59-00185176683e";
        String destnum = number; // In thi format: 255XXXXXXXXX

        // --------------------start of >> DO NOT EDIT THIS PART ----------------------------
        // Optional Requirements
        String senddate = "";
        String proxy_ip = "";
        String proxy_port = "";

        // Date is optional parameter, to reduce issues do not include it as a parameter
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        senddate = dateFormat.format(date);

        // Convert Text message to ASCII encoding standard
        message = URLEncoder.encode(message, "ASCII");
        message=message.replaceAll("[+]", "%20");

        // Setting up the URl parameters
        url = url + "sendername="+sendername+"&username="+username+"&password="+password+"&apikey="+apikey+"&destnum="+destnum+"&message="+message;


        System.out.println(url);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent","MOZILA");

        int responseCode = con.getResponseCode();
        // System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : \n" + responseCode+"\n\nAPI Response : ");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        // --------------------end of >> DO NOT EDIT THIS PART ----------------------------


        //print result
        System.out.println(response.toString());

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

