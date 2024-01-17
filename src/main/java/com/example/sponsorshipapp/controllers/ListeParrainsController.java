package com.example.sponsorshipapp.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.sponsorshipapp.DBConnection;
import com.example.sponsorshipapp.entities.Electeur;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListeParrainsController {

    @FXML
    private TableColumn<Electeur, String> actionsCol;

    @FXML
    private TableColumn<Electeur, Integer> idCol;

    @FXML
    private TableColumn<Electeur, String> nomCol;

    @FXML
    private TableColumn<Electeur, String> prenomCol;

    @FXML
    private TableView<Electeur> userTable;

    // fonctions qui selectionne tous les elcteur qui ont parrainer le candidat connecte
    // creer un electeur
    Electeur electeur = new Electeur(
            "test", "test", "login", "motDePasse", "profil", 1, 1);
    // creer la fonction qui recupere les parrains d'un candidat
    public List<Electeur> getSponsors(int candidatId) {
        List<Electeur> sponsors = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(
                    "select * from user where id in (select electeur_id from parrainages where candidat_id=?)");

            pstmt.setInt(1, candidatId);
            // AFFICHE CANDIDAT ID
            System.out.println(candidatId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Electeur Parrains = new Electeur(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
                        rs.getString("password"), rs.getString("profil_id"), rs.getInt("activated"), rs.getInt("id"));
                sponsors.add(Parrains);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("nombre de parrains" + sponsors.size());

        return sponsors;
    }

    // fonction initialize
    public void initialize() {
        // recuperer les candidats
        List<Electeur> sponsors = getSponsors(electeur.getConnectedUserId());
        // creer les colonnes
        idCol.setCellValueFactory(new PropertyValueFactory<>("idElec"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        // actionsCol.setCellValueFactory(new PropertyValueFactory<>("actionsCol"));
        // ajouter les candidats dans la table
        userTable.getItems().addAll(sponsors);
    }



    


}
