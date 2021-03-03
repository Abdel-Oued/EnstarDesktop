package fr.ensta.launchPattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("client.fxml"));
            //final ClientTCP controler = fxmlLoader.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.setStyle("-fx-background-color: aqua");
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client Interface");
        primaryStage.show();
    }
}
