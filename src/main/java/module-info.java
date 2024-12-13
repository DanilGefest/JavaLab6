module org.example.lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab6 to javafx.fxml;
    exports org.example.lab6;
    exports org.example.lab6.geometry2d;
    opens org.example.lab6.geometry2d to javafx.fxml;
}