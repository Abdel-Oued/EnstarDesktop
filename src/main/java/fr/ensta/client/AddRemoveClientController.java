package fr.ensta.client;

import fr.ensta.serveurMessagerie.IMessagerie;
import fr.ensta.serveurMessagerie.Messagerie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Controller principal de la section En ligne.
 * */
public class AddRemoveClientController {
    public Button btnShow;
    public Button btnAdd;
    public Button btnRemove;
    public Button buttonUpdate2;
    public ListView utilisateursConnectes2;
    @FXML private ListView lstUsers;
    @FXML private AnchorPane Users;
    @FXML private ListView lstAddUsers;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private MainClientController mainController;
    private String rep;

    /**
     * Permet de recuperer le controller principal mainClientController
     * @param mainClientController
     * */
    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }


    @FXML
    private void updateAllUsers() {
        lstUsers.getItems().clear();
        rep = mainController.getUser().getMonClientTCP().transmettreChaine("tousUtilisateurs");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> allUsers = mainController.getUser().getAllUsers();

        for (String user : allUsers) {
            lstUsers.getItems().add(user);
        }
    }


    @FXML
    private void updateConnectedUsers() {
        utilisateursConnectes2.getItems().clear();
        rep = mainController.getUser().getMonClientTCP().transmettreChaine("utilisateursConnectes");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> connectedUsers = mainController.getUser().getConnectedUsers();

        for (String user : connectedUsers) {
            utilisateursConnectes2.getItems().add(user);
        }

    }

    @FXML
    private void AddUsers() {
        String username=txtUsername.getText();
        String password=txtPassword.getText();

        mainController.getUser().getMonClientTCP().transmettreChaine("ajoutUtilisateur#"+username+"#"+password);


    }
    @FXML
    private void RemoveUsers() {
        String username=txtUsername.getText();
        String password=txtPassword.getText();

        mainController.getUser().getMonClientTCP().transmettreChaine("suppressionUtilisateur#"+username+"#"+password);

    }

}

