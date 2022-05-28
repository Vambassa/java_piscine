package edu.school21.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket = new Socket();

    public void connect(int port) {

        try {
            socket.connect(new InetSocketAddress(port));
            start();
        } catch (IOException e) {
            System.out.println("Can't connect to server");
            System.exit(1);
        }
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String input, output;
            while ((input = in.readLine()) != null) {

                System.out.println(input);
                if (input.equals("Successful!")) {
                    close();
                    System.exit(0);
                }
                if (input.startsWith("Failed")) {
                    close();
                    System.exit(1);
                }
                output = scanner.nextLine();
                out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {}
    }
}
