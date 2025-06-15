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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PartidoDialog {

    public static void mostrar(Partido partido) {
        Stage stage = new Stage();
        stage.setTitle("Partido: " + partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());

        Label lblGoles1 = new Label("Goles " + partido.getEquipo1().getNombre() + ": " + partido.getGoles1());
        Label lblGoles2 = new Label("Goles " + partido.getEquipo2().getNombre() + ": " + partido.getGoles2());

        Button btnGol1 = new Button("Anotar " + partido.getEquipo1().getNombre());
        Button btnGol2 = new Button("Anotar " + partido.getEquipo2().getNombre());
        Button btnFinalizar = new Button("Finalizar Partido");

        btnGol1.setOnAction(e -> {
            partido.anotarGol(partido.getEquipo1());
            lblGoles1.setText("Goles " + partido.getEquipo1().getNombre() + ": " + partido.getGoles1());
        });

        btnGol2.setOnAction(e -> {
            partido.anotarGol(partido.getEquipo2());
            lblGoles2.setText("Goles " + partido.getEquipo2().getNombre() + ": " + partido.getGoles2());
        });

        btnFinalizar.setOnAction(e -> {
            partido.finalizarPartido();
            if (partido.getResultado() == null) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Desempate");
                dialog.setHeaderText("Empate detectado");
                dialog.setContentText("Nombre del equipo ganador:");

                dialog.showAndWait().ifPresent(nombre -> {
                    if (nombre.equalsIgnoreCase(partido.getEquipo1().getNombre())) {
                        partido.aplicarDesempate(partido.getEquipo1());
                    } else if (nombre.equalsIgnoreCase(partido.getEquipo2().getNombre())) {
                        partido.aplicarDesempate(partido.getEquipo2());
                    }
                });
            }
            stage.close();
        });

        GridPane root = new GridPane();
        root.setPadding(new Insets(15));
        root.setVgap(10);
        root.setHgap(10);

        root.add(lblGoles1, 0, 0);
        root.add(lblGoles2, 1, 0);
        root.add(btnGol1, 0, 1);
        root.add(btnGol2, 1, 1);
        root.add(btnFinalizar, 0, 2, 2, 1);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}    

