package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.BufferedReader;
import java.io.PrintStream;


/**
 * Protocole pour l'envoie d'un message simple (a un seul destinataire).
 * */
public class ProtocoleAjoutSuppression implements IProtocole {

    //private Identification identification = new Identification();
    /**
     * .
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
            System.out.println("[ProtocoleAjoutSuppression] Ordre Recu " + inputReq);

            String[] chaines = inputReq.split("#");
            String entete = chaines[0];
            String nomUtilisateur = chaines[1];
            String motDePasse = chaines[2];

            if (entete.contentEquals("ajoutUtilisateur")) {

                if (maMessagerie.addUser(nomUtilisateur, motDePasse)) {

                    System.out.println("[ProtocoleAjoutSuppression] Ajout reussi ...");
                }
                else {
                    System.out.println("[ProtocoleAjoutSuppression]  L'ajout a echoue");
                }
            }
            else {

                if (maMessagerie.removeUser(nomUtilisateur, motDePasse)) {

                    System.out.println("[ProtocoleAjoutSuppression] Suppression reussite ... ! ");
                }
                else {
                    System.out.println("[ProtocoleAjoutSuppression] La suppression a echoue ...");
                }
            }
        } catch ( Exception e) {
            System.out.println("[ProtocoleAjoutSuppression] Probleme d'exception ");
        }

    }
}


