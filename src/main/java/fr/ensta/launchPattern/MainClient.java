package fr.ensta.launchPattern;

import fr.ensta.client.User;

import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        String myS;
        Scanner aSC = new Scanner( System.in );

        System.out.println(" Saisir votre nom !");
        String username = aSC.nextLine();
        System.out.println(" Saisir votre mot de passe !");
        String password = aSC.nextLine();
        User myUser = new User(username, password, 6666);


        if ( myUser.connexionServeur() ) {
            do {
                System.out.println(" Saisir un nouveau message !");
                myS = aSC.nextLine();

                myUser.envoyerMessage( myS );
            }while(!myS.equals("logout"));

            aSC.close();
            myUser.deconnexionServeur();
        }

    }

}

