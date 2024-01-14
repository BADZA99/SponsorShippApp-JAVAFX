
package com.example.sponsorshipapp.controllers;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class AdminController {

    @FXML
    private BorderPane bp;

    @FXML
    void ToActions(MouseEvent event) {
        loadPage("listeUser");

    }
    @FXML
    void ToHome(MouseEvent event) {
        loadPage("home");

    }

    @FXML
    void ToAddCandidat(MouseEvent event) {
        loadPage("addCandidat");
    }

    @FXML
    void ToAddElecteur(MouseEvent event) {
        loadPage("addElecteur");

    }

    private void loadPage(String page){
       Parent root=null;
         try {
              root=FXMLLoader.load(getClass().getResource("/pages/user/"+page+".fxml"));
            //   root = FXMLLoader.load(getClass().getResource("actions.fxml"));
         } catch (IOException e) {
              e.printStackTrace();
              System.out.println("Failed to load page: " + page);
              System.out.println("Error: " + e.getMessage());
         } 
            bp.setCenter(root);
    }

}
