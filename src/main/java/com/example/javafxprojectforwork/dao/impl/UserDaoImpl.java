package com.example.javafxprojectforwork.dao.impl;

import com.example.javafxprojectforwork.dao.UserDao;
import com.example.javafxprojectforwork.model.User;
import com.example.javafxprojectforwork.util.ConnectionUtil;
import com.example.javafxprojectforwork.exception.DataProcessingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String QUERY_FOR_INSERT_USER = "INSERT INTO users(name, password) VALUES(?, ?)";
    private static final String QUERY_FOR_GETTING_RANDOM_USER = "SELECT * FROM users ORDER BY RAND() LIMIT 1";

    @Override
    public void create(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FOR_INSERT_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t create product: " + user, e);
        }
    }

    @Override
    public Optional<User> getRandom() {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FOR_GETTING_RANDOM_USER)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Error getting random user", e);
        }
        return Optional.empty();
    }
}
