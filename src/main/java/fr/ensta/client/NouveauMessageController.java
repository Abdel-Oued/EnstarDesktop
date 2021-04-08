package fr.ensta.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controller principal de la section Nouveau Message.
 * */
public class NouveauMessageController {

    @FXML private VBox nouveauMessage;
    @FXML private Button boutonEnvoi;
    @FXML private Button buttonEnvoi;

    public TextField getDestinataire() {
        return destinataire;
    }

    public TextArea getCorpsMessage() {
        return corpsMessage;
    }

    @FXML private TextField destinataire;
    @FXML private TextArea corpsMessage;
    @FXML private MainClientController mainController;

    /**
     * Permet de recuperer le controller principal mainClientController
     * @param mainClientController
     * */
    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }

    @FXML private void envoyerMessage(ActionEvent actionEvent) {
        if (destinataire.getText().contentEquals("everyone"))
            mainController.getUser().envoyerMessageTout(corpsMessage.getText());
        else
            mainController.getUser().envoyerMessage(corpsMessage.getText() + "#" + destinataire.getText());
    }

    @FXML private void initialize(){}

}
