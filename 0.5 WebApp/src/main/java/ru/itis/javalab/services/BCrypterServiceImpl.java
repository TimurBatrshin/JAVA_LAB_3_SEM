package ru.itis.javalab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCrypterServiceImpl implements BCrypterService {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean checkPassword(String pass, String PRPass) {
        if (pass.equals(PRPass)) {
            return true;
        } else return false;
    }
}
