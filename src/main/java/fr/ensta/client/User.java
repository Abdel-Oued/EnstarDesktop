package fr.ensta.client;

import java.net.Socket;

public class User implements IUser{
    private String username;
    private String pwd;
    private ClientTCP monClientTCP;
    private int port;
    // create simple user
    public User(String username,String pwd){
        this.username=username;
        this.pwd=pwd;
        monClientTCP=new ClientTCP("localhost",port);
    }
    public int envoyer(String message,IUser user){
        monClientTCP.transmettreChaine(message);
        return 0;
    }
    @Override
    public boolean ConnexionServeur() {
        return false;
    }

    @Override
    public void DeconnexionServeur() {

    }

    @Override
    public int getUserID() {
        return 0;
    }
}
