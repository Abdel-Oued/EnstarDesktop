package fr.ensta.launchPattern;

import fr.ensta.client.ClientTCP;

public class MainClientProf {

	public static void main(String[] args) {
		ClientTCP myClt = new ClientTCP("localhost", 6666 );
		
		if ( myClt.connecterAuServeur() ) {
			myClt.transmettreChaine("PING");
			myClt.deconnecterDuServeur();
		}
	
	}

}
