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
import com.mycompany.torneo.model.Partido;
import com.mycompany.torneo.model.Resultado;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PartidoController {
    @FXML private Label lblEquipo1;
    @FXML private Label lblEquipo2;
    @FXML private Label lblMarcador;
    @FXML private Label lblTiempo;
    @FXML private ImageView imgEquipo1;
    @FXML private ImageView imgEquipo2;
    @FXML private ImageView imgBalon;
    @FXML private Button btnFinalizar;

    private Partido partido;
    private int tiempoRestante;
    private Timeline reloj;

    public void iniciarPartido(Equipo e1, Equipo e2, int tiempo) {
        partido = new Partido(e1, e2);
        lblEquipo1.setText(e1.getNombre());
        lblEquipo2.setText(e2.getNombre());
        lblMarcador.setText("0 - 0");
        imgEquipo1.setImage(new Image("file:" + e1.getFotoPreferida()));
        imgEquipo2.setImage(new Image("file:" + e2.getFotoPreferida()));
        tiempoRestante = tiempo;
        lblTiempo.setText(tiempoRestante + " min");

        reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarTiempo()));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }

    private void actualizarTiempo() {
        tiempoRestante--;
        lblTiempo.setText(tiempoRestante + " min");
        if (tiempoRestante <= 0) {
            reloj.stop();
            finalizarPartido();
        }
    }

    @FXML
    private void anotarEquipo1() {
        partido.anotarGol(partido.getEquipo1());
        actualizarMarcador();
    }

    @FXML
    private void anotarEquipo2() {
        partido.anotarGol(partido.getEquipo2());
        actualizarMarcador();
    }

    @FXML
    private void finalizarPartido() {
        partido.finalizarPartido();
        Resultado resultado = partido.getResultado();
        if (resultado == null) {
            // Aplicar desempate manual por ahora
            resultado = new Resultado(partido.getEquipo1(), partido.getGoles1(), partido.getGoles2(), true);
            partido.aplicarDesempate(resultado.getGanador());
        }
        lblMarcador.setText("Final: " + partido.toString());
        btnFinalizar.setDisable(true);
    }

    private void actualizarMarcador() {
        lblMarcador.setText(partido.getGoles1() + " - " + partido.getGoles2());
    }    
}
