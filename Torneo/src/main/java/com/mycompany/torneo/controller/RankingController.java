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
import com.mycompany.torneo.model.Torneo;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class RankingController {

    @FXML private TableView<Pair<String, Integer>> tablaRanking;
    @FXML private TableColumn<Pair<String, Integer>, String> colEquipo;
    @FXML private TableColumn<Pair<String, Integer>, Integer> colVictorias;

    private Torneo torneo;

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
        mostrarRanking();
    }

    private void mostrarRanking() {
        ObservableList<Pair<String, Integer>> datos = FXCollections.observableArrayList();

        for (Map.Entry<Equipo, Integer> entry : torneo.calcularRanking().entrySet()) {
            datos.add(new Pair<>(entry.getKey().getNombre(), entry.getValue()));
        }

        colEquipo.setCellValueFactory(new PropertyValueFactory<>("key"));
        colVictorias.setCellValueFactory(new PropertyValueFactory<>("value"));

        tablaRanking.setItems(datos);
    }
}