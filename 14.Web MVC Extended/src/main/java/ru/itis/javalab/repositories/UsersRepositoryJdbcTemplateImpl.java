package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.javalab.models.User;

import java.util.*;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {


    //language=SQL
    private static final String SQL_FIND_ONE_BY_AGE = "select * from student where age = :age limit 1";
    private static final String SQL_INSERT_USER = "insert into student (first_name, last_name) values (:first_name, :last_name)";
    private static final String SQL_SELECT = "select * from student";
    private static final String SQL_SELECT_BY_ID = "select * from student where id = :id limit 1";
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from student order by id limit :limit offset :offset;";

    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .first_name(row.getString("first_name"))
            .last_name(row.getString("last_name"))
            .isDeleted(row.getBoolean("is_deleted"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<User> findFirstByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public Optional<User> findUserByAge(int age) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_AGE, Collections.singletonMap("age", age), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return jdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public void save(User entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", entity.getFirst_name());
        params.put("last_name", entity.getLast_name());
        jdbcTemplate.update(SQL_INSERT_USER, params);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, Collections.singletonMap("id", id), userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }
}
