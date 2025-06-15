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
import com.mycompany.torneo.util.JSONUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class EquipoController {
    @FXML private TextField txtNombre;
    @FXML private ImageView imgFoto;
    @FXML private ComboBox<Deporte> cmbDeporte;
    @FXML private TableView<Equipo> tblEquipos;
    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colDeporte;

    private final List<Equipo> listaEquipos = new ArrayList<>();
    private final List<Deporte> listaDeportes = new ArrayList<>();
    private File imagenSeleccionada;

    private static final String RUTA_EQUIPOS = "equipos.json";
    private static final String RUTA_DEPORTES = "deportes.json";

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDeporte.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getDeporte().getNombre()));

        cargarDeportes();
        cargarEquipos();

        cmbDeporte.setItems(FXCollections.observableArrayList(listaDeportes));
        actualizarTabla();
    }

    @FXML
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto del Equipo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        imagenSeleccionada = fileChooser.showOpenDialog(null);
        if (imagenSeleccionada != null) {
            imgFoto.setImage(new Image(imagenSeleccionada.toURI().toString()));
        }
    }

    @FXML
    private void guardarEquipo() {
        String nombre = txtNombre.getText();
        Deporte deporte = cmbDeporte.getValue();

        if (!nombre.isEmpty() && imagenSeleccionada != null && deporte != null) {
            Equipo equipo = new Equipo(nombre, imagenSeleccionada.getAbsolutePath(), null, deporte);
            listaEquipos.add(equipo);
            JSONUtil.guardarObjeto(RUTA_EQUIPOS, listaEquipos);
            limpiarFormulario();
            actualizarTabla();
        }
    }

    private void cargarDeportes() {
        List<Deporte> cargados = JSONUtil.cargarObjeto(RUTA_DEPORTES, ArrayList.class);
        if (cargados != null) {
            listaDeportes.addAll(cargados);
        }
    }

    private void cargarEquipos() {
        List<Equipo> cargados = JSONUtil.cargarObjeto(RUTA_EQUIPOS, ArrayList.class);
        if (cargados != null) {
            listaEquipos.addAll(cargados);
        }
    }

    private void actualizarTabla() {
        tblEquipos.getItems().setAll(listaEquipos);
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        imgFoto.setImage(null);
        cmbDeporte.getSelectionModel().clearSelection();
        imagenSeleccionada = null;
    }    
}
