package fr.ensta.Message;

import fr.ensta.client.IUser;
import fr.ensta.servPattern.ServeurTCP;

public interface IEnvoieMessage {
    public int envoyer(String message, ServeurTCP serveur, IUser user);
}
