package com.example.sponsorshipapp.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.sponsorshipapp.DBConnection;

import javafx.scene.control.Button;

public class Electeur {
    private String nom;
    private String prenom;
    private String login;
    private String motDePasse;
    private  String profil;
    // private String profilId;
    private int activated;
    private int idElec;
    // creer un bouton
    // private Button showInfoBtn;
    private Button desactivateBtn;




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
        this.desactivateBtn=new Button("desactiver");

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
        if (this.activated == 0) {
            this.desactivateBtn.setText("activer");
        } else {
            this.desactivateBtn.setText("desactiver");
        }
        return this.activated;
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

    // gettteur et setteur button
    public Button getDesactivateBtn() {
        return desactivateBtn;
    }
    public void setDesactivateBtn(Button desactivateBtn) {
        this.desactivateBtn = desactivateBtn;
    }

    // setactivated
    public void setActivated(int activated) {
        this.activated = activated;
    }

// fonction sur le bouton desactivateBtn qui va update le champ activated de l'electeur correspondant 
    public void desactiverUtilisateur(Electeur electeur) {
        // verifier activated de l'electeur
        if (electeur.getActivated() == 0) {
            electeur.setActivated(1);
        } else
        electeur.setActivated(0);
        String sql = "UPDATE user SET activated = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, electeur.getActivated());
            preparedStatement.setInt(2, electeur.getIdElec());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Desactiver " + electeur.getLogin());

    }


    private String generateLogin(String nom, String prenom) {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 999
        int randomNumber = random.nextInt(1000);
        return nom.toLowerCase() + prenom.toLowerCase() + randomNumber;
    }
}