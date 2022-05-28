package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Arrays;
import java.util.List;


public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Orange", 70),
            new Product(1L, "Biscuit", 200),
            new Product(2L, "Bread", 50),
            new Product(3L, "Milk", 80),
            new Product(4L, "Juice", 100));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "Biscuit", 200);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Cucumber", 100);
    final Product EXPECTED_SAVE_PRODUCT = new Product(5L, "Tomato", 99);
    final List<Product> EXPECTED_DELETE_PRODUCTS = Arrays.asList(
            new Product(0L, "Orange", 70),
            new Product(2L, "Bread", 50),
            new Product(3L, "Milk", 80),
            new Product(4L, "Juice", 100));

    private EmbeddedDatabase source;
    private ProductsRepository productsRepository;

    @BeforeEach
    void init() {
        source = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();

        productsRepository = new ProductsRepositoryJdbcImpl(source);
    }

    @Test
    void checkFindAll() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void checkFindById(){
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(1L).get());
    }

    @Test
    void checkUpdate() {
        productsRepository.update(new Product(1L, "Cucumber", 100));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(1L).get());
    }

    @Test
    void checkSave() {
        productsRepository.save(new Product(5L, "Tomato", 99));
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, productsRepository.findById(5L).get());
    }

    @Test
    void checkDelete() {
        productsRepository.delete(1L);
        Assertions.assertEquals(EXPECTED_DELETE_PRODUCTS, productsRepository.findAll());
    }

    @AfterEach
    void sourceClose() {
        source.shutdown();
    }
}