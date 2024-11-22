package com.example.learnregex.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExOperations extends Master{

    @FXML
    private TextField textField;
    @FXML
    private TextField patternField;
    @FXML
    private Button processBtn;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField replaceTextfield;
    @FXML
    private Button replaceBtn;


    @FXML
    public void basicOperations(ActionEvent event) {
        String result = Master.operator(textField.getText(),patternField.getText());
        resultArea.setText(result);
    }

    @FXML
    public void replaceOperations(ActionEvent event) {
        String input = textField.getText();
        String replaceword =  replaceTextfield.getText();
        String result = input.replaceAll("\\bboy\\b",replaceword);
        System.out.println(result);
    }
}
