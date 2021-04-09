package fr.ensta.serveurMessagerie;

import fr.ensta.servPattern.ProcessusEchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public interface IMessagerie {
    public void envoyerMessage(String message, String destinataire, String emetteur, ProcessusEchange pe);
    public ArrayList<String> getConnectedUsers();
    public ArrayList<String> getAllUsers();

    void addConnectedUser(String nomUtilisateur);

    void removeConnectedUser(String nomUtilisateur);

    boolean removeUser(String nomUtilisateur, String motDePasse) throws IOException;

    boolean addUser(String nomUtilisateur, String motDePasse);

    boolean identify(String nomUtilisateur, String motDePasse);
}
