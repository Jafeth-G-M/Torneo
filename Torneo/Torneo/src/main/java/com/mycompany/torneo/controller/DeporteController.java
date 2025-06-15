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
import com.mycompany.torneo.util.JSONUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeporteController {
    @FXML private TextField txtNombre;
    @FXML private ImageView imgBalon;
    @FXML private TableView<Deporte> tblDeportes;
    @FXML private TableColumn<Deporte, String> colNombre;

    private final List<Deporte> listaDeportes = new ArrayList<>();
    private File imagenSeleccionada;

    private static final String RUTA_JSON = "deportes.json";

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cargarDeportes();
        actualizarTabla();
    }

    @FXML
    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen del balón");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        imagenSeleccionada = fileChooser.showOpenDialog(null);
        if (imagenSeleccionada != null) {
            imgBalon.setImage(new Image(imagenSeleccionada.toURI().toString()));
        }
    }

    @FXML
    private void guardarDeporte() {
        String nombre = txtNombre.getText();
        if (!nombre.isEmpty() && imagenSeleccionada != null) {
            Deporte deporte = new Deporte(nombre, imagenSeleccionada.getAbsolutePath());
            listaDeportes.add(deporte);
            JSONUtil.guardarObjeto(RUTA_JSON, listaDeportes);
            limpiarFormulario();
            actualizarTabla();
        }
    }

    private void cargarDeportes() {
        List<Deporte> cargados = JSONUtil.cargarObjeto(RUTA_JSON, ArrayList.class);
        if (cargados != null) {
            listaDeportes.addAll(cargados);
        }
    }

    private void actualizarTabla() {
        tblDeportes.getItems().setAll(listaDeportes);
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        imgBalon.setImage(null);
        imagenSeleccionada = null;
    }    
}
