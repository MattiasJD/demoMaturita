module com.example.demomaturita {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demomaturita to javafx.fxml;
    exports com.example.demomaturita;
}