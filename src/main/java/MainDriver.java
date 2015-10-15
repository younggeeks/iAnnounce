/**
 * Created by samjunior on 10/7/15.
 */

import com.guigarage.material.IconType;
import com.guigarage.material.MaterialDesignPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainDriver extends Application {

    private MaterialDesignPane materialDesignPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root=(BorderPane) FXMLLoader.load(getClass().getClassLoader().getResource("layouts/new.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setTitle("Sms App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
