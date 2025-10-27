package ServerPackage;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final int PORT = 1234;
    private static AtomicInteger clientCount = new AtomicInteger(0);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                int clientId = clientCount.incrementAndGet();
                System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
                System.out.println("Client number: " + clientId);

                // Create a thread for this client
                Thread clientThread = new Thread(new ClientHandler(clientSocket, clientId));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
