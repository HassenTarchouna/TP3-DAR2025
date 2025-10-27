package ServerPackage;


import java.io.*;
import java.net.*;

public class Server {
    private static int clientCount = 0;

    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // accepte une connexion
                clientCount++;

                System.out.println("Nouveau client connecté : " +
                        clientSocket.getRemoteSocketAddress() +
                        " | Client n°" + clientCount);

                // Crée un thread pour gérer ce client
                new Thread(new ClientHandler(clientSocket, clientCount)).start();
            }
        } catch (IOException e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
        }
    }
}


