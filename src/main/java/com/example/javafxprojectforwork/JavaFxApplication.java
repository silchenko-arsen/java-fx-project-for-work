package com.example.javafxprojectforwork;

import com.example.javafxprojectforwork.dao.TableCreatorDao;
import com.example.javafxprojectforwork.dao.impl.TableCreatorDaoImpl;
import com.example.javafxprojectforwork.javafx.UserManagementWindow;
import com.example.javafxprojectforwork.service.impl.ReaderImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {
    private static final String TITLE_APPLICATION = "User Management System";
    private static final String FILE_FOR_INIT_TABLE = "src/main/resources/init_table.sql";
    private static final int WIDTH_APPLICATION = 400;
    private static final int HEIGHT_APPLICATION = 300;

    @Override
    public void start(Stage primaryStage) {
        UserManagementWindow userManagementWindow = new UserManagementWindow();
        Scene scene = new Scene(userManagementWindow, WIDTH_APPLICATION, HEIGHT_APPLICATION);
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE_APPLICATION);
        primaryStage.show();
    }

    public static void main(String[] args) {
        ReaderImpl reader = new ReaderImpl();
        String query = reader.read(FILE_FOR_INIT_TABLE);
        TableCreatorDao tableCreatorDao = new TableCreatorDaoImpl();
        tableCreatorDao.createTable(query);
        launch(args);
    }
}
