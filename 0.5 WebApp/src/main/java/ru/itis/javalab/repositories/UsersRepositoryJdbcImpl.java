package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String SQL_SELECT_BY_AGE = "select * from student where age = ?";
    private static final String SQL_SELECT = "select * from student";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        List<User> users = template.query(SQL_SELECT_BY_AGE, userRowMapper, age);
        return users;
    }

    @Override
    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = template.query(SQL_SELECT, userRowMapper);

        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
