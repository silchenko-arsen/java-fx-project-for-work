package com.example.javafxprojectforwork.service.impl;

import com.example.javafxprojectforwork.dao.UserDao;
import com.example.javafxprojectforwork.dao.impl.UserDaoImpl;
import com.example.javafxprojectforwork.model.User;
import com.example.javafxprojectforwork.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final String REGEX_FOR_NAME = "[a-zA-Z]+";
    private static final String REGEX_FOR_PASSWORD = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
    private static final String ERROR_IF_WRONG_NAME = "Name is wrong (empty, doesn't start from upper case or has something besides letter). Please, input right name";
    private static final String ERROR_IF_WRONG_PASSWORD = "Long of password is less 8 or you didn't input anything special character. Please, right password";
    private static final String ERROR_IF_EMPTY_DATABASE = "Database is empty. Please, save an user.";
    private static final String SUCCESS_CREATE = "User saved to database.";
    private static final int CHECKER_LENGTH_PASSWORD = 8;
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public String create(String name, String password) {
        if (!name.matches(REGEX_FOR_NAME) || !Character.isUpperCase(name.charAt(0))) {
            return ERROR_IF_WRONG_NAME;
        } else if (password.length() < CHECKER_LENGTH_PASSWORD && !password.matches(REGEX_FOR_PASSWORD)) {
            return ERROR_IF_WRONG_PASSWORD;
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            userDao.create(user);
            return SUCCESS_CREATE;
        }
    }

    @Override
    public String getRandom() {
        Optional<User> result = userDao.getRandom();
        if (result.isEmpty()) {
            return ERROR_IF_EMPTY_DATABASE;
        }
        return result.get().toString();
    }
}
