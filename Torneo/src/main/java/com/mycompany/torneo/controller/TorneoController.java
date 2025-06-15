/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.controller;

/**
 *
 * @author jafet
 */

import com.mycompany.torneo.model.Deporte;
import com.mycompany.torneo.model.Equipo;
import com.mycompany.torneo.model.Torneo;
import com.mycompany.torneo.util.JSONUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class TorneoController {
    @FXML private TextField txtNombreTorneo;
    @FXML private Spinner<Integer> spnCantidadEquipos;
    @FXML private Spinner<Integer> spnTiempoPartido;
    @FXML private ComboBox<Deporte> cmbDeporte;
    @FXML private ListView<Equipo> lstEquiposDisponibles;
    @FXML private ListView<Equipo> lstEquiposSeleccionados;

    private final List<Equipo> equiposDisponibles = new ArrayList<>();
    private final List<Deporte> deportesDisponibles = new ArrayList<>();
    private final List<Torneo> torneos = new ArrayList<>();

    private static final String RUTA_EQUIPOS = "equipos.json";
    private static final String RUTA_DEPORTES = "deportes.json";
    private static final String RUTA_TORNEOS = "torneos.json";

    @FXML
    public void initialize() {
        spnCantidadEquipos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 32, 2));
        spnTiempoPartido.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 60, 10));

        cargarDeportes();
        cargarEquipos();

        cmbDeporte.setItems(FXCollections.observableArrayList(deportesDisponibles));
        lstEquiposDisponibles.setItems(FXCollections.observableArrayList(equiposDisponibles));
    }

    @FXML
    private void agregarEquipo() {
        Equipo seleccionado = lstEquiposDisponibles.getSelectionModel().getSelectedItem();
        if (seleccionado != null && !lstEquiposSeleccionados.getItems().contains(seleccionado)) {
            lstEquiposSeleccionados.getItems().add(seleccionado);
        }
    }

    @FXML
    private void quitarEquipo() {
        Equipo seleccionado = lstEquiposSeleccionados.getSelectionModel().getSelectedItem();
        lstEquiposSeleccionados.getItems().remove(seleccionado);
    }

    @FXML
    private void crearTorneo() {
        String nombre = txtNombreTorneo.getText();
        Deporte deporte = cmbDeporte.getValue();
        int cantidad = spnCantidadEquipos.getValue();
        int tiempo = spnTiempoPartido.getValue();
        List<Equipo> seleccionados = new ArrayList<>(lstEquiposSeleccionados.getItems());

        if (!nombre.isEmpty() && deporte != null && seleccionados.size() == cantidad) {
            Torneo torneo = new Torneo(nombre, deporte, cantidad, tiempo);
            seleccionados.forEach(torneo::agregarEquipo);
            torneos.add(torneo);
            JSONUtil.guardarObjeto(RUTA_TORNEOS, torneos);
            limpiarFormulario();
        } else {
            new Alert(Alert.AlertType.WARNING, "Complete los campos correctamente y seleccione la cantidad exacta de equipos.").show();
        }
    }

    private void cargarDeportes() {
        List<Deporte> cargados = JSONUtil.cargarObjeto(RUTA_DEPORTES, ArrayList.class);
        if (cargados != null) deportesDisponibles.addAll(cargados);
    }

    private void cargarEquipos() {
        List<Equipo> cargados = JSONUtil.cargarObjeto(RUTA_EQUIPOS, ArrayList.class);
        if (cargados != null) equiposDisponibles.addAll(cargados);
    }

    private void limpiarFormulario() {
        txtNombreTorneo.clear();
        cmbDeporte.getSelectionModel().clearSelection();
        lstEquiposSeleccionados.getItems().clear();
        spnCantidadEquipos.getValueFactory().setValue(2);
        spnTiempoPartido.getValueFactory().setValue(10);
    }    
}
