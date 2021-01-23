package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.front.Front;
import ru.itis.front.FrontImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.MailsService;
import ru.itis.services.MailsServiceImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;


public class Application {
    public static void main(String[] args) {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/Users");
//        hikariConfig.setDriverClassName("org.postgresql.Driver");
//        hikariConfig.setUsername("postgres");
//        hikariConfig.setPassword("Timur007");
//        hikariConfig.setMaximumPoolSize(20);
//        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
//
//        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(hikariDataSource);
//        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(namedParameterJdbcTemplate);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        MailsService mailsService = new MailsServiceImpl();
//        UsersService usersService = new UsersServiceImpl(usersRepository, passwordEncoder, mailsService);
//        Front front = new FrontImpl(usersService);
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Front front = context.getBean("front", Front.class);
        front.run();
    }
}
