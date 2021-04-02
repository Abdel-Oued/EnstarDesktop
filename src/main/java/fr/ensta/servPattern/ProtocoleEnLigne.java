package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.*;
import java.util.ArrayList;


/**
 * Protocole pour l'envoie d'un message simple (a un seul destinataire).
 * */
public class ProtocoleEnLigne implements IProtocole {

    /**
     * Envoie le message a un utilisateur.
     * */
    //public void execute(IContext c , InputStream unInput , OutputStream unOutput, String inputReq, ProcessusEchange processusEchangeCourant ) {
    public void execute(IContext c , BufferedReader is, PrintStream os, String inputReq, ProcessusEchange processusEchangeCourant ) {
        IMessagerie maMessagerie = (IMessagerie) c;
        //String inputReq;
        //BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
        //PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            //if ((inputReq = is.readLine()) != null) {
            if (true) {
                System.out.println("[ProtocoleEnLigne] Ordre Recu " + inputReq);

                ArrayList<String> connectedUsers = maMessagerie.getConnectedUsers();
                StringBuilder connectedUsersConcat = new StringBuilder();
                connectedUsersConcat.append("listeConnectes#");
                for (String user : connectedUsers) {
                    connectedUsersConcat.append(user).append("-");
                }
                os.println(connectedUsersConcat);
                //processusEchangeCourant.setProtocole(new ProtocoleEnvoieSimple());
                System.out.println("[ProtocoleEnLigne] Mise a jour ! ");

            }
        } catch ( Exception e) {
            System.out.println("[ProtocoleEnLigne] Probleme d'exception ");
        }

    }
}


