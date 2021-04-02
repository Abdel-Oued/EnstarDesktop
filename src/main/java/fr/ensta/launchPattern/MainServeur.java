package fr.ensta.launchPattern;

import fr.ensta.servPattern.IContext;
import fr.ensta.servPattern.ProtocoleConnexion;
import fr.ensta.servPattern.ProtocoleEnvoieSimple;
import fr.ensta.servPattern.ServeurTCP;
import fr.ensta.serveurMessagerie.Messagerie;

public class MainServeur {

    public static void main(String[] args) {
        ServeurTCP myServ = new ServeurTCP((IContext) new Messagerie(), new ProtocoleConnexion() , 6666 );
        myServ.start();

    }
}
