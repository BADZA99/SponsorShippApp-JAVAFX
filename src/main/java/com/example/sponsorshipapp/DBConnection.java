package com.example.sponsorshipapp;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class DBConnection {
    static String user ="root";
    static String password ="";
    static String url ="jdbc:mysql://localhost:3306/sponsorship_app";
    // static String driver="com.mysql.jdbc.Driver";

    public static Connection getConnection(){
        Connection con=null;
        try {
            // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, password);
            System.out.println("connexion reussie");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connexion echouee");
            throw new RuntimeException(e);
        }
        return con;
    }

}
