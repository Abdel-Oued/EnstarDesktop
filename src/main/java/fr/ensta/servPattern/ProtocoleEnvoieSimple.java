package fr.ensta.servPattern;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;


public class ProtocoleEnvoieSimple implements IProtocole {

    public void execute(IContext c , InputStream unInput , OutputStream unOutput ) {

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            if ((inputReq = is.readLine()) != null) {
                System.out.println(" Ordre Recu " + inputReq);
                String chaines[] = inputReq.split("#");

//                if (chaines[0].contentEquals("Message_simple")) {
//                    valeurExpediee = "PONG";
//                    System.out.println(" Reponse serveur "	+ valeurExpediee);
//                }
//                os.println(valeurExpediee);
                String message = chaines[0];
                String destinataire = chaines[1];

                for (ProcessusEchange pe : ServeurTCP.getProcessusConnectes()) {


                }
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}

