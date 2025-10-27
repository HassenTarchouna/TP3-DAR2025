package ServerPackage;
import java.io.*;
import java.net.*;
class ClientHandler implements Runnable {
    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Bienvenue ! Vous êtes le client n°" + clientNumber);

            // Le serveur ne fait rien d'autre ici — il attend juste la déconnexion du client
            Thread.sleep(2000); // simulation d'une connexion pendant 2sec

            out.println("Déconnexion du serveur...");
            socket.close();

            System.out.println("Client n°" + clientNumber + " déconnecté.");
        } catch (Exception e) {
            System.err.println("Erreur avec le client n°" + clientNumber + " : " + e.getMessage());
        }
    }
}