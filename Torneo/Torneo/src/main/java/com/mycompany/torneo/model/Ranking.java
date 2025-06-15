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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking implements ISerializableJSON {
    private List<Estadistica> estadisticas;

    public Ranking(List<Estadistica> estadisticas) {
        this.estadisticas = new ArrayList<>(estadisticas);
    }

    public void ordenarPorPuntaje() {
        Collections.sort(estadisticas, new Comparator<Estadistica>() {
            @Override
            public int compare(Estadistica e1, Estadistica e2) {
                return Integer.compare(e2.getPuntaje(), e1.getPuntaje()); // descendente
            }
        });
    }

    public List<Estadistica> getEstadisticasOrdenadas() {
        ordenarPorPuntaje();
        return estadisticas;
    }

    public void agregarEstadistica(Estadistica estadistica) {
        estadisticas.add(estadistica);
    }

    public Estadistica getEstadisticaEquipo(Equipo equipo) {
        for (Estadistica e : estadisticas) {
            if (e.getEquipo().equals(equipo)) {
                return e;
            }
        }
        return null;
    }

    public int getPosicionEquipo(Equipo equipo) {
        ordenarPorPuntaje();
        for (int i = 0; i < estadisticas.size(); i++) {
            if (estadisticas.get(i).getEquipo().equals(equipo)) {
                return i + 1;
            }
        }
        return -1; // no encontrado
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ranking:\n");
        int posicion = 1;
        for (Estadistica e : getEstadisticasOrdenadas()) {
            sb.append(posicion++).append(". ").append(e.getResumen()).append("\n");
        }
        return sb.toString();
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
            Ranking ranking = gson.fromJson(reader, Ranking.class);
            this.estadisticas = ranking.estadisticas != null ? ranking.estadisticas : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}