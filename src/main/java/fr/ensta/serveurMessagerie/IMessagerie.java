package fr.ensta.serveurMessagerie;

import fr.ensta.servPattern.ProcessusEchange;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

public interface IMessagerie {
    public void envoyerMessage(String message, String destinataire, String emetteur, ProcessusEchange pe);
    public ArrayList<String> getConnectedUsers();

    void addConnectedUser(String nomUtilisateur);

    void removeConnectedUser(String nomUtilisateur);
}
