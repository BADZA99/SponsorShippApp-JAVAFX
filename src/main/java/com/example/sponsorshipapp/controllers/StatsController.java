package com.example.sponsorshipapp.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.sponsorshipapp.DBConnection;
import com.example.sponsorshipapp.entities.Electeur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatsController {

    @FXML
    private TableColumn<Electeur, String> actionsCol;

    @FXML
    private TableColumn<Electeur, Integer> activatedCol;

    @FXML
    private TableColumn<Electeur, Integer> idCol;

    @FXML
    private TableColumn<Electeur, String> loginCol;

    @FXML
    private TableColumn<Electeur, String> prenomCol;

    @FXML
    private TableColumn<Electeur, Integer> profilIdCol;

    @FXML
    private TableView<Electeur> userTable;

    // creer un electeur
    Electeur electeur = new Electeur(
            "test", "test", "login", "motDePasse", "profil", 1, 1);
    // creer la fonction qui recupere les electeur avec un profil_id qui vaut 2
    public List<Electeur> getAllCandidats() {
        List<Electeur> candidats = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(
                    "select * from user where profil_id=2");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Electeur cad = new Electeur(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
                        rs.getString("password"), rs.getString("profil_id"), rs.getInt("activated"), rs.getInt("id"));
                candidats.add(cad);
            }

        } catch (SQLException e) {
            System.out.println("erreur lors de la recuperation des parrains");
            throw new RuntimeException(e);
        }
        return candidats;
    }

   



    // fonction initialize
    public void initialize() {
        // recuperer la liste des candidats
        List<Electeur> Electeurs = getAllCandidats();
        // creer une observable list
        ObservableList<Electeur> CandidatsObs = FXCollections.observableArrayList(Electeurs);

         // parcourir ElecteursData et lier chaque bouton desactiver a la fonction desactiver

        for (Electeur electeur : Electeurs) {
            electeur.getShowInfoBtn().setOnAction((ActionEvent event) -> {
                // electeur.ShowAllSponsors(electeur);
                electeur.ShowAllSponsors();
                // rafraichir la page
                initialize();
            });
        }

        userTable.setItems(CandidatsObs);

      
        // afficher les colonnes
        idCol.setCellValueFactory(new PropertyValueFactory<>("idElec"));
        // nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        profilIdCol.setCellValueFactory(new PropertyValueFactory<>("profil"));
        activatedCol.setCellValueFactory(new PropertyValueFactory<>("activated"));
        actionsCol.setCellValueFactory(new PropertyValueFactory<Electeur, String>("showInfoBtn"));
        // actionsCol.setCellFactory(cellFactory);
    }

}
