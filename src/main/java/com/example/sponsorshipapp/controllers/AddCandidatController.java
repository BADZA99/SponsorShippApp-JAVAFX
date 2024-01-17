package com.example.sponsorshipapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

// import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.sponsorshipapp.DBConnection;
import com.example.sponsorshipapp.entities.Electeur;

public class AddCandidatController {

    @FXML
    private Button btnAdd;

    @FXML
    private ChoiceBox<String> selectProfil;

    @FXML
    private PasswordField txtMdp;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    // connexion a la base de donnee
    public void initialize() {
        try {
            Connection con = DBConnection.getConnection();
            // Créer une requête SQL pour obtenir tous les profils
            String sql = "SELECT name FROM role";

            // Créer une requête préparée
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Exécuter la requête et obtenir les résultats
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parcourir les résultats et ajouter chaque profil à la ChoiceBox
            while (resultSet.next()) {
                String profil = resultSet.getString("name");
                // ne pas ajouter le role admin
                if (profil.equals("ROLE_ADMIN")) {
                    continue;
                } else if (profil.equals("ROLE_ELECTEUR")) {
                    continue;
                }
                selectProfil.getItems().add(profil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // plus precis
            System.out.println("Erreur lors de la récupération des profils");
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    // fonction qui vide les champs du formulaire
    public void clearFields() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtMdp.setText("");
        selectProfil.setValue("");
    }

    @FXML
    // void addElecteur(MouseEvent event) {
    //     String nom = txtNom.getText();
    //     String prenom = txtPrenom.getText();
    //     String mdp = txtMdp.getText();
    //     String profil = selectProfil.getValue().toString();
    //     Electeur electeur = new Electeur(nom, prenom, mdp, profil);
    //     System.out.println(electeur);
    //     // ajouter l'electeur dans la base de donnee
    //     try {
    //         Connection con = DBConnection.getConnection();
    //         // Créer une requête SQL pour insérer un nouvel électeur
    //         String sql = "INSERT INTO user (nom, prenom, login, password,activated,profil_id) VALUES (?, ?, ?, ?, ?, ?)";

    //         // Créer une requête préparée
    //         PreparedStatement preparedStatement = con.prepareStatement(sql);
    //         preparedStatement.setString(1, nom);
    //         preparedStatement.setString(2, prenom);
    //         preparedStatement.setString(3, electeur.getLogin());
    //         preparedStatement.setString(4, mdp);
    //         preparedStatement.setInt(5, 1);
    //         preparedStatement.setInt(6, electeur.getProfilId());

    //         // Exécuter la requête
    //         preparedStatement.executeUpdate();

    //         System.out.println("Candidat ajouté avec succès");
    //         clearFields();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         // plus precis
    //         System.out.println("Erreur lors de l'ajout du candidat");
    //         System.out.println("Erreur: " + e.getMessage());
    //     }

    // }

    void addElecteur(MouseEvent event) {
    String nom = txtNom.getText();
    String prenom = txtPrenom.getText();
    String mdp = txtMdp.getText();
    String profil = selectProfil.getValue().toString();
    Electeur electeur = new Electeur(nom, prenom, mdp, profil);
    System.out.println(electeur);
    // ajouter l'electeur dans la base de donnee
    try {
        Connection con = DBConnection.getConnection();
        // Vérifier le nombre de candidats
        String countSql = "SELECT COUNT(*) FROM user WHERE profil_id = ?";
        PreparedStatement countStatement = con.prepareStatement(countSql);
        countStatement.setInt(1, electeur.getProfilId());
        ResultSet rs = countStatement.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            if (count >= 5) {
                // Afficher une alerte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("nombre de candidat maximum atteint.");
                alert.showAndWait();
                return;
            }
        }
        // Créer une requête SQL pour insérer un nouvel électeur
        String sql = "INSERT INTO user (nom, prenom, login, password,activated,profil_id) VALUES (?, ?, ?, ?, ?, ?)";

        // Créer une requête préparée
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);
        preparedStatement.setString(3, electeur.getLogin());
        preparedStatement.setString(4, mdp);
        preparedStatement.setInt(5, 1);
        preparedStatement.setInt(6, electeur.getProfilId());

        // Exécuter la requête
        preparedStatement.executeUpdate();

        System.out.println("Candidat ajouté avec succès");
        clearFields();
    } catch (SQLException e) {
        e.printStackTrace();
        // plus precis
        System.out.println("Erreur lors de l'ajout du candidat");
        System.out.println("Erreur: " + e.getMessage());
    }
}

}
