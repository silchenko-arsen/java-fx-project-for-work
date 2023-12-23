package com.example.javafxprojectforwork.util;

import com.example.javafxprojectforwork.exception.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionUtil {
    private static final String PROPERTIES_FILE = "application.properties";
    private static final String URL = "database.url";
    private static final String USERNAME = "database.username";
    private static final String PASSWORD = "database.password";
    private static final Properties PROPERTIES = loadProperties();

    private ConnectionUtil() {}

    public static Connection getConnection() throws SQLException {
        String url = PROPERTIES.getProperty(URL);
        String username = PROPERTIES.getProperty(USERNAME);
        String password = PROPERTIES.getProperty(PASSWORD);
        return DriverManager.getConnection(url, username, password);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConnectionUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            throw new FileNotFoundException("Sorry, unable to find " + PROPERTIES_FILE, e);
        }
        return properties;
    }
}

