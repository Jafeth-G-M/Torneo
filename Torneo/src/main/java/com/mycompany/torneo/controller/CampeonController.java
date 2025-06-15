/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.controller;

/**
 *
 * @author jafet
 */
import com.mycompany.torneo.model.Equipo;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CampeonController {

    @FXML private ImageView imgCampeon;
    @FXML private Label lblCampeon;

    public void mostrarCampeon(Equipo equipo) {
        lblCampeon.setText("¡Campeón: " + equipo.getNombre() + "!");
        if (equipo.getFotoPreferida() != null) {
            imgCampeon.setImage(new Image("file:" + equipo.getFotoPreferida()));
        }

        // Animación de aparición (Fade + Scale)
        FadeTransition fade = new FadeTransition(Duration.seconds(2), imgCampeon);
        fade.setFromValue(0);
        fade.setToValue(1);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(2), imgCampeon);
        scale.setFromX(0.5);
        scale.setFromY(0.5);
        scale.setToX(1.2);
        scale.setToY(1.2);
        scale.setAutoReverse(true);
        scale.setCycleCount(2);

        fade.play();
        scale.play();
    }
}