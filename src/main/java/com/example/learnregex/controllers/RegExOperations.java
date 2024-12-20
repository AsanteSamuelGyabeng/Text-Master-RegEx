package com.example.learnregex.controllers;

import com.example.learnregex.dataManagement.Bookmark;
import com.example.learnregex.service.FileManager;

import com.example.learnregex.service.NotesDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;


public class RegExOperations extends Master {
    Logger log = LoggerFactory.getLogger(RegExOperations.class);

    @FXML
    private TextField textField, patternField, replaceTextfield, findInput, replaceInput, regexSearch;

    @FXML
    private Button processBtn, replaceButton, findBtn, importBtn, pdfBtn, docsBtn, helpBtn, patternBtn,applyChanges,refreshBtn;

    @FXML
    private TextArea resultArea, userInput;

    @FXML
    private Label wordCountLabel;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private ListView<String> bookmarkArea;

    @FXML
    private void initialize() {
        choice.getItems().addAll(
                "Extract Matches",
                "Count Matches"
        );
        choice.setValue("Extract Matches");
    }

    Map<String, String> bookmarks = new HashMap<>();


    @FXML
    private void analyzeRegex() {
        String action = choice.getValue();
        try {
            switch (action) {
                case "Extract Matches":
                    findOperation(new ActionEvent());
                    break;
                case "Count Matches":
                    countMatches();
                    break;
            }
        } catch (Exception e) {
            resultArea.setText("Invalid Selection " + e.getMessage());
        }
    }


    /**
     * @readFromFile - calls the service class FileManager to Read the contents of a file and sets it to the userInput text area
     */
    @FXML
    private void readFromFile() {
        String content = FileManager.openFileAndRead((Stage) userInput.getScene().getWindow());
        if (content != null) {
            userInput.setText(content);
        }
    }


    /**
     * @highlightMatches - calls the service class FileManager to save the contents of the result area to a file
     */
    @FXML
    private void highlightMatches() {
        if (!userInput.getText().isEmpty() && !regexSearch.getText().isEmpty()) {
            String result = patternOperation(userInput.getText(), regexSearch.getText());
            resultArea.setText(result);
        } else {
            ShowAlert.showAlertError();
        }
    }


    /**
     * @countMatches - count the number of matches in the given text
     */
    private void countMatches() {
        if (!userInput.getText().isEmpty() && !findInput.getText().isEmpty()) {
            int result = countMatchesOperation(userInput.getText(), findInput.getText());
            resultArea.setText("Total matches found: " + String.valueOf(result));
        } else {
            ShowAlert.showAlertError();
        }
    }

    /**
     * @handleApplyChanges - calls the service class FileManager to save the contents of the result area to a file
     */
    @FXML
    public void handleApplyChanges() {
        if (!resultArea.getText().isEmpty()) {
            userInput.setText(resultArea.getText());
            resultArea.clear();
        } else {
            ShowAlert.showAlertError();
        }
    }

    /**
     * @param event
     * @findOperation - uses finder method from Master class to find matches
     */
    @FXML
    public void findOperation(ActionEvent event) {
        if (!userInput.getText().isEmpty() && !findInput.getText().isEmpty()) {
            String result = Master.finder(userInput.getText(), findInput.getText());
            resultArea.setText(result);
        } else {
            ShowAlert.showAlertError();
        }
    }



    /**
     * @param event
     * @replaceOperation calls the @Master class and pass the params to perform the replace operation
     */
    @FXML
    public void replaceOperation(ActionEvent event) {
        if (!userInput.getText().isEmpty() && !findInput.getText().isEmpty() && !replaceInput.getText().isEmpty()) {
            String result = Master.replaceWords(userInput.getText(), findInput.getText(), replaceInput.getText());
            resultArea.setText(result);
        } else {
            ShowAlert.showAlertError();
        }
    }

    /**
     * @handleExportToPdf - calls the FileManager class to export the result to a pdf file
     */
    @FXML
    public void handleExportToPdf() {
        String content = resultArea.getText();
        Stage stage = (Stage) resultArea.getScene().getWindow();
        FileManager.handleExportToPdf(content, stage);
    }

    /**
     * @handleBookmark - calls the FileManager class to export the result to a bookmark file
     */
    @FXML
    private void handleBookmark() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Provide a title");
        dialog.setContentText("Please enter a Title here:");

        String input = dialog.showAndWait().orElse(null);
        if (input == null || input.isEmpty()) {
            System.out.println("No title provided.");
            return;
        }

        ObservableList<String> items = FXCollections.observableArrayList();
        bookmarks = Bookmark.addBookmark(input, resultArea.getText());
        for (Map.Entry<String, String> entry : bookmarks.entrySet()) {
            items.add(entry.getKey());
        }
        bookmarkArea.setItems(items);
        bookmarkArea.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleBookmarkSelection(newValue);
            } else {
                System.out.println("No item selected.");
            }
        });
    }

    /**
     * @handleBookmarkSelection method handle the selected bookmark from the listview
     * @param selectedKey
     */
    private void handleBookmarkSelection(String selectedKey) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bookmark Options");
        alert.setHeaderText("Manage Bookmark");
        alert.setContentText("Choose an option for the selected bookmark:");
        ButtonType editButton = new ButtonType("Edit");
        ButtonType deleteButton = new ButtonType("Delete");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(editButton, deleteButton, cancelButton);
        alert.showAndWait().ifPresent(choice -> {
            if (choice == editButton) {
                editBookmark(selectedKey);
            } else if (choice == deleteButton) {
                deleteBookmark(selectedKey);
            }
        });
    }

    /**
     * @editBookmark method edit the bookmark
     * @param selectedKey
     */
    private void editBookmark(String selectedKey) {
        Dialog<String> editDialog = new Dialog<>();
        editDialog.setTitle("Edit Bookmark");
        editDialog.setHeaderText("Update Bookmark Value");

        TextArea textArea = new TextArea(bookmarks.get(selectedKey));
        textArea.setWrapText(true);

        VBox content = new VBox();
        content.setSpacing(10);
        content.getChildren().add(new Label("Enter the new value:"));
        content.getChildren().add(textArea);
        editDialog.getDialogPane().setContent(content);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        editDialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);
        editDialog.setResultConverter(buttonType -> {
            if (buttonType == okButtonType) {
                return textArea.getText();
            }
            return null;
        });
        String newValue = editDialog.showAndWait().orElse(null);
        if (newValue == null || newValue.isEmpty()) {
           log.error("No value entered for editing.");
            return;
        }
        bookmarks.put(selectedKey, newValue);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bookmark Updated");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Bookmark '%s' updated to:\n%s", selectedKey, newValue));
        alert.showAndWait();
    }


    /**
     * @deleteBookmark method delete the bookmark
     * @param selectedKey
     */
    private void deleteBookmark(String selectedKey) {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Confirm Deletion");
        confirmDelete.setHeaderText("Delete Bookmark");
        confirmDelete.setContentText("Are you sure you want to delete this bookmark?");
        confirmDelete.showAndWait().ifPresent(choice -> {
            if (choice == ButtonType.OK) {
                bookmarks.remove(selectedKey);
                ShowAlert.showAlertSuccess("deleted successfuly");
                ObservableList<String> items = FXCollections.observableArrayList(bookmarks.keySet());
                bookmarkArea.setItems(items);
            }
        });

    }

    /**
     * @handleRefresh method clear all the fields
     */
    @FXML
    private void handleRefresh(){
        userInput.clear();
        resultArea.clear();
        findInput.clear();
        replaceInput.clear();
    }


    @FXML
    private void quickReference(){
        NotesDialog.displayNotesDialog();
    }

}