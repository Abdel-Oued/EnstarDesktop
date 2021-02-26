package fr.ensta.client;

public interface IUser {
    boolean ConnexionServeur();

    void DeconnexionServeur();
    int envoyerMessage(String message);
    int getUserID();
}
