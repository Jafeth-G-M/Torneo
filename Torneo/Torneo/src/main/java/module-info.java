module com.mycompany.torneo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.torneo to javafx.fxml;
    exports com.mycompany.torneo;
    requires com.google.gson;
}
