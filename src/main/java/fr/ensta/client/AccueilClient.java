package fr.ensta.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class AccueilClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fr.ensta.client/mainClient.fxml"));
        //root.setStyle("-fx-background-image: url(Logo.png)");
        Scene scene = new Scene(root,800,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enstar Desktop");
        //primaryStage.getIcons().add(new Image("src/main/resources/Icon/ENSTA_LOGO.png"));
        primaryStage.show();

        //scene.getStylesheets().add(getClass().getResource("/java/resources/fr.ensta.client/welcomePage.css").toExternalForm());

        primaryStage.setOnCloseRequest((event) -> {
            MainClientController.close(event);
            System.out.println("Fermeture de la session");
        });
    }

}