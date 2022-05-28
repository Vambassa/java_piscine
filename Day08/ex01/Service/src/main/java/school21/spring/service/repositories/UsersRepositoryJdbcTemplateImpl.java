package school21.spring.service.repositories;

import javax.sql.DataSource;
import school21.spring.service.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource source) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(source);
    }

    @Override
    public User findById(Long id) {

        String query = "SELECT * FROM users WHERE id = :id";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        try {
            return jdbcTemplate.queryForObject(query, paramMap, new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {

        String query = "SELECT * FROM users;";

        try {
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(User entity) {

        String query = "INSERT INTO users VALUES(:id, :email);";
        getParamMap(entity, query);
    }

    @Override
    public void update(User entity) {

        String query = "UPDATE users SET email = :email WHERE id = :id;";
        getParamMap(entity, query);
    }

    private void getParamMap(User entity, String query) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", entity.getId());
        paramMap.put("email", entity.getEmail());

        try {
            this.jdbcTemplate.update(query, paramMap);
        } catch (DataAccessException e) {}
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM users WHERE id = :id;";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        try {
            this.jdbcTemplate.update(query, paramMap);
        } catch (DataAccessException e) {}
    }

    @Override
    public Optional<User> findByEmail(String email) {

        String query = "SELECT * FROM users WHERE email = :email;";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);

        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(query, paramMap, new BeanPropertyRowMapper<>(User.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}