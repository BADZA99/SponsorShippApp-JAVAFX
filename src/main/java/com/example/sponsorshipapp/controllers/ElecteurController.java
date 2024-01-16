package com.example.sponsorshipapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.sponsorshipapp.DBConnection;
import com.example.sponsorshipapp.entities.Electeur;

public class ElecteurController {

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

    // fonnction getActivatedCandidats qui va recuperer tous les users de la base de
    // donnee avec un profil_id qui vaut 2 et un activated qui vaut 1
    public List<Electeur> getActivatedCandidats() {
        List<Electeur> Candidats = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from user where profil_id=2 and activated=1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Candidats.add(new Electeur(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
                        rs.getString("password"), rs.getString("profil_id"), rs.getInt("activated"), rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("erreur lors de la recuperation des candidats");
            throw new RuntimeException(e);
        }
        return Candidats;
    }

    // fonction initialize

    public void initialize() {
        // recuperer la liste des candidats
        List<Electeur> Candidats = getActivatedCandidats();
        // creer une observable list
        ObservableList<Electeur> CandidatsObservable = FXCollections.observableArrayList(Candidats);

        // parcourir Candidats et lier chaque bouton parrainer a la fonction
        // parrainer
        for (Electeur electeur : Candidats) {

            electeur.getParrainerBtn().setOnAction((ActionEvent event) -> {
                electeur.parrainer(electeur);
                // rafraichir la page
                initialize();
            });

        }
        // ajouter les candidats dans le tableau
        userTable.setItems(CandidatsObservable);
        // ajouter les colonnes
        idCol.setCellValueFactory(new PropertyValueFactory<>("idElec"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        actionsCol.setCellValueFactory(new PropertyValueFactory<Electeur, String>("parrainerBtn"));

    }

}
