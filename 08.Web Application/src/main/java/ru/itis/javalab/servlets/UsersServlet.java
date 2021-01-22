package ru.itis.javalab.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;
import ru.itis.javalab.repositories.UserRepositoryImpl;
import ru.itis.javalab.services.UserService;
import ru.itis.javalab.services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/java_lab_1");
        hikariConfig.setPassword("Timur007");
        hikariConfig.setUsername("postgres");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(20);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        this.userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(userService.getAllUsers());
    }
}
