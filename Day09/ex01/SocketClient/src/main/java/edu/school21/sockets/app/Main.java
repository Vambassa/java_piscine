package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--server-port=")) {
            System.out.println("Invalid argument");
            return;
        }

        int port = 0;
        try {
            port = Integer.parseInt(args[0].substring("--server-port=".length()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument");
            return;
        }

        Client client = new Client();
        if (client.connect(port)) {
            client.run();
        } else {
            System.out.println("Error connecting to server");
        }

    }
}
