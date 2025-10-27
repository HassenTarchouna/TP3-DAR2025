package ClientPackage;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connecté au serveur " + host + ":" + port);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Serveur: " + line);
            }

            System.out.println("Déconnecté du serveur.");

        } catch (IOException e) {
            System.err.println("Erreur côté client : " + e.getMessage());
        }
    }
}
