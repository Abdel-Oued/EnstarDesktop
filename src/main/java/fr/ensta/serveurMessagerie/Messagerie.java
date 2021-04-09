package fr.ensta.serveurMessagerie;

import fr.ensta.servPattern.IContext;
import fr.ensta.servPattern.ProcessusEchange;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Observable;

import java.util.ArrayList;
import java.util.Scanner;

public class Messagerie extends Observable implements IMessagerie, IContext {

    private ArrayList<String> connectedUsers = new ArrayList<>();
    private ArrayList<String> allUsers = new ArrayList<>();


    @Override
    public void envoyerMessage(String message, String destinataire, String emetteur, ProcessusEchange pe) {
        PrintStream destinataireOS  = null;
        try {
            destinataireOS = new PrintStream(pe.getClientSocket().getOutputStream());
            destinataireOS.println("messageSimple#[" + emetteur + "] "+message);
            destinataireOS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ajoute l'utilisateur à la liste des utilisateurs en ligne
     * @param user
     */
    public void addConnectedUser(String user) {
        connectedUsers.add(user);

        this.setChanged();
        this.notifyObservers();
    }

    /**
     * supprime l'utilisateur de la liste des utilisateurs en ligne
     * @param user
     */
    public void removeConnectedUser(String user) {
        connectedUsers.remove(user);

        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<String> getConnectedUsers() {
        return connectedUsers;
    }

    @Override
    public ArrayList<String> getAllUsers() {
        allUsers = new ArrayList<>();
        try {
            File myObj = new File("src/main/resources/fr.ensta.serveurMessagerie/utilisateurs.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allUsers.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("[Messagerie] An error occurred.");
            e.printStackTrace();
        }
        return allUsers;
    }

    public boolean addUser(String username, String password) {
        try {
            //test if exist***
            FileWriter myWriter = new FileWriter("src/main/resources/fr.ensta.serveurMessagerie/utilisateurs.txt",true);
            myWriter.write(username+";"+password+'\n');
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;

    }

    public boolean removeUser(String username, String password) throws IOException {
        File inputFile = new File("src/main/resources/fr.ensta.serveurMessagerie/utilisateurs.txt");
        File tempFile = new File("src/main/resources/fr.ensta.serveurMessagerie/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = username+";"+password;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        FileChannel src = new FileInputStream(tempFile).getChannel();
        FileChannel dest = new FileOutputStream(inputFile).getChannel();
        dest.transferFrom(src, 0, src.size());

        return true;
    }

    public boolean identify(String username, String password)
    {
        String line;
        BufferedReader reader = null;

        try
        {
            File file = new File("src/main/resources/fr.ensta.serveurMessagerie/utilisateurs.txt");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            //reader = new BufferedReader(new FileReader("utilisateurs.txt"));
        }
        catch(FileNotFoundException | UnsupportedEncodingException exc)
        {
            System.out.println("Erreur d'ouverture du fichier utilisateurs.txt");
        }

        while (true)
        {
            assert reader != null;
            try {
                if ((line = reader.readLine()) == null) break;
                String[] chaines = line.split(";");

                if((chaines[0].equals(username)) && (chaines[1].equals(password))) {
                    reader.close();
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
