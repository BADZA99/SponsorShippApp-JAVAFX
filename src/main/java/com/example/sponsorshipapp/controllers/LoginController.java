package com.example.sponsorshipapp.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.sponsorshipapp.App;
import com.example.sponsorshipapp.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private Button btnCon;

    @FXML
    private TextField tlogin;

    @FXML
    private PasswordField tmdp;

    @FXML
    public void login(MouseEvent event) {
        PreparedStatement st=null;
        ResultSet rs=null;

        String username = tlogin.getText();
        String password = tmdp.getText();
        System.out.println(username);
        System.out.println(password);
        Connection con=DBConnection.getConnection();

        // fais la controle de saisie 
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("veuillez remplir tous les champs");
            return;
        }


        try {
            st=con.prepareStatement("select * from user where login=? and password=?");
            st.setString(1, username);
            st.setString(2, password);
            rs=st.executeQuery();
            if(rs.next()){
                System.out.println("login reussie");
                // ouvrir la page admin
                App app=new App();
                app.openAdminPage();
                Stage stage=(Stage)((Button)event.getSource()).getScene().getWindow();
                stage.close();
            }else{
                System.out.println("login et/ou mot de passe incorrect");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

            
        }
        



    }

}
