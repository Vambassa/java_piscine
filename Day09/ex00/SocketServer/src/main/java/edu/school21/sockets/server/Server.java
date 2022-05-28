package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
    private final UsersService service;
    private ServerSocket serverSocket;

    @Autowired
    public Server(UsersService service) {
        this.service = service;
    }

    public void connect(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started successfully");
            start();
        } catch (IOException e) {
            System.out.println("Failed to start server");
            System.exit(1);
        }
    }

    public void start() {

        try(Socket client = serverSocket.accept();
            InputStreamReader stream = new InputStreamReader(client.getInputStream());
            BufferedReader in = new BufferedReader(stream);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            out.println("Hello from Server!");

            while (true) {

                String command = in.readLine();
                if (!command.equals("signUp")) {
                    out.println("Unknown command");
                    continue;
                }

                out.println("Enter username:");
                String username = in.readLine();
                while (username.length() == 0) {
                    out.println("Enter username:");
                    username = in.readLine();
                }

                out.println("Enter password:");
                String password = in.readLine();
                while (password.length() == 0) {
                    out.println("Enter password:");
                    password = in.readLine();
                }
                if (service.signUp(username, password)) {
                    out.println("Successful!");
                    System.exit(0);
                } else {
                    out.println("Failed to sign up");
                    System.exit(1);
                }
            }
        } catch (IOException e) {}
    }
}
