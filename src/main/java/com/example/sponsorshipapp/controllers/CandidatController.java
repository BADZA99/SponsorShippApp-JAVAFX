
package com.example.sponsorshipapp.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CandidatController {

    @FXML
    private BorderPane bp;

    @FXML
    void ToActions(MouseEvent event) {
        loadPage("listeParrains");

    }

    @FXML
    void ToHome(MouseEvent event) {
        loadPage("listeParrains");
    }

    @FXML
    void ToAddCandidat(MouseEvent event) {
        loadPage("nbParrains");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/pages/candidat/" + page + ".fxml"));
            // root = FXMLLoader.load(getClass().getResource("actions.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load page: " + page);
            System.out.println("Error: " + e.getMessage());
        }
        bp.setCenter(root);
    }

}
