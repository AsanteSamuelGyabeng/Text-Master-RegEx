package com.example.learnregex.controllers;

import javafx.scene.control.Alert;

public class ShowAlert {

    /**
     * the method below shows an alert
     * */
    public static void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showAlertError() {
        ShowAlert.showAlert(
             "Error",
             "Please fill all the fields",
             "No input given!!! Please enter the fields and continue! ",
    Alert.AlertType.ERROR);
}

    public static void showAlertSuccess(String message) {
        ShowAlert.showAlert(
             "Success",
             "Export Successful",
             message,
             Alert.AlertType.INFORMATION  );
        }


}
