package fr.ensta.client;

import fr.ensta.identification.Identification;

public class Admin implements IUser{
    private String username;
    private String password;
    private ClientTCP monClientTCP;
    //private int port;

    public Admin(String username, String password, int port){
        this.username=username;
        this.password = password;
        this.monClientTCP = new ClientTCP("localhost",port);
    }

    /**
     * Connecte l'administrateur au serveur.
     * */
    @Override
    public boolean connexionServeur() {
        Identification identification = new Identification();
        boolean recognized = identification.identify(this.username, this.password);

        if (recognized) {
            System.out.println("Administrateur reconnu, connexion... !");
            boolean connected = monClientTCP.connecterAuServeur();

            if (connected) {

            }
            return connected;
        }
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
}
