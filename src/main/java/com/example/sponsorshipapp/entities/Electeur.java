package com.example.sponsorshipapp.entities;

import java.util.Random;

public class Electeur {
    private String nom;
    private String prenom;
    private String login;
    private String motDePasse;
    private String profil;

    public Electeur(String nom, String prenom, String motDePasse, String profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.profil = profil;
        this.login = generateLogin(nom, prenom);
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



    private String generateLogin(String nom, String prenom) {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 999
        int randomNumber = random.nextInt(1000);
        return nom.toLowerCase() + prenom.toLowerCase() + randomNumber;
    }
}