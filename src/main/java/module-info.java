module com.example.sponsorshipapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    


    opens com.example.sponsorshipapp to javafx.fxml;
    opens com.example.sponsorshipapp.controllers to javafx.fxml;
    // opens com.example.sponsorshipapp.entities to javafx.fxml;
    opens com.example.sponsorshipapp.entities to javafx.base;

    exports com.example.sponsorshipapp;
}