package com.example.sponsorshipapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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

public class ListeUserController {

    @FXML
    private TableView<Electeur> userTable;

    @FXML
    private TableColumn<Electeur, String> loginCol;

    @FXML
    private TableColumn<Electeur, String> prenomCol;

    @FXML
    private TableColumn<Electeur, Integer> idCol;

    @FXML
    private TableColumn<Electeur, Integer> profilIdCol;

    @FXML
    private TableColumn<Electeur, Integer> activatedCol;

    @FXML
    private TableColumn<Electeur, Integer> actionsCol;

    // button
    // @FXML
    // private Button showInfoBtn;

    // fonction getAll
    public List<Electeur> getAll() {
        List<Electeur> Electeurs = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            // Créer une requête SQL pour obtenir tous les Electeurs
            String sql = "SELECT * FROM user";

            // Créer une requête préparée
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Exécuter la requête et obtenir les résultats
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parcourir les résultats et ajouter chaque Electeur à la liste
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String login = resultSet.getString("login");
                String motDePasse = resultSet.getString("password");
                String profil = resultSet.getString("profil_id");
                int activated = resultSet.getInt("activated");
                int id = resultSet.getInt("id");
                Electeur Electeur = new Electeur(nom, prenom, login, motDePasse, profil, activated
                ,id);
                Electeurs.add(Electeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Electeurs;

    }

    public void initialize() {
        DBConnection.getConnection();
        // Créer une liste d'Electeurs
        List<Electeur> ElecteursData = getAll();
        // afficher dans le terminal
        // System.out.println(Electeurs + "\n");
        // parcour le tableau d'electeur et affcihe les informations de chaque electeur
        // for (Electeur Electeur : Electeurs) {
        //     System.out.println(Electeur);
        // }

        // remplir le tableau avec les eleteurs sauf ceux qui ont un profil_id de 1
        // Assumons que ElecteursData est une ObservableList<Electeur>
        ObservableList<Electeur> filteredElecteurs = FXCollections.observableArrayList(
                ElecteursData.stream().filter(electeur -> !electeur.getProfil().equals("1")).collect(Collectors.toList())
        );

        // Définir les cell value factories
        idCol.setCellValueFactory(new PropertyValueFactory<Electeur, Integer>("idElec"));
        loginCol.setCellValueFactory(new PropertyValueFactory<Electeur,String>("login"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Electeur, String>("prenom"));
        profilIdCol.setCellValueFactory(new PropertyValueFactory<Electeur, Integer>("profil"));
        activatedCol.setCellValueFactory(new PropertyValueFactory<Electeur, Integer>("activated"));
        // actionsCol.setCellValueFactory(new PropertyValueFactory<>("actions"));

        // Afficher les électeurs filtrés dans le tableau
        userTable.setItems(filteredElecteurs);

        





    }

}
