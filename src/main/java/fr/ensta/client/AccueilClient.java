package fr.ensta.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class AccueilClient extends Application {

    @FXML private Button connexion;
    @FXML private TextField username;
    @FXML private PasswordField password;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fr.ensta.client/accueilClient.fxml"));



        root.setStyle("-fx-background-color: aqua");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enstar Desktop");
        primaryStage.show();
    }


    @FXML
    private void connexion(ActionEvent actionEvent) {
        User myUser = new User(username.getText(), password.getText(), 6666);

        if ( myUser.connexionServeur() ) {
            Parent root = null;
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.ensta.client/mainClient.fxml"));
                root = loader.load();
                MainClientController mainClientController = loader.getController();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            root.setStyle("-fx-background-color: aqua");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Enstar Desktop");
            stage.show();
        }
    }
}
