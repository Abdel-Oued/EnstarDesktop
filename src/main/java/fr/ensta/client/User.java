package fr.ensta.client;

public class User implements IUser{
    private String username;
    private String pwd;


    private ClientTCP monClientTCP;
    //private int port;

    // create simple user
    public User(String username,String pwd, int port){
        this.username=username;
        this.pwd=pwd;
        monClientTCP=new ClientTCP("localhost",port);
    }

    /**
     * renvoie 1 si message reçu par le serveur et 0 sinon
     * */
    public int envoyerMessage(String message){
        // message contient le destinataire à qui envoyer
        String statusString = monClientTCP.transmettreChaine(message);
        //int status = Integer.parseInt(statusString);
        //Integer.parseInt(statusString);
        return 1;
    }

//    public String recevoirMessage(){
//        // message contient le destinataire à qui envoyer
//        return monClientTCP.recevoirChaine();
//    }

    @Override
    public boolean connexionServeur() {
        boolean connected = monClientTCP.connecterAuServeur();

        if (connected) {
            //monClientTCP.attendreMessage();
            RecevoirMessage recevoirMessage = new RecevoirMessage(this);
            recevoirMessage.start();
        }
        return connected;
    }

    @Override
    public void deconnexionServeur() {
        monClientTCP.transmettreChaine("logout");
        monClientTCP.deconnecterDuServeur();
    }

    @Override
    public int getUserID() {
        return 0;
    }

    public ClientTCP getMonClientTCP() {
        return monClientTCP;
    }

}
