package fr.ensta.client;

import java.util.ArrayList;

/**
 * Cette classe represente un administrateur de l'application.
 * */
public class Admin extends User{
    private String username;
    private String password;
    private ClientTCP monClientTCP;

    private BoiteReception boiteReception;
    private ArrayList<String> connectedUsers;
    //private int port;

    public Admin(String username, String password, int port){
        super(username, password, port);
//        this.username=username;
//        this.password = password;
//        this.monClientTCP = new ClientTCP("localhost",port);
//        this.boiteReception = new BoiteReception();
//        this.connectedUsers = new ArrayList<>();
    }

    /**
     * Connecte l'administrateur au serveur.
     * */
    @Override
    public boolean connexionServeur() {
//        Identification identification = new Identification();
//        boolean recognized = identification.identify(this.username, this.password);
//
//        if (recognized) {
//            System.out.println("Administrateur reconnu, connexion... !");}
        boolean connected = monClientTCP.connecterAuServeur();

        if (connected) {
            monClientTCP.transmettreChaine("connexion#"+username+"#"+password);
            RecevoirMessage recevoirMessage = new RecevoirMessage(this);
            recevoirMessage.start();
            return connected;
        }
            //return connected;
        //}
        System.out.println("Administrateur non reconnu !");
        return false;

    }

    /**
     * Deconnecte l'administrateur du serveur
     * */
    @Override
    public void deconnexionServeur() {
        monClientTCP.transmettreChaine("logout");
        monClientTCP.deconnecterDuServeur();
    }

    public ClientTCP getMonClientTCP() {
        return monClientTCP;
    }

    @Override
    public BoiteReception getBoiteReception() {
        return null;
    }

    @Override
    public void setConnectedUsers(ArrayList<String> connectedUsers) {

    }
}
