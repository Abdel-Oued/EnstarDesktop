package fr.ensta.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * C'est un thread qui se lance a la connection d'un nouvel utilisateur.
 * Il est charge de recuperer les messages a destination de l'utilisateur.
 * */
public class RecevoirMessage extends Thread{
    private IUser user;

    public RecevoirMessage(IUser aUser){

        user = aUser;

    }

    @Override
    public void run() {
        String messageRecu;
        while (user.getMonClientTCP().isConnected()) {
            try {
                if ( (messageRecu = user.getMonClientTCP().getSocIn().readLine()) != null) {
                    System.out.println( "Message recu : " + messageRecu);
                    String[] chaines = messageRecu.split("#");
                    String entete = chaines[0];
                    String contenu = chaines[1];

                    if (entete.equals("messageSimple")) {
                        user.getBoiteReception().addMessage(contenu);
                    }

                    else if (entete.equals("listeConnectes")) {
                        // mise a jour de la liste user.connectedUsers
                        String[] connectedUsersList = contenu.split("-");
                        ArrayList<String> connectedUsers = new ArrayList<>(Arrays.asList(connectedUsersList));
                        user.setConnectedUsers(connectedUsers);
                    }

                    else if (entete.equals("listeUtilisateurs")) {
                        // mise a jour de la liste user.allUsers
                        String[] allUsersList = contenu.split("-");
                        ArrayList<String> allUsers = new ArrayList<>(Arrays.asList(allUsersList));
                        user.setAllUsers(allUsers);
                    }
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
