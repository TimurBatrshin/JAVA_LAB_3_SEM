package ru.itis.javalab.services;

public class BCrypterServiceImpl implements BCrypterService {
    @Override
    public boolean checkPassword(String pass, String dbPass) {
           if (pass.equals(dbPass)) {
                return true;
            } else return false;
        }
    }
