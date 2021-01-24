package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.repositories.UserRepository;
import ru.itis.services.UserService;



public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
        int i = 0;
        //        UserService userService = applicationContext.getBean(UserService.class);
    }
}
