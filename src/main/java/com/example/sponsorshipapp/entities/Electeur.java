package com.example.sponsorshipapp.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.sponsorshipapp.DBConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private static int connectedUserId;
    private static int connectedUserActivated;
    // creer un bouton
    // private Button showInfoBtn;
    private Button desactivateBtn;
    private Button parrainerBtn;
    private static int HasSponsored=-1;




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
        this.parrainerBtn=new Button("parrainer");

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

    // creer la fonction getConnectedUserId
    public int getConnectedUserId() {
        return connectedUserId;
    }
    
    public static void setConnectedUserId(int connectedUserId) {
        Electeur.connectedUserId = connectedUserId;
    }

    // getteur et setteur pour HasSponsored
    public static int getHasSponsored() {
        return HasSponsored;
    }
    public static void setHasSponsored(int hasSponsored) {
        Electeur.HasSponsored = hasSponsored;
    }

    // getteur et setteur pour le bouton parrainer
    public Button getParrainerBtn() {
        return parrainerBtn;
    }

    // getBtns()
    public List<Button> getBtns() {
        List<Button> btns = new ArrayList<>();
        btns.add(this.parrainerBtn);
        return btns;
    }
    public void setParrainerBtn(Button parrainer) {
        this.parrainerBtn = parrainer;
    }

    // creer la fonction getConnectedUserActivated
    public static  int getConnectedUserActivated() {
        return connectedUserActivated;
    }

    // creer la fonction setConnectedUserActivated
    public static void setConnectedUserActivated(int connectedUserActivated) {
        Electeur.connectedUserActivated = connectedUserActivated;
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

    // creer la fonction parrainer unique si activated vaut 1
    public void parrainer(Electeur electeur) {
        // afficher les infos
        System.out.println("activated de l'electeur connecte " + electeur.getConnectedUserActivated());
        System.out.println("hasSponsored de l'electeur connecte " + electeur.getHasSponsored());

        // verifier si l'electeur est desactive par l'admin
        if (electeur.getConnectedUserActivated() != 1) {
            System.out.println("vous etes desactiver par l'admin vous pouvez pas pas parrainer");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("vous etes desactiver par l'admin vous ne pouvez pas pas parrainer ");
            alert.showAndWait();
            return;
        }

        // verifier si l'electeur a deja parraine
        if (electeur.getHasSponsored() == 1) {
            System.out.println("vous avez deja parrainer ");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("vous avez deja parrainer ");
            alert.showAndWait();
            return;
        }

        // inserer dans la table parrainages la date,l'id du user connecte et idelec
        String sql = "INSERT INTO parrainages (date_parrainage, electeur_id, candidat_id) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "2021-05-05");
            preparedStatement.setInt(2, electeur.getConnectedUserId());
            // afficher les infos
            System.out.println("id du candidat " + electeur.getIdElec());
            System.out.println("id de l'electeur connecte " + electeur.getConnectedUserId());
            preparedStatement.setInt(3, electeur.getIdElec());
            preparedStatement.executeUpdate();
            // afficher un message de confirmation
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("vous avez parrainer avec succes");
            alert.showAndWait();
            // update le champ HasSponsored de l'electeur
            Electeur.setHasSponsored(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}