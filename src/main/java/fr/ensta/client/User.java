package fr.ensta.client;

import fr.ensta.identification.Identification;

public class User implements IUser{
    private String username;
    private String password;


    private ClientTCP monClientTCP;
    //private int port;

    // create simple user
    public User(String username, String password, int port){
        this.username=username;
        this.password = password;
        this.monClientTCP = new ClientTCP("localhost",port);
    }

    /**
     * renvoie 1 si message reçu par le serveur et 0 sinon
     * */
    public void envoyerMessage(String message){
        // message contient le destinataire à qui envoyer
        String statusString = monClientTCP.transmettreChaine(message);
    }

    @Override
    public boolean connexionServeur() {
        Identification identification = new Identification();
        boolean recognized = identification.identify(this.username, this.password);

        if (recognized) {
            System.out.println("Utilisateur reconnu, connexion... !");
            boolean connected = monClientTCP.connecterAuServeur();

            if (connected) {
                RecevoirMessage recevoirMessage = new RecevoirMessage(this);
                recevoirMessage.start();
            }
            return connected;
        }
        System.out.println("Utilisateur nom reconnu !");
        return false;

    }

    @Override
    public void deconnexionServeur() {
        monClientTCP.transmettreChaine("logout");
        monClientTCP.deconnecterDuServeur();
    }

    @Override
    public int getUserID() {
        return 0;
    }

    public ClientTCP getMonClientTCP() {
        return monClientTCP;
    }

}
