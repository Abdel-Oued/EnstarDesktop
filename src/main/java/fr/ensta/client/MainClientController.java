package fr.ensta.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Controller principal de la fenetre de discussion.
 * */
public class MainClientController {

    @FXML private Button buttonConnexion;
    @FXML private Button buttonDeconnexion;
    @FXML private TextField username;
    @FXML private PasswordField password;

    @FXML private AnchorPane boiteReception;
    @FXML private VBox nouveauMessage;
    @FXML private AnchorPane enLigne;
    @FXML private Tab loginTab;
    @FXML private Tab discussionTab;
    @FXML private Tab adminTab;
    @FXML private TextField destinataire;
    @FXML private TextArea corpsMessage;
    @FXML private Label resultatConnexion;
    @FXML private TabPane allTabs;

    @FXML private NouveauMessageController nouveauMessageController;
    @FXML private BoiteReceptionController boiteReceptionController;
    @FXML private EnLigneController enLigneController;
    @FXML private AddRemoveClientController addRemoveClientController;
    private static User user;

    /**
     * Cette fonction est appelee lors de l'instantiation du controller.
     * Chaque sous-controller a un attribut qui sera une l'actuel controller principal.
     * */
    @FXML private void initialize(){

        nouveauMessageController.injectMainController(this);
        boiteReceptionController.injectMainController(this);
        enLigneController.injectMainController(this);
        addRemoveClientController.injectMainController(this);
        destinataire = nouveauMessageController.getDestinataire();
        corpsMessage = nouveauMessageController.getCorpsMessage();
        allTabs.getTabs().remove(adminTab);
        allTabs.getTabs().remove(discussionTab);

    }

    @FXML
    private void connexion(ActionEvent actionEvent) {
        user = new User(username.getText(), password.getText(), 6666);
        if ( user.connexionServeur() ) {
            if(user.getUsername().contentEquals("admin")) {
                //user = (Admin) user;
                allTabs.getTabs().add(adminTab);
            } else
                allTabs.getTabs().add(discussionTab);
            buttonConnexion.setVisible(false);
            buttonDeconnexion.setVisible(true);
            destinataire.setEditable(true);
            corpsMessage.setEditable(true);
            resultatConnexion.setText("Connexion reussie");
            resultatConnexion.setVisible(true);
            loginTab.setText(username.getText());

            user.getBoiteReception().addObserver(boiteReceptionController);
        }

        else {
            resultatConnexion.setText("Connexion echouee");
            resultatConnexion.setVisible(true);
        }
    }

    public User getUser() {
        return user;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getDestinataire() {
        return destinataire;
    }

    public void deconnexion(ActionEvent actionEvent) {
        user.deconnexionServeur();
        System.out.println("Fermeture de la session");
        Platform.exit();
    }

    public static void close(WindowEvent event) {
        if(user!=null){user.deconnexionServeur();}
    }
}
