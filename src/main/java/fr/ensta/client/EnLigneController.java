package fr.ensta.client;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

public class EnLigneController {
    @FXML private ScrollPane enLigne;
    @FXML private MainClientController mainController;

    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }
}
