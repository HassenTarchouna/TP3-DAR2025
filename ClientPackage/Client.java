package ClientPackage;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server.");
            System.out.println(in.readLine()); // Welcome message

            String userInput;
            while (true) {
                System.out.print("Enter operation: ");
                userInput = keyboard.readLine();
                out.println(userInput);

                if ("quit".equalsIgnoreCase(userInput)) break;
                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}