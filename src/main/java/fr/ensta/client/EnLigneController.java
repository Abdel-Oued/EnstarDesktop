package fr.ensta.client;

import fr.ensta.serveurMessagerie.IMessagerie;
import fr.ensta.serveurMessagerie.Messagerie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Controller principal de la section En ligne.
 * */
public class EnLigneController {
    public Button buttonUpdate;
    @FXML private ListView utilisateursConnectes;
    @FXML private AnchorPane enLigne;
    @FXML private MainClientController mainController;

    /**
     * Permet de recuperer le controller principal mainClientController
     * @param mainClientController
     * */
    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }

    @FXML
    private void update() {
        utilisateursConnectes.getItems().clear();
        mainController.getUser().getMonClientTCP().transmettreChaine("utilisateursConnectes");
        ArrayList<String> connectedUsers = mainController.getUser().getConnectedUsers();

        for (String user : connectedUsers) {
            utilisateursConnectes.getItems().add(user);
        }

    }
}
