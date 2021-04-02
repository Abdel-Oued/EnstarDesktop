package fr.ensta.client;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;
import java.util.Observer;

/**
 * Controller principal de la section Boite de reception.
 * */
public class BoiteReceptionController implements Observer {

    @FXML private ListView messagesRecus;
    @FXML private AnchorPane boiteReception;
    @FXML private MainClientController mainController;

    /**
     * Permet de recuperer le controller principal mainClientController
     * @param mainClientController
     * */
    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }

    @FXML private void initialize(){
    }

    @Override
    public void update(Observable o, Object arg) {
        BoiteReception br = (BoiteReception) o;

        // on affiche le dernier message recu
        //messagesRecus.setText(br.getListMessages().get(0));
        messagesRecus.getItems().add(0, br.getListMessages().get(0));

    }
}
