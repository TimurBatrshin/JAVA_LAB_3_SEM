package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryJdbcImpl;
import ru.itis.repositories.UserRepositoryJdbcTemplateImpl;
import ru.itis.utils.PasswordCheckerUtil;
import ru.itis.utils.PasswordCheckerUtilImpl;
@Configuration
public class AppConfiguration {


    @Bean
    public PasswordCheckerUtil passwordChecker() {
        return new PasswordCheckerUtilImpl();
    }
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryJdbcTemplateImpl();
    }
}
