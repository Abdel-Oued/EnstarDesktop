package fr.ensta.client;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

/**
 * Controller principal de la section En ligne.
 * */
public class EnLigneController {
    @FXML private ScrollPane enLigne;
    @FXML private MainClientController mainController;

    /**
     * Permet de recuperer le controller principal mainClientController
     * @param mainClientController
     * */
    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }
}
