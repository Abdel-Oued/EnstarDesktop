package fr.ensta.client;

import java.io.IOException;
import java.net.UnknownHostException;

public class RecevoirMessage extends Thread{
    private User user;

    public RecevoirMessage(User unUser){

        user = unUser;

    }

    @Override
    public void run() {
        String messageRecu;
        while (user.getMonClientTCP().isConnected()) {
            try {
                if ( (messageRecu = user.getMonClientTCP().getSocIn().readLine()) != null)
                System.out.println( "Message recu : " + messageRecu);

            } catch (UnknownHostException e) {
                System.err.println("Serveur inconnu : " + e);
            } catch (IOException e) {
                System.err.println("Exception entree/sortie:  " + e);
                e.printStackTrace();
            }
        }
    }
}
