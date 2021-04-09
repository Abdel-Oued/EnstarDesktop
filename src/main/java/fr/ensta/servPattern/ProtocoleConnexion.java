package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.*;
import java.util.Vector;


/**
 * Protocole pour l'envoie d'un message simple (a un seul destinataire).
 * */
public class ProtocoleConnexion implements IProtocole {

    //private Identification identification = new Identification();
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
                System.out.println("[ProtocoleConnexion] Ordre Recu " + inputReq);

                String[] chaines = inputReq.split("#");
                String entete = chaines[0];
                String nomUtilisateur = chaines[1];
                String motDePasse = chaines[2];

                if (maMessagerie.identify(nomUtilisateur, motDePasse)) {
                    maMessagerie.addConnectedUser(nomUtilisateur);
                    processusEchangeCourant.setNom(nomUtilisateur);
                    //processusEchangeCourant.setProtocole(new ProtocoleEnvoieSimple());
                    System.out.println("[ProtocoleConnexion] Identification reussie: Utilisateur reconnu, connexion... ! ");
                }
                else {
                    System.out.println("[ProtocoleConnexion] Identification echouee: Utilisateur non reconnu ! ");
                }
            }
        } catch ( Exception e) {
            System.out.println("[ProtocoleConnexion] Probleme d'exception ");
        }

    }
}


