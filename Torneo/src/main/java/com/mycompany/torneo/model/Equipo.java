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

public class Equipo implements ISerializableJSON {
    private String nombre;
    private String fotoArchivo;
    private String fotoCamara;
    private Deporte deporte;

    public Equipo(String nombre, String fotoArchivo, String fotoCamara, Deporte deporte) {
        this.nombre = nombre;
        this.fotoArchivo = fotoArchivo;
        this.fotoCamara = fotoCamara;
        this.deporte = deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoArchivo() {
        return fotoArchivo;
    }

    public void setFotoArchivo(String fotoArchivo) {
        this.fotoArchivo = fotoArchivo;
    }

    public String getFotoCamara() {
        return fotoCamara;
    }

    public void setFotoCamara(String fotoCamara) {
        this.fotoCamara = fotoCamara;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public String getFotoPreferida() {
        return (fotoCamara != null && !fotoCamara.isEmpty()) ? fotoCamara : fotoArchivo;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equalsIgnoreCase(equipo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }

    // Implementación de iserializablejson

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
            Equipo equipo = gson.fromJson(reader, Equipo.class);
            this.nombre = equipo.nombre;
            this.fotoArchivo = equipo.fotoArchivo;
            this.fotoCamara = equipo.fotoCamara;
            this.deporte = equipo.deporte;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
