package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;


/**
 * Protocole pour l'envoie d'un message simple (a un seul destinataire).
 * */
public class ProtocoleTousUtilisateurs implements IProtocole {


    /**
     * .
     * */
    //public void execute(IContext c , InputStream unInput , OutputStream unOutput, String inputReq, ProcessusEchange processusEchangeCourant ) {
    public void execute(IContext c , BufferedReader is, PrintStream os, String inputReq, ProcessusEchange processusEchangeCourant ) {
        IMessagerie maMessagerie = (IMessagerie) c;
        //String inputReq;
        //BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
        //PrintStream os = new PrintStream(unOutput);
//        try {
//
//            System.out.println("[ProtocoleTousUtilisateurs] Ordre Recu " + inputReq);
//
//            ArrayList<String> allUsers = maMessagerie.getAllUsers();
//            StringBuilder allUsersConcat = new StringBuilder();
//            allUsersConcat.append("listeUtilisateurs#");
//            for (String user : allUsers) {
//                allUsersConcat.append(user).append("-");
//            }
//            os.println(allUsersConcat);
//
//        } catch ( Exception e) {
//            System.out.println("[ProtocoleTousUtilisateurs] Probleme d'exception ");
//        }

        try {
            String valeurExpediee = "";

            //if ((inputReq = is.readLine()) != null) {
            if (true) {
                System.out.println("[ProtocoleTousUtilisateurs] Ordre Recu " + inputReq);

                ArrayList<String> allUsers = maMessagerie.getAllUsers();
                StringBuilder allUsersConcat = new StringBuilder();
                allUsersConcat.append("listeUtilisateurs#");
                for (String user : allUsers) {
                    allUsersConcat.append(user).append("-");
                }
                os.println(allUsersConcat);
                //processusEchangeCourant.setProtocole(new ProtocoleEnvoieSimple());
                System.out.println("[ProtocoleTousUtilisateurs] Mise a jour ! ");

            }
        } catch ( Exception e) {
            System.out.println("[ProtocoleTousUtilisateurs] Probleme d'exception ");
        }


    }
}


