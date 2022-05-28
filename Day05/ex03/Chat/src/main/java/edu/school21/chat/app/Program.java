package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) throws SQLException {

        HikariDataSource source = new HikariDataSource();
        source.setJdbcUrl("jdbc:postgresql://localhost:5432/new_chat");
        source.setUsername("postgres");
        source.setPassword("Lundina02");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(source);

        Message msg = repository.findById(1L).get();
        msg.setText("oooo my new message");
        repository.update(msg);
        System.out.println(repository.findById(1L).get());
    }
}