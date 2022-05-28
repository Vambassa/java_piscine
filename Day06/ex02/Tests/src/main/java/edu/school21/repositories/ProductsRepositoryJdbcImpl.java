package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource source;

    public ProductsRepositoryJdbcImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public List<Product> findAll() {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products;";

        try  {

            Connection connection = source.getConnection();
            PreparedStatement prepQuery = connection.prepareStatement(query);
            ResultSet result = prepQuery.executeQuery();

            while (result.next()) {
                Product product = new Product(result.getLong(1), result.getString(2), result.getInt(3));
                list.add(product);
            }

            prepQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) {

        String query = "SELECT * FROM products WHERE id = ?;";

        try {
            Connection connection = source.getConnection();
            PreparedStatement prepQuery = connection.prepareStatement(query);
            prepQuery.setLong(1, id);

            ResultSet result = prepQuery.executeQuery();
            if (result.next()) {
                Product product = new Product(result.getLong(1), result.getString(2), result.getInt(3));
                prepQuery.close();
                connection.close();
                return Optional.of(product);
            }
            prepQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {

        String query = "UPDATE products SET name = ?, price = ? WHERE id = ?";

        try {
            Connection connection = source.getConnection();
            PreparedStatement prepQuery = connection.prepareStatement(query);
            prepQuery.setString(1, product.getName());
            prepQuery.setInt(2, product.getPrice());
            prepQuery.setLong(3, product.getId());
            prepQuery.executeUpdate();

            prepQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void save(Product product) {

        String query = "INSERT INTO products VALUES (?, ?, ?);";

        try {
            Connection connection = source.getConnection();
            PreparedStatement prepQuery = connection.prepareStatement(query);
            prepQuery.setLong(1, product.getId());
            prepQuery.setString(2, product.getName());
            prepQuery.setInt(3, product.getPrice());
            prepQuery.executeUpdate();

            prepQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM products WHERE id = ?;";

        try {
            Connection connection = source.getConnection();
            PreparedStatement prepQuery = connection.prepareStatement(query);
            prepQuery.setLong(1, id);
            prepQuery.executeUpdate();

            prepQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
