package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final HikariDataSource source;

    public MessagesRepositoryJdbcImpl(HikariDataSource source) {
        this.source = source;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {

        Optional<Message> msg;

        Connection connection = source.getConnection();
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM messages WHERE id = " + id);
        result.next();

        User user = new User(1L, "vambassa", "aaa", null, null);
        Chatroom chatroom = new Chatroom(1L, "random", user, null);
        msg = Optional.of(new Message(result.getLong(1), user, chatroom,
                result.getString("text") , LocalDateTime.of(2022, 1, 10, 10, 30)));

        statement.close();
        connection.close();

        return msg;
    }
}
