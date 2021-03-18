package fr.ensta.client;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

/**
 * Controller principal de la section Boite de reception.
 * */
public class BoiteReceptionController {
    @FXML private ScrollPane boiteReception;
    @FXML private MainClientController mainController;

    /**
     * Permet de recuperer le controller principal mainClientController
     * @param mainClientController
     * */
    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }
}
