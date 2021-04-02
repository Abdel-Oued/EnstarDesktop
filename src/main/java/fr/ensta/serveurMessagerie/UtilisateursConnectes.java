package fr.ensta.serveurMessagerie;

import java.util.ArrayList;
import java.util.Observable;

public class UtilisateursConnectes extends Observable {
    private ArrayList<String> listConnectedUsers = new ArrayList<>();

    public UtilisateursConnectes() {

    }

    /**
     * Ajoute l'utilisateur à la liste des utilisateurs en ligne
     * @param user
     */
    public void addUser(String user) {
        listConnectedUsers.add(user);

        this.setChanged();
        this.notifyObservers();
    }

    /**
     * supprime l'utilisateur de la liste des utilisateurs en ligne
     * @param user
     */
    public void removeUser(String user) {
        listConnectedUsers.add(user);

        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<String> getListConnectedUsers() {
        return listConnectedUsers;
    }
}
