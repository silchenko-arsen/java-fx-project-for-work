package com.example.javafxprojectforwork.dao.impl;

import com.example.javafxprojectforwork.dao.TableCreatorDao;
import com.example.javafxprojectforwork.exception.DataProcessingException;
import com.example.javafxprojectforwork.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreatorDaoImpl implements TableCreatorDao {

    public void createTable(String queryForCreationTable) {
            try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(queryForCreationTable)) {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Can`t create table 'users' ", e);
            }
    }
}

