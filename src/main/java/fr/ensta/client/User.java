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

    /**
     * renvoie 1 si message reçu par le serveur et 0 sinon
     * */
    public int envoyerMessage(String message){
        // message contient le destinataire à qui envoyer
        String statusString = monClientTCP.transmettreChaine(message);
        int status = Integer.parseInt(statusString);
        return status;
    }

    @Override
    public boolean ConnexionServeur() {

        return monClientTCP.connecterAuServeur();
    }

    @Override
    public void DeconnexionServeur() {
        monClientTCP.deconnecterDuServeur();
    }

    @Override
    public int getUserID() {
        return 0;
    }
}
