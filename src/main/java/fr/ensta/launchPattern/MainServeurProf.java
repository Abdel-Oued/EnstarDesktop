package fr.ensta.launchPattern;

import fr.ensta.servPattern.IContext;
import fr.ensta.servPattern.ServeurTCP;

public class MainServeurProf {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP((IContext) new UnContexte(), new ProtocolePingPong() , 6666 );
		myServ.start();
		
	}
}
