package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Vector;

public class ProtocolEnvoieMultiple implements IProtocole{

    public void execute(IContext c , BufferedReader is, PrintStream os, String inputReq, ProcessusEchange processusEchangeCourant) {
        IMessagerie maMessagerie = (IMessagerie) c;
        //String inputReq;
        //BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
        //PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            //if ((inputReq = is.readLine()) != null) {
            if (true) {
                System.out.println("[ProtocoleEnvoieMultiple] Ordre Recu " + inputReq);

                if (inputReq.contentEquals("logout")) {
                    maMessagerie.removeConnectedUser(processusEchangeCourant.getNom());
                    processusEchangeCourant.isloggedin = false;
                    processusEchangeCourant.getClientSocket().close();
                }
                else{
                    String[] chaines = inputReq.split("#");
                    String entete = chaines [0];
                    String message = chaines[1];

                    // recherche dans la liste des connectes
                    Vector<ProcessusEchange> processusConnectes = ServeurTCP.getProcessusConnectes();
                    //ArrayList<String> connectedUsers = maMessagerie.getConnectedUsers();

                    for (ProcessusEchange pe : processusConnectes) {
                        if (pe.isloggedin) {
                            // possibilite d'appleler une methode de Messagerie
                            maMessagerie.envoyerMessage(message, pe.getNom(), processusEchangeCourant.getNom(), pe);

                            //PrintStream destinataireOS  = new PrintStream(pe.getClientSocket().getOutputStream());
                            //destinataireOS.println("messageSimple#[" + processusEchangeCourant.getNom() + "] "+message);
                            //destinataireOS.flush();

                        }


                    }
                }

            }
        } catch ( Exception e) {
            System.out.println("[ProtocoleEnvoieMultiple] Probleme d'exception ");
        }
    }
}
