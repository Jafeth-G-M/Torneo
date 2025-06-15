/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.controller;

/**
 *
 * @author jafet
 */

import com.mycompany.torneo.model.Estadistica;
import com.mycompany.torneo.model.Equipo;
import com.mycompany.torneo.model.Ranking;
import com.mycompany.torneo.util.JSONUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List; 

public class EstadisticaController {
 
    @FXML private ListView<String> lstResumenEstadisticas;
    @FXML private TableView<Estadistica> tblRanking;
    @FXML private TableColumn<Estadistica, String> colEquipo;
    @FXML private TableColumn<Estadistica, Integer> colPuntaje;

    private final List<Estadistica> estadisticas = new ArrayList<>();

    private static final String RUTA_ESTADISTICAS = "estadisticas.json";

    @FXML
    public void initialize() {
        colEquipo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getEquipo().getNombre()));
        colPuntaje.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getPuntaje()).asObject());

        cargarEstadisticas();
        mostrarResumenes();
        mostrarRanking();
    }

    private void cargarEstadisticas() {
        List<Estadistica> cargadas = JSONUtil.cargarObjeto(RUTA_ESTADISTICAS, ArrayList.class);
        if (cargadas != null) estadisticas.addAll(cargadas);
    }

    private void mostrarResumenes() {
        List<String> resumenes = new ArrayList<>();
        for (Estadistica e : estadisticas) {
            resumenes.add(e.getResumen());
        }
        lstResumenEstadisticas.setItems(FXCollections.observableArrayList(resumenes));
    }

    private void mostrarRanking() {
        Ranking ranking = new Ranking(estadisticas);
        tblRanking.setItems(FXCollections.observableArrayList(ranking.getEstadisticasOrdenadas()));
    }   
}
