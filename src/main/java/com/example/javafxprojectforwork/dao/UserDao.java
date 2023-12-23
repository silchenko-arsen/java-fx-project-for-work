package com.example.javafxprojectforwork.dao;

import com.example.javafxprojectforwork.model.User;

import java.util.Optional;

public interface UserDao {
    void create(User user);
    Optional<User> getRandom();
}
