package fr.ensta.serveurMessagerie;

import fr.ensta.client.IUser;

public interface IEnvoieMessage {
    public int envoyer(String message, IUser user);
}
