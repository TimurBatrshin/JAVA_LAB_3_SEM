package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.models.User;;

import javax.sql.DataSource;
import java.util.*;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ONE_BY_EMAIL = " select * from USERS where email = :email limit 1";

    //language=SQL
    private static final String SQL_INSERT = "insert into users(firstName, lastName, email, phone, password) values" +
            "(:firstName, :lastName, :email, :phone, :hashPassword)";

//    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//            this.jdbcTemplate = template;
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .email(row.getString("email"))
                    .phone(row.getString("phone"))
                    .hashPassword(row.getString("hash_password"))
                    .build();


    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_FIND_ONE_BY_EMAIL,Collections.singletonMap("email", email),userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", entity.getFirstName());
        params.put("lastName", entity.getLastName());
        params.put("email", entity.getEmail());
        params.put("phone", entity.getPhone());
        params.put("hashPassword", entity.getHashPassword());
        namedParameterJdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
