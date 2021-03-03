package fr.ensta.servPattern;

import fr.ensta.serveurMessagerie.IMessagerie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;


public class ProtocoleEnvoieSimple implements IProtocole {

    public void execute(IContext c , InputStream unInput , OutputStream unOutput, ProcessusEchange processusCourant ) {

        IMessagerie maMessagerie = (IMessagerie) c;
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            if ((inputReq = is.readLine()) != null) {
                System.out.println("[ProcessusEnvoieSimple] Ordre Recu " + inputReq);

                if (inputReq.contentEquals("logout")) {
                    processusCourant.isloggedin = false;
                    processusCourant.getClientSocket().close();
                }
                else{
                    String[] chaines = inputReq.split("#");

    //                if (chaines[0].contentEquals("Message_simple")) {
    //                    valeurExpediee = "PONG";
    //                    System.out.println(" Reponse serveur "	+ valeurExpediee);
    //                }
    //                os.println(valeurExpediee);
                    String message = chaines[0];
                    String destinataire = chaines[1];

                    Vector<ProcessusEchange> processusConnectes = ServeurTCP.getProcessusConnectes();

                    for (ProcessusEchange pe : processusConnectes) {
                        if (pe.getNom().equals(destinataire) && pe.isloggedin) {
                            //status = maMessagerie.envoyerMessage(message, pe);
                            PrintStream destinataireOS  = new PrintStream(pe.getClientSocket().getOutputStream());
                            destinataireOS.println("[Message de " + processusCourant.getNom() + "] "+message);
                            destinataireOS.flush();

                            break;
                        }


                    }
                }


            }
        } catch ( Exception e) {
            System.out.println("[ProcessusEnvoieSimple] Pb d'exception ");
        }
    }
}

