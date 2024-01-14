package com.example.sponsorshipapp.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.sponsorshipapp.DBConnection;

public class Electeur {
    private String nom;
    private String prenom;
    private String login;
    private String motDePasse;
    private  String profil;
    // private String profilId;
    private int activated;
    private int idElec;



    public Electeur(String nom, String prenom, String motDePasse, String profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.profil = profil;
        this.login = generateLogin(nom, prenom);
    }

    public Electeur(String nom, String prenom, String login, String motDePasse, String profil, int activated,
            int idElec) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
        this.profil = profil;
        this.activated = activated;
        this.idElec = idElec;

    }

    @Override
    public String toString() {
        return "Electeur {" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", profil='" + profil + '\'' +
                ", activated=" + activated +
                ", idElec=" + idElec +
                '}';
    }

    // getteur login
    public String getLogin() {
        return login;
    }

    // creer la fonction getProfilId
    public int getProfilId() {
        if (profil.equals("ROLE_ELECTEUR")) {
            return 3;
        } else if (profil.equals("ROLE_CANDIDAT")) {
            return 2;
        }
        return 0;
    }

    // creer la fonction getActivated
    public int getActivated() {
        return 1;
    }

    // creer la fonction getNom
    public String getNom() {
        return nom;
    }

    // creer la fonction getPrenom
    public String getPrenom() {
        return prenom;
    }

    // creer la fonction getMotDePasse
    public String getMotDePasse() {
        return motDePasse;
    }

    // creer la fonction getProfil
    public String getProfil() {
        return profil;
    }

    // creer la fonction getIdElec
    public int getIdElec() {
        return idElec;
    }


    // creer la fonction getAll
    // public static List<Electeur> getAll() {
    //     List<Electeur> Electeurs = new ArrayList<>();
    //     try {
    //         Connection con = DBConnection.getConnection();
    //         // Créer une requête SQL pour obtenir tous les utilisateurs
    //         String sql = "SELECT * FROM user";

    //         // Créer une requête préparée
    //         PreparedStatement preparedStatement = con.prepareStatement(sql);

    //         // Exécuter la requête et obtenir les résultats
    //         ResultSet resultSet = preparedStatement.executeQuery();

    //         // Parcourir les résultats et ajouter chaque utilisateur à la liste des utilisateurs
    //         while (resultSet.next()) {
    //             String nom = resultSet.getString("nom");
    //             String prenom = resultSet.getString("prenom");
    //             String login = resultSet.getString("login");
    //             String motDePasse = resultSet.getString("password");
    //             String profil = resultSet.getString("profil");
    //             Electeurbdd electeur = new Electeurbdd(nom, prenom, motDePasse, profil,login);
    //             Electeurs.add(electeur);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return Electeurs;
    // }



    private String generateLogin(String nom, String prenom) {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 999
        int randomNumber = random.nextInt(1000);
        return nom.toLowerCase() + prenom.toLowerCase() + randomNumber;
    }
}