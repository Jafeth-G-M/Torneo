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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AnimacionController {
    @FXML private AnchorPane rootPane;
    @FXML private Label lblCampeon;
    @FXML private ImageView imgCampeon;

    public void mostrarCampeon(Equipo campeon) {
        lblCampeon.setText("¡Campeón: " + campeon.getNombre() + "!");
        imgCampeon.setImage(new Image("file:" + campeon.getFotoPreferida()));

        animarTexto();
        animarImagen();
    }

    private void animarTexto() {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), lblCampeon);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(4);
        fade.setAutoReverse(true);
        fade.play();
    }

    private void animarImagen() {
        ScaleTransition scale = new ScaleTransition(Duration.seconds(1.5), imgCampeon);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        scale.play();
    }    
}
