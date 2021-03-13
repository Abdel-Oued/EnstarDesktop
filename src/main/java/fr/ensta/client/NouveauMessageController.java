package fr.ensta.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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

    public void injectMainController(MainClientController mainClientController){
        this.mainController = mainClientController;
    }

    @FXML private void envoyerMessage(ActionEvent actionEvent) {
        mainController.getUser().envoyerMessage(corpsMessage.getText() + "#" + destinataire.getText());
    }

    @FXML private void initialize(){}
}
