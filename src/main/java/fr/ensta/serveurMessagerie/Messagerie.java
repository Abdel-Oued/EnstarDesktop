package fr.ensta.serveurMessagerie;

import fr.ensta.servPattern.IContext;
import fr.ensta.servPattern.ProcessusEchange;
import java.util.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Messagerie extends Observable implements IMessagerie, IContext {

    private ArrayList<String> connectedUsers = new ArrayList<>();

    @Override
    public void envoyerMessage(String message, String destinataire, String emetteur, ProcessusEchange pe) {
        PrintStream destinataireOS  = null;
        try {
            destinataireOS = new PrintStream(pe.getClientSocket().getOutputStream());
            destinataireOS.println("messageSimple#[" + emetteur + "] "+message);
            destinataireOS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ajoute l'utilisateur à la liste des utilisateurs en ligne
     * @param user
     */
    public void addConnectedUser(String user) {
        connectedUsers.add(user);

        this.setChanged();
        this.notifyObservers();
    }

    /**
     * supprime l'utilisateur de la liste des utilisateurs en ligne
     * @param user
     */
    public void removeConnectedUser(String user) {
        connectedUsers.remove(user);

        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<String> getConnectedUsers() {
        return connectedUsers;
    }

}
