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

    private static final String SQL_SELECT_BY_AGE = "select * from student where id = ?";
    private static final String SQL_SELECT = "select * from student";

    private DataSource dataSource;

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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_BY_AGE);
            statement.setInt(1, age);
            resultSet = statement.executeQuery();

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    @Override
    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT);
            resultSet = statement.executeQuery();

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
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
