package com.example.sponsorshipapp.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.sponsorshipapp.DBConnection;
import com.example.sponsorshipapp.entities.Electeur;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class nbParrainsController {

    @FXML
    private Text nbparainstxt;

//  nouveau electeur
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
            System.out.println( "id user connecte"+candidatId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Electeur Parrains = new Electeur(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
                        rs.getString("password"), rs.getString("profil_id"), rs.getInt("activated"), rs.getInt("id"));
                sponsors.add(Parrains);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    //    afficher la taille de sponsors
        // System.out.println("nombre de parrains"+sponsors.size());

        return sponsors;
    }

    // fonction initialize
    public void initialize() {
        // recuperer la liste des parrains
        List<Electeur> sponsors = getSponsors(electeur.getConnectedUserId());
        // afficher le nombre de parrains
        nbparainstxt.setText("Chers canadidat vous avez "+sponsors.size()+" parrains actuellement");
    }





}
