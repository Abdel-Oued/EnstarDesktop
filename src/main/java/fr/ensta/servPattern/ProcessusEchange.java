package fr.ensta.servPattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Processus de Echange (anciennement ServeurSpecifique)
 */
public class ProcessusEchange extends Thread {

	public ServeurTCP getMonServeurTCP() {
		return monServeurTCP;
	}

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;
	private String nom;
	boolean isloggedin;
	private IProtocole protocole;

	public ProcessusEchange(Socket uneSocket, ServeurTCP unServeur, String name) {
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[ProcessusEchange] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;

		// les ajouts commencent la
		protocole = monServeurTCP.getProtocole();
		nom = name;
		isloggedin = true;
	}


	public void run() {
		String inputReq;
		BufferedReader is = null;
		PrintStream os = null;
		try {
			is = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("[ProtocoleEchange] Probleme d'exception ");
		}

		while(isloggedin){
			try {
				assert is != null;
				if ((inputReq = is.readLine()) != null) {
					System.out.println("[ProtocoleEchange] Ordre Recu " + inputReq);

					String[] chaines = inputReq.split("#");
					String entete = chaines[0];

					if (entete.contentEquals("messageSimple") || entete.contentEquals("logout")) {
						protocole = new ProtocoleEnvoieSimple();
					} else if (entete.contentEquals("utilisateursConnectes")) {
						protocole = new ProtocoleEnLigne();
					} else if (entete.contentEquals("connexion")) {
						protocole = new ProtocoleConnexion();
					} else if (entete.contentEquals("messageMultiple")) {
						protocole = new ProtocolEnvoieMultiple();
					}
				}

				//monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream(), this );
				//this.protocole.execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream(), inputReq, this );
				this.protocole.execute( monServeurTCP.getContexte() , is , os, inputReq, this );

				System.out.println("[ProcessusEchange] Transaction faite ");
			} catch (IOException e) {
				System.err.println("[ProcessusEchange] Exception : " + e );
				e.printStackTrace();
			}
		}
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public IProtocole getProtocole() {
		return protocole;
	}

	public void setProtocole(IProtocole protocole) {
		this.protocole = protocole;
	}

}
