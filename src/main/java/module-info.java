module com.softwaremethproject4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.softwaremethproject4 to javafx.fxml;

    exports com.softwaremethproject4;
    exports controller;

    opens controller to javafx.fxml;

    exports controller.panels;

    opens controller.panels to javafx.fxml;
}