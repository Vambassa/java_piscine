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

    @Override
    public void save(Message message) {

        if (message.getAuthor().getId() == null || message.getRoom().getId() == null) {
            throw new NotSavedSubEntityException("Something is null");
        }

        try {
            Connection connection = source.getConnection();
            String query = "INSERT INTO messages (author_id, room_id, text, time) VALUES (?, ?, ?, ?) RETURNING id;";
            PreparedStatement prepQuery = connection.prepareStatement(query);
            prepQuery.setLong(1, message.getAuthor().getId());
            prepQuery.setLong(2, message.getRoom().getId());
            prepQuery.setString(3, message.getText());
            prepQuery.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));

            ResultSet result = prepQuery.executeQuery();
            result.next();

            message.setId(result.getLong(1));
            prepQuery.close();
            connection.close();
        } catch (SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }
}
