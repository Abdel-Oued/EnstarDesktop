package fr.ensta.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fr.ensta.client/mainClient.fxml"));
        root.setStyle("-fx-background-color: aqua");
        Scene scene = new Scene(root,800,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enstar Desktop");
        primaryStage.show();

        primaryStage.setOnCloseRequest((event) -> {
            MainClientController.close(event);
            System.out.println("Fermeture de la session");
        });
    }

}