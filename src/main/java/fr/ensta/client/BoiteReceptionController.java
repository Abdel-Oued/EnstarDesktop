package fr.ensta.client;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

public class BoiteReceptionController {
    @FXML private ScrollPane boiteReception;
    @FXML private MainClientController mainController;

    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }
}
