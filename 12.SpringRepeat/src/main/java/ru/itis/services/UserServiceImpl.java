package ru.itis.services;

import ru.itis.repositories.UserRepository;
import ru.itis.utils.PasswordCheckerUtil;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private PasswordCheckerUtil passwordChecker;

    public UserServiceImpl(UserRepository userRepository, PasswordCheckerUtil passwordChecker) {
        this.userRepository = userRepository;
        this.passwordChecker = passwordChecker;
    }
}
