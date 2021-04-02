package fr.ensta.servPattern;
import java.io.*;

/**
 * Classe qui gere la recherche d'un utilisateur qui tente de se connecter.
 * */
public class Identification {

    public Identification()
    {
    }

    public boolean identify(String username, String password)
    {
        String line;
        BufferedReader reader = null;

        try
        {
            File file = new File("src/main/resources/fr.ensta.servPattern/utilisateurs.txt");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            //reader = new BufferedReader(new FileReader("utilisateurs.txt"));
        }
        catch(FileNotFoundException | UnsupportedEncodingException exc)
        {
            System.out.println("Erreur d'ouverture du fichier utilisateurs.txt");
        }

        while (true)
        {
            //assert reader != null;
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
