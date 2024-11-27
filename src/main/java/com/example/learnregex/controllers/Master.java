package com.example.learnregex.controllers;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.* ;



public class Master {
private static final Logger log = LoggerFactory.getLogger(Master.class);

    /**
     * @finder find a match pattern and words in inputs
     * @param userInput
     * @param wordToFind
     * @return
     */
    public static String finder(String userInput, String wordToFind){
        List<String> matches = new ArrayList<>();
        try{
            Matcher mt = Pattern.compile(wordToFind).matcher(userInput);
            while (mt.find()) {
                matches.add(mt.group());
            }
            return String.join("\n", matches);
        } catch (Exception e) {
            log.error("Error occurred while finding matches: " + e.getMessage());
        }
        return "No match found, try other word(s)";
    }

    /**
     * @patternOperation highlight a match pattern and words in inputs
     * @param userInput
     * @param wordToFind
     * @return
     */
    public static String patternOperation(String userInput, String wordToFind){
        StringBuilder highlightedText = new StringBuilder();
        Matcher mt = Pattern.compile(wordToFind).matcher(userInput);
        int lastEnd = 0;

        while (mt.find()) {
            highlightedText.append(userInput, lastEnd, mt.start());
            highlightedText.append("**")
                    .append(mt.group())
                    .append("**");
            lastEnd = mt.end();
        }

        highlightedText.append(userInput.substring(lastEnd));
        return highlightedText.toString();
    }

    /**
     * @countMatchesOperation does the counting of match words or patterns
     * @param userInput
     * @param wordToFind
     * @return
     */
    public static int countMatchesOperation(String userInput, String wordToFind){
    Matcher mt = Pattern.compile(wordToFind).matcher(userInput);
    int count = 0;
    while (mt.find()) {
        count++;
    }
    return count;
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
            log.error("An error occurred while replacing words: " + e.getMessage());
            ShowAlert.showAlert("Error", "Something went wrong, please try again",e.getMessage(), Alert.AlertType.ERROR);
        }
        return "No match found, try other word(s)";
    }



}
