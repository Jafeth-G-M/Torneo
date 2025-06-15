/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.model;

/**
 *
 * @author jafet
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.torneo.model.interfaces.ISerializableJSON;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Deporte implements ISerializableJSON {
    private String nombre;
    private String imagenBalon;

    // Constructor, getters, setters

    public String getNombre() {
        return nombre;
    }

    public String getImagenBalon() {
        return imagenBalon;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagenBalon(String imagenBalon) {
        this.imagenBalon = imagenBalon;
    }

    public Deporte() {
    }

    public Deporte(String nombre, String imagenBalon) {
        this.nombre = nombre;
        this.imagenBalon = imagenBalon;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Deporte deporte = (Deporte) obj;
        return nombre.equalsIgnoreCase(deporte.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }

    // Métodos de la interfaz ISerializableJSON

    @Override
    public void guardarJSON(String ruta) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarJSON(String ruta) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ruta)) {
            Deporte deporte = gson.fromJson(reader, Deporte.class);
            this.nombre = deporte.nombre;
            this.imagenBalon = deporte.imagenBalon;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
