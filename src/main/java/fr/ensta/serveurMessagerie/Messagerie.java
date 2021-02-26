package fr.ensta.serveurMessagerie;

import fr.ensta.servPattern.IContext;

import java.util.Observable;

public class Messagerie extends Observable implements IMessagerie, IContext {

    @Override
    public String envoyerMessage(String message) {
        return null;
    }
}
