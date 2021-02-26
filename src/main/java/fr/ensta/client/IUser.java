package fr.ensta.client;

public interface IUser {
    boolean connexionServeur();

    void deconnexionServeur();
    int envoyerMessage(String message);
    int getUserID();
}
