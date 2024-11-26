module com.example.learnregex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires layout;
    requires kernel;
    requires org.slf4j;


    opens com.example.learnregex to javafx.fxml;
    exports com.example.learnregex;
    opens com.example.learnregex.controllers to javafx.fxml;
    opens com.example.learnregex.service to javafx.fxml;
 exports com.example.learnregex.controllers;
 exports com.example.learnregex.service;
}