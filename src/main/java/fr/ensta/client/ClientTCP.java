package fr.ensta.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

import static javafx.application.Application.launch;

/**
 * Client TCP qui realise les echanges avec le serveur.
 * */
public class ClientTCP {

	private int numeroPort;

	private String nomServeur;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;

	private boolean connected;

	
	/** Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port unNumero. */
	public  ClientTCP(String unNomServeur, int unNumero) {        
		numeroPort = unNumero;
		nomServeur = unNomServeur;
		connected = false;
	}

	/**
	 * Connexion du client TCP au serveur.
	 * */
	public boolean connecterAuServeur() {
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socketServeur = new Socket( nomServeur, numeroPort);
			socOut = new PrintStream(socketServeur.getOutputStream());
			socIn = new BufferedReader ( 
					new InputStreamReader (socketServeur.getInputStream()));
			connected = true;
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("Connexion faite");
		return connected;
	}

	/**
	 * Deconnexion du client TCP au le serveur.
	 * */
	public void deconnecterDuServeur() {        
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			//recevoirMessageThread.stop();
			socOut.close();
			socIn.close();
			socketServeur.close();
			connected = false;
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	}

	/**
	 * Transmet un message au serveur.
	 * */
	public String transmettreChaine(String uneChaine) {        
		String msgServeur = null;
		try {
			System.out.println( "Requete client : " + uneChaine );
			socOut.println( uneChaine );
			socOut.flush();
			//msgServeur = socIn.readLine();
			//System.out.println( "Reponse serveur : " + msgServeur );

		//} catch (UnknownHostException e) {
		//	System.err.println("Serveur inconnu : " + e);
		} catch (Exception e) {
			System.err.println("Exception entree/sortie:  " + e);
			e.printStackTrace();
		}
		return msgServeur;
	}


	/* A utiliser pour ne pas deleguer la connexion aux interfaces GUI */
	public String transmettreChaineConnexionPonctuelle(String uneChaine) {
		String msgServeur = null;
		String chaineRetour = "";
		System.out.println("\nClient connexionTransmettreChaine " + uneChaine);
		if (connecterAuServeur()) {
			try {
				socOut.println(uneChaine);
				socOut.flush();
				msgServeur = socIn.readLine();
				while( msgServeur != null && msgServeur.length() >0) {
					chaineRetour += msgServeur + "\n";
					msgServeur = socIn.readLine();
				}
				System.out.println("Client msgServeur " + chaineRetour);
				deconnecterDuServeur();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		}
		else
		{	
			System.err.println("Connexion echouee");
		}
		return chaineRetour;
	}

	public BufferedReader getSocIn() {
		return socIn;
	}

	public boolean isConnected() {
		return connected;
	}
}
