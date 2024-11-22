module com.example.learnregex {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.learnregex to javafx.fxml;
    exports com.example.learnregex;
    opens com.example.learnregex.controllers to javafx.fxml;
 exports com.example.learnregex.controllers;
}