package com.example.sponsorshipapp;
//package responsive;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/pages/login.fxml"));
        Scene scene =new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    // fonction qui ouvre la page admin
    public void openAdminPage() throws Exception {
        // fermer toutes les fenetres avant
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/pages/admin.fxml"));
        Scene scene =new Scene(loader.load());
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();
    }
    // fonction qui ouvre la page electeur 
    public void openElecteurPage() throws Exception {
        // fermer toutes les fenetres avant
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/pages/electeur.fxml"));
        Scene scene =new Scene(loader.load());
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
