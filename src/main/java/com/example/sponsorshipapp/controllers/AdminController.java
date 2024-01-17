
package com.example.sponsorshipapp.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class AdminController {

    @FXML
    private BorderPane bp;

    @FXML
    private Button btndeco;

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

       @FXML
    void toStats(ActionEvent event) {
        loadPage("stats");

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

    // fonction deconexion
    // @FXML
    // void deconnexion(ActionEvent event) throws Exception {
    //     // fermer toutes les fenetres avant
    //     FXMLLoader loader =new FXMLLoader(getClass().getResource("/pages/login.fxml"));
    //     Parent root =loader.load();
    //     btndeco.getScene().setRoot(root);
        
    // }

}
