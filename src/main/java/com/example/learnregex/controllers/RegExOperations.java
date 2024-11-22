package com.example.learnregex.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExOperations extends Master{

    @FXML
    private TextField textField, patternField, replaceTextfield, findInput,replaceInput;
    @FXML
    private Button processBtn, replaceButton,findBtn,importBtn,pdfBtn,docsBtn,helpBtn,patternBtn;
    @FXML
    private TextArea resultArea,userInput;
    @FXML
    private Label wordCountLabel;


    @FXML
    public void findOperation(ActionEvent event) {
        if (!userInput.getText().isEmpty() && !findInput.getText().isEmpty()){
            String result = Master.finder(userInput.getText(),findInput.getText());
            resultArea.setText(result);
        }else{
            ShowAlert.showAlert(
                    "Error", "Please fill all the fields",
                    "No input given!!! Please enter the fields and continue! ",
                    Alert.AlertType.ERROR
            );
        }


    }

    @FXML
    public void replaceOperation(ActionEvent event) {
        if (!userInput.getText().isEmpty() && !findInput.getText().isEmpty() && !replaceInput.getText().isEmpty()){
        String result = Master.replaceWords(userInput.getText(),findInput.getText(),replaceInput.getText());
        resultArea.setText(result);
        }else{
            ShowAlert.showAlert(
                    "Error",
                    "Please fill all the fields",
                    "No input given!!! Please enter the fields and continue! ",
                    Alert.AlertType.ERROR);
        }
    }


}
