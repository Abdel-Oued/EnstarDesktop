package fr.ensta.client;

public interface IUser {
    boolean ConnexionServeur();

    void DeconnexionServeur();

    int getUserID();
}
