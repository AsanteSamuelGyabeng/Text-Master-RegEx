package com.example.learnregex.service;

import javafx.scene.control.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class NotesDialog {



    public static void displayNotesDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Notes/References");
        dialog.setHeaderText("Here are your notes or references:");
        TextArea notesArea = new TextArea();
        notesArea.setWrapText(true);
        notesArea.setEditable(false);
        try {
            Path filePath = Paths.get("src/main/java/com/example/learnregex/service/quickreference.txt");
            if (Files.exists(filePath)) {
                String notesContent = new String(Files.readAllBytes(filePath));
                notesArea.setText(notesContent);
            } else {
                notesArea.setText("References file not found.");
            }
        } catch (IOException e) {
            notesArea.setText("Error loading references: " + e.getMessage());
        }
        dialog.getDialogPane().setContent(notesArea);
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);

        dialog.showAndWait();
    }


}
