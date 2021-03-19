package fr.ensta.servPattern;

import fr.ensta.client.ClientTCP;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertNotNull;

public class ServeurTCPTest {

        static ServeurTCP aServer;
        static ClientTCP client;
        static Socket soc;
        ServerSocket serverSocket=null;
        static int nbConnexions;

        @DisplayName("hello")
        @Test
        public void testRunServerStarted() {
                System.out.println("before class");
                aServer = new ServeurTCP( 6666 );
                nbConnexions=aServer.getNbConnexions();
                assertNotNull(aServer);
                try {
                        System.out.println("[ServeurTCP] Attente du serveur pour la communication d'un client " );
                        soc = serverSocket.accept();
                        nbConnexions++;
                        aServer.setNbConnexions(nbConnexions);
                        System.out.println("[ServeurTCP] Nb automates : " + nbConnexions);
                } catch (IOException e) {
                        System.out.println("[ServeurTCP] Accept failed: " + serverSocket.getLocalPort() + ", " + e);
                        System.exit(1);
                }

        }


}
