module com.roguelike_java {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.roguelike_java to javafx.fxml;
    exports com.roguelike_java;
}
