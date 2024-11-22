package com.example.learnregex.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExOperations extends Master{

    @FXML
    private TextField textField, patternField, replaceTextfield, findInput,replaceInput,regexSearch;
    @FXML
    private Button processBtn, replaceButton,findBtn,importBtn,pdfBtn,docsBtn,helpBtn,patternBtn;
    @FXML
    private TextArea resultArea,userInput;
    @FXML
    private Label wordCountLabel;


    /**
     *
     * @param event
     */
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

    /**
     *
     * @param event
     */
    @FXML
    public void regexOperation(ActionEvent event) {
        if (!userInput.getText().isEmpty() && !regexSearch.getText().isEmpty()){
            String result = Master.finder(userInput.getText(),regexSearch.getText());
            resultArea.setText(result);
        }else{
            ShowAlert.showAlert(
                    "Error", "Please fill all the fields",
                    "No input given!!! Please enter the fields and continue! ",
                    Alert.AlertType.ERROR
            );
        }


    }

    /**
     * @replaceOperation calls the @Master class and pass the params to perform the replace operation
     * @param event
     */
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
