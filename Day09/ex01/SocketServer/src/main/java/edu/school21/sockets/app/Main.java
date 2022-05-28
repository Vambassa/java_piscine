package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--port=")) {
            System.out.println("Invalid argument");
            return;
        }

        int port = 0;
        try {
            port = Integer.parseInt(args[0].substring("--port=".length()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument");
            return;
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

        Server server = context.getBean("server", Server.class);
        if (server.listen(port)) {
            System.out.println("Server has started successfully");
            server.run();
        }
        System.out.println("Failed to start Server");
    }
}
