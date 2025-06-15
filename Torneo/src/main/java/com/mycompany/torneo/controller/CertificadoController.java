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
import com.mycompany.torneo.model.Estadistica;
import com.mycompany.torneo.model.Torneo;
import com.mycompany.torneo.util.PDFUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CertificadoController {
    @FXML private Label lblNombreEquipo;
    @FXML private Label lblInfoTorneo;
    @FXML private Label lblResumen;
    @FXML private ImageView imgEquipo;
    @FXML private Button btnGenerarPDF;

    private Equipo equipo;
    private Torneo torneo;
    private Estadistica estadistica;

    public void setDatos(Equipo equipo, Torneo torneo, Estadistica estadistica) {
        this.equipo = equipo;
        this.torneo = torneo;
        this.estadistica = estadistica;

        lblNombreEquipo.setText(equipo.getNombre());
        lblInfoTorneo.setText("Torneo: " + torneo.getNombre() + " | Deporte: " + torneo.getDeporte().getNombre());
        lblResumen.setText(estadistica.getResumen());

        try {
            imgEquipo.setImage(new Image("file:" + equipo.getFotoPreferida()));
        } catch (Exception e) {
            System.err.println("Error cargando imagen del equipo: " + e.getMessage());
        }
    }

    @FXML
    private void generarPDF() {
        try {
            PDFUtil.generarCertificado(equipo, torneo, estadistica);
            new Alert(Alert.AlertType.INFORMATION, "Certificado generado correctamente.").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error al generar el certificado: " + e.getMessage()).show();
        }
    }
}