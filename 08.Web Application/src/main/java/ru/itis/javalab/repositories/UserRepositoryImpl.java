package ru.itis.javalab.repositories;


import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    private static final String SQL_FIND_ALL = "select * from students";
    private static final String SQL_FIND_ALL_BY_AGE = "select * from students where age = ?";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = template.query(SQL_FIND_ALL, userRowMapper);

        return users;
    }

    @Override
    public List<User> findAllByAge(int age) {
        List<User> users = template.query(SQL_FIND_ALL_BY_AGE, userRowMapper, age);
        return users;
    }
}