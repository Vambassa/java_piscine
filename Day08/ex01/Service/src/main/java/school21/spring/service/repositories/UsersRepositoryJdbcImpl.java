package school21.spring.service.repositories;

import school21.spring.service.models.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource source;

    public UsersRepositoryJdbcImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public User findById(Long id) {

        String query = "SELECT * FROM users WHERE id = ?;";

        try (Connection connection = source.getConnection();
             PreparedStatement prepQuery = connection.prepareStatement(query)) {

            prepQuery.setLong(1, id);
            try (ResultSet result = prepQuery.executeQuery()) {
                if (result.next()) {
                    return new User(result.getLong(1), result.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> findAll() {

        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM users;";

        try (Connection connection = source.getConnection();
             PreparedStatement prepQuery = connection.prepareStatement(query)) {

            ResultSet result = prepQuery.executeQuery();
            while (result.next()) {
                User user = new User(result.getLong(1), result.getString(2));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User entity) {

        String query = "INSERT INTO users VALUES(?, ?);";

        try (Connection connection = source.getConnection();
             PreparedStatement prepQuery = connection.prepareStatement(query)) {

            prepQuery.setLong(1, entity.getId());
            prepQuery.setString(2, entity.getEmail());
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {

        String query = "UPDATE users SET email = ? WHERE id = ?;";

        try (Connection connection = source.getConnection();
             PreparedStatement prepQuery = connection.prepareStatement(query)) {

            prepQuery.setString(1, entity.getEmail());
            prepQuery.setLong(2, entity.getId());
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM users WHERE id = ?;";

        try (Connection connection = source.getConnection();
             PreparedStatement prepQuery = connection.prepareStatement(query)) {

            prepQuery.setLong(1, id);
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {

        String query = "SELECT * FROM users WHERE email = ?;";

        try (Connection connection = source.getConnection();
             PreparedStatement prepQuery = connection.prepareStatement(query)) {

            prepQuery.setString(1, email);
            ResultSet result = prepQuery.executeQuery();

            if (result.next()) {
                return Optional.of(new User(result.getLong(1), result.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
