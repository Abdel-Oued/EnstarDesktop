package fr.ensta.client;

public interface IUser {
    boolean connexionServeur();

    void deconnexionServeur();
    void envoyerMessage(String message);
    int getUserID();
}
