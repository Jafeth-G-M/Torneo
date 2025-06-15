module com.mycompany.torneo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires webcam.capture;
    requires com.google.gson;

    opens com.mycompany.torneo to javafx.fxml;
    opens com.mycompany.torneo.controller to javafx.fxml;
    opens com.mycompany.torneo.util to javafx.fxml;

    exports com.mycompany.torneo;
    exports com.mycompany.torneo.controller;
    exports com.mycompany.torneo.util;
}
