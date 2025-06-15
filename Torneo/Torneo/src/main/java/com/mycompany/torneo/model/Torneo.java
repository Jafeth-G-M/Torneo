/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.model;

/**
 *
 * @author jafet
 */
import com.mycompany.torneo.model.interfaces.ISerializableJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Torneo implements ISerializableJSON {
    private String nombre;
    private Deporte deporte;
    private int cantidadEquipos;
    private int tiempoPorPartido;
    private List<Equipo> equipos;

    public Torneo(String nombre, Deporte deporte, int cantidadEquipos, int tiempoPorPartido) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.cantidadEquipos = cantidadEquipos;
        this.tiempoPorPartido = tiempoPorPartido;
        this.equipos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public int getTiempoPorPartido() {
        return tiempoPorPartido;
    }

    public void setTiempoPorPartido(int tiempoPorPartido) {
        this.tiempoPorPartido = tiempoPorPartido;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public boolean agregarEquipo(Equipo equipo) {
        if (equipos.size() < cantidadEquipos && !equipos.contains(equipo)) {
            return equipos.add(equipo);
        }
        return false;
    }

    public boolean removerEquipo(Equipo equipo) {
        return equipos.remove(equipo);
    }

    public boolean estaCompleto() {
        return equipos.size() == cantidadEquipos;
    }

    @Override
    public String toString() {
        return nombre + " (" + deporte.getNombre() + ")";
    }

    // Implementación de ISerializableJSON
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
            Torneo torneo = gson.fromJson(reader, Torneo.class);
            this.nombre = torneo.nombre;
            this.deporte = torneo.deporte;
            this.cantidadEquipos = torneo.cantidadEquipos;
            this.tiempoPorPartido = torneo.tiempoPorPartido;
            this.equipos = torneo.equipos != null ? torneo.equipos : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}