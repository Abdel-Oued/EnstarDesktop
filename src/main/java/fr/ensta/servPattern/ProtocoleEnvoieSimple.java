package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;


/**
 * Protocole pour l'envoie d'un message simple (a un seul destinataire).
 * */
public class ProtocoleEnvoieSimple implements IProtocole {

    /**
     * Envoie le message a un utilisateur.
     * */
    //public void execute(IContext c , InputStream unInput , OutputStream unOutput, String inputReq, ProcessusEchange processusEchangeCourant ) {
    public void execute(IContext c , BufferedReader is, PrintStream os, String inputReq, ProcessusEchange processusEchangeCourant) {
    IMessagerie maMessagerie = (IMessagerie) c;
        //String inputReq;
        //BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
        //PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            //if ((inputReq = is.readLine()) != null) {
            if (true) {
                System.out.println("[ProtocoleEnvoieSimple] Ordre Recu " + inputReq);

                if (inputReq.contentEquals("logout")) {
                    maMessagerie.removeConnectedUser(processusEchangeCourant.getNom());
                    processusEchangeCourant.isloggedin = false;
                    processusEchangeCourant.getClientSocket().close();
                }
                else{
                    String[] chaines = inputReq.split("#");
                    String entete = chaines [0];
                    String message = chaines[1];
                    String destinataire = chaines[2];

                    // recherche dans la liste des connectes
                    Vector<ProcessusEchange> processusConnectes = ServeurTCP.getProcessusConnectes();
                    //ArrayList<String> connectedUsers = maMessagerie.getConnectedUsers();

                    for (ProcessusEchange pe : processusConnectes) {
                        if (pe.getNom().equals(destinataire) && pe.isloggedin) {
                            // possibilite d'appleler une methode de Messagerie
                            maMessagerie.envoyerMessage(message, destinataire, processusEchangeCourant.getNom(), pe);

                            //PrintStream destinataireOS  = new PrintStream(pe.getClientSocket().getOutputStream());
                            //destinataireOS.println("messageSimple#[" + processusEchangeCourant.getNom() + "] "+message);
                            //destinataireOS.flush();

                            break;
                        }


                    }
                }

            }
        } catch ( Exception e) {
            System.out.println("[ProtocoleEnvoieSimple] Probleme d'exception ");
        }
    }
}

