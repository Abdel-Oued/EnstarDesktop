package fr.ensta.client;

import java.util.ArrayList;
import java.util.Observable;

public class BoiteReception extends Observable {

    private ArrayList<String> listMessages = new ArrayList<>();

    public BoiteReception() {

    }

    /**
     * Ajoute le nouveau message eu debut le la liste de messages recus.
     * @param nouveauMessage
     */
    public void addMessage(String nouveauMessage) {
        listMessages.add(0, nouveauMessage);

        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<String> getListMessages() {
        return listMessages;
    }
}
