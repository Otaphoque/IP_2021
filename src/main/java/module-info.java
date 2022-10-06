module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens truc to javafx.fxml;
    exports truc;
    exports view;
    opens view to javafx.fxml;
}