package launchPattern;

import client.ClientTCP;

public class MainClient {

	public static void main(String[] args) {
		ClientTCP myClt = new ClientTCP("localhost", 6666 );
		
		if ( myClt.connecterAuServeur() ) {
			myClt.transmettreChaine("PING");
			myClt.deconnecterDuServeur();
		}
	
	}

}
