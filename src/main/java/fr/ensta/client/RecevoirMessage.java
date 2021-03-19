package fr.ensta.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * C'est un thread qui se lance a la connection d'un nouvel utilisateur.
 * Il est charge de recuperer les messages a destination de l'utilisateur.
 * */
public class RecevoirMessage extends Thread{
    private User user;

    public RecevoirMessage(User aUser){

        user = aUser;

    }

    @Override
    public void run() {
        String messageRecu;
        while (user.getMonClientTCP().isConnected()) {
            try {
                if ( (messageRecu = user.getMonClientTCP().getSocIn().readLine()) != null) {
                    System.out.println( "Message recu : " + messageRecu);
                    user.getBoiteReception().addMessage(messageRecu);
                }

            } catch (UnknownHostException e) {
                System.err.println("Serveur inconnu : " + e);
            } catch (SocketException e) {
                //System.err.println("Socket ferme:  " + e);
                //e.printStackTrace();
            } catch (IOException e) {
                //System.err.println("Exception entree/sortie:  " + e);
                //e.printStackTrace();
            }
        }
    }
}
