package servPattern;
import java.io.*;
import java.net.*;


public class ServeurTCP extends Thread{

	private static int nbConnexions = 0;
	
	/** Maximum de connexions client autorisées */
	private int maxConnexions;
	
	private Socket clientSocket;

	private IContext contexte;
	
	private IProtocole protocole;
	
	private int numeroPort;

	public ServeurTCP(int unNumeroPort) {        
		numeroPort = unNumeroPort;
		maxConnexions = 10;
	} 

	public ServeurTCP(IContext b,IProtocole p, int port) {
		this(port);
		contexte = b;
		protocole = p;
	}

	public void setBanqueCentrale(IContext uneBanque) {        
		contexte = uneBanque;
	} 

	public IContext getBanqueCentrale() {        
		return contexte;
	} 

	public String toString() {        
		return "[ServeurTCP] Port : " +  numeroPort + ", Contexte: " + contexte ;
	} 

	/* l'ancienne methode go est remplacee par run */
	public void run() {        
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket ( numeroPort );
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + numeroPort + ", " + e);
			System.exit(1);
		}

		
		/* On autorise maxConnexions traitements*/
		while (nbConnexions <= maxConnexions) {
			try {
				System.out.println(" Attente du serveur pour la communication d'un client " );
				clientSocket = serverSocket.accept();
				nbConnexions ++;
				System.out.println("Nb automates : " + nbConnexions);
			} catch (IOException e) {
				System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
			ProcessusTransaction st = new ProcessusTransaction( clientSocket , this );
			st.start();
		}
		System.out.println("Deja " + nbConnexions + " clients. Maximum autorisé atteint");

		try {
			serverSocket.close();
			nbConnexions --;
		} catch (IOException e) {
			System.out.println("Could not close");
		}

	} 
	

	public IProtocole getProtocole() {
		return protocole;
	}

	public IContext getContexte() {
		return contexte;
	}
		

}
