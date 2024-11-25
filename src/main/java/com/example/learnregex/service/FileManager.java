package com.example.learnregex.service;
import com.example.learnregex.controllers.ShowAlert;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import javafx.stage.FileChooser;
import java.io.*;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FileManager {

    private static final Logger log = LoggerFactory.getLogger(FileManager.class);

    /**
     * @openFileAndRead method allows you to open a file and read its content
     * @param stage
     * @return
     */
    public static String openFileAndRead(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            return readFileContent(selectedFile);
        }
        return null;
    }

    /**
     * @readFileContent method reads the content of the file
     * @param file
     * @return
     */
    private static String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("An error occurred while reading the file.", e);
        }
        return content.toString();
    }

    /**
     * @handleExportToPdf method takes the first step to export by allowing user to name the file and choose the location to save it.
     * @param content
     * @param stage
     */
    public static void handleExportToPdf(String content, Stage stage) {
        if (content.isEmpty()) {
            ShowAlert.showAlertError();
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TextMaster", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            try {
                exportToPdf(content, selectedFile);
            } catch (IOException e) {
                log.error("Error", "An error occurred while exporting to PDF.");
            }
        }
    }

    /**
     * @exportToPdf methods handles the export
     * @param content
     * @param file
     * @throws IOException
     */
    public static void exportToPdf(String content, File file) throws IOException {
        PdfWriter writer = new PdfWriter(String.valueOf(file));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        document.add(new Paragraph(content));
        document.close();
        ShowAlert.showAlertSuccess("Success! PDF exported successfully.");
    }
}

