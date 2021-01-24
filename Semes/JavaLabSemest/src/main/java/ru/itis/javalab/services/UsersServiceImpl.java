package ru.itis.javalab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.itis.javalab.dto.UserDto.from;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public List<UserDto> getAllUser(int page, int size) {
        return from(usersRepository.findAll(page, size));
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(User.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .password(user.getPassword())
                .build());
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return usersRepository.findFirstByEmailAndPassword(email, password);
    }

    @Override
    public Optional<User> findUserByPassword(String password) {
        return usersRepository.findUserByPassword(password);
    }

    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(usersRepository.findById(userId).orElse(null));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(Map pool) {
        User user = User.builder()
                .first_name((String) pool.get("first_name"))
                .last_name((String) pool.get("last_name"))
                .build();
        usersRepository.save(user);

    }
}
