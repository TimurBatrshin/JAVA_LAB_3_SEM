//package ru.itis.javalab.repositories;
//
//import ru.itis.javalab.models.User;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class UsersRepositoryJdbcImpl implements UsersRepository {
//
//    private static final String SQL_SELECT_BY_AGE = "select * from student where age = ?";
//    private static final String SQL_SELECT = "select * from student";
//    private static final String SQL_UPDATE_USER = "update students set first_name = ?, last_name = ?, age = ?, is_deleted = ?";
//    private static final String SQL_FIND_BY_ONE = "select * from students where id = ?";
//
//    private DataSource dataSource;
//    private SimpleJdbcTemplate template;
//
//    public UsersRepositoryJdbcImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    private RowMapper<User> userRowMapper = row -> User.builder()
//            .id(row.getLong("id"))
//            .firstName(row.getString("first_name"))
//            .lastName(row.getString("last_name"))
//            .age(row.getInt("age"))
//            .isDeleted(row.getBoolean("is_deleted"))
//            .build();
//
//    @Override
//    public List<User> findAllByAge(Integer age) {
//        List<User> users = template.query(SQL_SELECT_BY_AGE, userRowMapper, age);
//        return users;
//    }
//
//    @Override
//    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> findOneById(Long id) {
//       // User users = template.query(SQL_FIND_BY_ONE, userRowMapper, id);
//        return null;
//    }
//
//    @Override
//    public List<User> updateUser(Long id) {
//        List<User> users = template.query(SQL_UPDATE_USER, userRowMapper, id);
//
//        return users;
//    }
//
//    @Override
//    public List<User> findAll() {
//        List<User> users = template.query(SQL_SELECT, userRowMapper);
//
//        return users;
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(User entity) {
//
//    }
//
//    @Override
//    public void update(User entity) {
//        PreparedStatement statement = null;
//        try {
//            Connection connection = dataSource.getConnection();
//            statement = connection.prepareStatement(SQL_UPDATE_USER);
//            statement.setString(1, entity.getFirstName());
//            statement.setString(2, entity.getLastName());
//            statement.setInt(3, entity.getAge());
//            statement.setBoolean(4, entity.getIsDeleted());
//            statement.setLong(5, entity.getId());
//
//            int affectedRows = statement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//
//                }
//            }
//
//        }
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void delete(User entity) {
//
//    }
//}
