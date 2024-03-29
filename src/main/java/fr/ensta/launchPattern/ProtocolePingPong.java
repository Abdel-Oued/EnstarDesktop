package fr.ensta.launchPattern;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import fr.ensta.servPattern.IContext;
import fr.ensta.servPattern.IProtocole;
import fr.ensta.servPattern.ProcessusEchange;


public class ProtocolePingPong implements IProtocole {

	public void execute(IContext c , BufferedReader is, PrintStream os, String inputReq, ProcessusEchange processusEchangeCourant) {
		
//		String inputReq;
//		BufferedReader is = new BufferedReader(new InputStreamReader(
//				unInput));
//		PrintStream os = new PrintStream(unOutput);
		try {
			String valeurExpediee = "";

			if ((inputReq = is.readLine()) != null) {
				System.out.println(" Ordre Recu " + inputReq);
				String chaines[] = inputReq.split(" ");

				if (chaines[0].contentEquals("PING")) {
					valeurExpediee = "PONG";
					System.out.println(" Reponse serveur "	+ valeurExpediee);
				}
				os.println(valeurExpediee);
			}
		} catch ( Exception e) {
			System.out.println(" Pb d'exception ");
		}			
	}
}
