module com.example.javafxprojectforwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxprojectforwork to javafx.fxml;
    exports com.example.javafxprojectforwork;
}
