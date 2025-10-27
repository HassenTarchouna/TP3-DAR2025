package ServerPackage;
import java.io.*;
import java.net.*;
class ClientHandler implements Runnable {
    private Socket socket;
    private int clientId;

    public ClientHandler(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Send client number
            out.println("Welcome! You are client #" + clientId);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("quit")) {
                    out.println("Goodbye!");
                    break;
                }

                // Compute the operation using your compute() method
                String result = compute(inputLine);
                out.println("Result: " + result);
            }

        } catch (IOException e) {
            System.err.println("Error with client #" + clientId + ": " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
            System.out.println("Client #" + clientId + " disconnected.");
        }
    }

    private String compute(String operation) {
        try {
            String[] parts = operation.trim().split(" ");
            if (parts.length != 3) return "Invalid operation format.";

            double a = Double.parseDouble(parts[0]);
            double b = Double.parseDouble(parts[2]);
            String op = parts[1];

            switch (op) {
                case "+": return String.valueOf(a + b);
                case "-": return String.valueOf(a - b);
                case "*": return String.valueOf(a * b);
                case "/": return b != 0 ? String.valueOf(a / b) : "Division by zero!";
                default: return "Unknown operator!";
            }
        } catch (Exception e) {
            return "Error computing operation.";
        }
    }
}
