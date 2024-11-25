package com.example.learnregex.dataManagement;

import com.example.learnregex.controllers.ShowAlert;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Bookmark {

    static Logger log = LoggerFactory.getLogger(Bookmark.class);

    static final Map<String, String> bookmarks = new HashMap<>();

    public static Map<String, String> addBookmark(String key, String value) {
        try {
            bookmarks.put(key, value);
            ShowAlert.showAlertSuccess("successfuly saved into bookmark");
            return bookmarks;
        } catch (Exception e) {
            log.error("error occured trying to insert into bookmark" + e);
        }
        return null;
    }

}
