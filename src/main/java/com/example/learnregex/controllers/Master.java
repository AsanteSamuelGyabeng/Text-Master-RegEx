package com.example.learnregex.controllers;
import javafx.scene.control.Alert;

import java.util.regex.* ;

public class Master {


    /**
     * @finder find a match pattern and words in inputs
     * @param userInput
     * @param wordToFind
     * @return
     */
    public static String finder(String userInput, String wordToFind){
        try{
            Matcher mt = Pattern.compile(wordToFind).matcher(userInput);
            if (mt.find()) return mt.group() + " is found";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "No match found, try other word(s)";
    }

    /**
     * @param userInput
     * @param replaceInput
     * @param wordToBeReplaced
     * @return
     * @replaceWords method replaces all occurances in the input given
     */
    public static String replaceWords(String userInput, String replaceInput, String wordToBeReplaced) {
        try{
            String finalresult = userInput.replaceAll("\\b"+replaceInput+"\\b",wordToBeReplaced);
            return finalresult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ShowAlert.showAlert("Error", "Something went wrong, please try again",e.getMessage(), Alert.AlertType.ERROR);
        }
        return "No match found, try other word(s)";
    }


}
