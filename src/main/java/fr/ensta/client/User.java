package fr.ensta.client;

import fr.ensta.identification.Identification;

/**
 * Cette classe represente un utilisateur de l'application.
 * */
public class User implements IUser{
    private String username;
    private String password;
    private ClientTCP monClientTCP;

    private BoiteReception boiteReception;
    //private int port;

    public User(String username, String password, int port){
        this.username=username;
        this.password = password;
        this.monClientTCP = new ClientTCP("localhost",port);
        this.boiteReception = new BoiteReception();
    }

    /**
     * Envoie un message.
     * @param message
     * */
    public void envoyerMessage(String message){
        // message contient le destinataire à qui envoyer
        String statusString = monClientTCP.transmettreChaine(message);
    }

    /**
     * Connecte l'utilisateur au serveur.
     * */
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
        System.out.println("Utilisateur non reconnu !");
        return false;

    }

    /**
     * Deconnecte l'utilisateur du serveur.
     * */
    @Override
    public void deconnexionServeur() {
        monClientTCP.transmettreChaine("logout");
        monClientTCP.deconnecterDuServeur();
    }

    public ClientTCP getMonClientTCP() {
        return monClientTCP;
    }

    public BoiteReception getBoiteReception() {
        return boiteReception;
    }

}
