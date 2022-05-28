package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws SQLException {

            HikariDataSource source = new HikariDataSource();
            source.setJdbcUrl("jdbc:postgresql://localhost:5432/new_chat");
            source.setUsername("postgres");
            source.setPassword("Lundina02");

            MessagesRepository repository = new MessagesRepositoryJdbcImpl(source);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a message ID");
            System.out.print("-> ");
            Long msgId = scanner.nextLong();
            System.out.print("Message : ");
            System.out.println(repository.findById(msgId).get());
    }
}