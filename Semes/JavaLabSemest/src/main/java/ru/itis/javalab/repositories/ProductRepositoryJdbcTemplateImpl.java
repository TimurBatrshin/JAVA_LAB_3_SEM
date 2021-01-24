package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.javalab.models.Product;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcTemplateImpl implements ProductRepositories{
    private static final String SQL_FIND_ALL = "select * from product";
    private static final String SQL_FIND_ALL_PRODUCT_FROM_CART_USER = "select u.email, p.name from cart_shop join users u on cart_shop.cart_id_user = u.id join product p on cart_shop.product_id = p.id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Product> productRowMapper = (row, i) -> Product.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .cost(row.getInt("cost"))
            .build();

    public ProductRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public List<User> findAll()
    {
        return null;
    }


    @Override
    public List<Product> findAll(int page, int size) {
        return null;
    }

    @Override
    public void save(Product entity) {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProduct() {
        return jdbcTemplate.query(SQL_FIND_ALL, productRowMapper);
    }

    @Override
    public List<Product> findAllProductsFromCartUser() {
        return jdbcTemplate.query(SQL_FIND_ALL_PRODUCT_FROM_CART_USER, productRowMapper);
    }
}
