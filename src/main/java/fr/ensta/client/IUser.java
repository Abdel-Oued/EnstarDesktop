package fr.ensta.client;

import java.util.ArrayList;

/**
 * ...
 * */
public interface IUser {
    boolean connexionServeur();
    void deconnexionServeur();

    ClientTCP getMonClientTCP();

    BoiteReception getBoiteReception();

    void setConnectedUsers(ArrayList<String> connectedUsers);

    void setAllUsers(ArrayList<String> allUsers);
}
