package sample;

import gateway.AfricasTalkingGateway;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layouts/master.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // launch(args);
//
//        String username="samjunior";
//        String apiKey="73d6810ff78322bf919ab5b3874f7ed34c1e3910ff00a0f16b04e97f4c0e59e8";
//
//       // receipients
//        String receipients="+255714095262";
//
//
//        //message
//        String message="One Two Mic Test! Dogo upo?";
//
//        AfricasTalkingGateway gateway=new AfricasTalkingGateway(username,apiKey);
//
//        try {
//            JSONArray results=gateway.sendMessage(receipients,message);
//
//            for (int i=0;i<results.length();++i){
//                JSONObject result=results.getJSONObject(i);
//                System.out.println(result.getString("status")+",");
//                System.out.println(result.getString("number")+",");
//                System.out.println(result.getString("messageId")+",");
//                System.out.println(result.getString("cost")+",");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//
//    }


    }
}

