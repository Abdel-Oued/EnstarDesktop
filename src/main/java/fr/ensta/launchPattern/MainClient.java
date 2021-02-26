package fr.ensta.launchPattern;

import fr.ensta.client.User;

import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        User myUser = new User("username", "1234", 6666);
        String myS = null;
        Scanner aSC = new Scanner( System.in );

        if ( myUser.connexionServeur() ) {
            while(!aSC.equals("logout")) {
                System.out.println(" Saisir un nouveau message !");
                myS = aSC.nextLine();

                myUser.envoyerMessage( myS );
            }

            aSC.close();
            myUser.deconnexionServeur();
        }

    }

}

