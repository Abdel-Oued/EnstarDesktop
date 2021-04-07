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
    @FXML private ListView lstUsers;
    @FXML private AnchorPane Users;
    @FXML private ListView lstAddUsers;
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
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
        lstUsers.getItems().clear();
        //ShowUsers.getItems().add("hello");
        try {
            File myObj = new File("src\\main\\resources\\fr.ensta.identification\\utilisateurs.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lstUsers.getItems().add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        }
    @FXML
    private void AddUsers() {
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        try {
            //test if exist***
            FileWriter myWriter = new FileWriter("src\\main\\resources\\fr.ensta.identification\\utilisateurs.txt",true);
            myWriter.write(username+";"+password+'\n');
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    @FXML
    private void RemoveUsers() throws IOException {
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        File inputFile = new File("src\\main\\resources\\fr.ensta.identification\\utilisateurs.txt");
        File tempFile = new File("src\\main\\resources\\fr.ensta.identification\\myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = username+";"+password;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        FileChannel src = new FileInputStream(tempFile).getChannel();
        FileChannel dest = new FileOutputStream(inputFile).getChannel();
        dest.transferFrom(src, 0, src.size());
    }

    }

