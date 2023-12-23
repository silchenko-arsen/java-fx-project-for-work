package com.example.javafxprojectforwork.javafx;

import com.example.javafxprojectforwork.service.UserService;
import com.example.javafxprojectforwork.service.impl.UserServiceImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserManagementWindow extends VBox {
    private static final String SAVE_USER_BUTTON = new String("Save User");
    private static final String RANDOM_USER_BUTTON = new String("Random User");
    private static final String CONTAINS_RESULT = new String("Please");
    private static final String NAME_LABEL = new String("Name:");
    private static final String PASSWORD_LABEL = new String("Password:");
    private static final int NUMBER_FOR_SPACING_AND_PADDING = 10;
    private final UserService userService;

    public UserManagementWindow() {
        TextField nameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button saveButton = new Button(SAVE_USER_BUTTON);
        Button randomUserButton = new Button(RANDOM_USER_BUTTON);
        Text resultText = new Text();
        userService = new UserServiceImpl();
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String password = passwordField.getText();
            String result  = userService.create(name, password);
            if (result.contains(CONTAINS_RESULT)) {
                showAlert(result);
            } else {
                nameField.clear();
                passwordField.clear();
                resultText.setText(result);
            }
        });

        randomUserButton.setOnAction(e -> {
            String result = userService.getRandom();
            if (result.contains(CONTAINS_RESULT)) {
                showAlert(result);
            } else {
                resultText.setText(result);
            }
        });

        getChildren().addAll(
                new Label(NAME_LABEL),
                nameField,
                new Label(PASSWORD_LABEL),
                passwordField,
                saveButton,
                randomUserButton,
                resultText
        );
        setSpacing(NUMBER_FOR_SPACING_AND_PADDING);
        setPadding(new Insets(NUMBER_FOR_SPACING_AND_PADDING));
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

