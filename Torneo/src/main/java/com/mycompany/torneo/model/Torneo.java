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
import java.util.Collections;
import java.util.List;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Torneo implements ISerializableJSON {

    private String nombre;
    private Deporte deporte;
    private int cantidadEquipos;
    private int tiempoPorPartido;
    private List<Equipo> equipos;
    private List<Partido> partidos; 
    
    public Torneo(String nombre, Deporte deporte, int cantidadEquipos, int tiempoPorPartido) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.cantidadEquipos = cantidadEquipos;
        this.tiempoPorPartido = tiempoPorPartido;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
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

    public List<Partido> getPartidos() {
        return partidos;
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
    
    /**
 * Calcula el ranking de equipos basado en partidos ganados.
 * @return Mapa ordenado de equipos con su cantidad de victorias.
 */
public Map<Equipo, Integer> calcularRanking() {
    Map<Equipo, Integer> ranking = new HashMap<>();

    for (Equipo equipo : equipos) {
        ranking.put(equipo, 0); // iniciar en 0
    }

    for (Partido partido : partidos) {
        if (partido.isFinalizado()) {
            Equipo ganador = partido.getGanador();
            if (ganador != null) {
                ranking.put(ganador, ranking.get(ganador) + 1);
            }
        }
    }

    // Ordenar de mayor a menor
    return ranking.entrySet().stream()
        .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (a, b) -> a,
            LinkedHashMap::new
        ));
}

    /**
     * Genera los partidos aleatorios entre los equipos inscritos.
     */
    public void generarPartidos() {
        partidos.clear();

        List<Equipo> mezclados = new ArrayList<>(equipos);
        Collections.shuffle(mezclados);

        for (int i = 0; i < mezclados.size() - 1; i += 2) {
            Equipo equipo1 = mezclados.get(i);
            Equipo equipo2 = mezclados.get(i + 1);
            partidos.add(new Partido(equipo1, equipo2));
        }

        if (mezclados.size() % 2 != 0) {
            Equipo libre = mezclados.get(mezclados.size() - 1);
            System.out.println("⚠ Equipo con pase automático: " + libre.getNombre());
            // Puedes guardar un partido nulo o especial si lo deseas
        }
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
            Torneo cargado = gson.fromJson(reader, Torneo.class);
            this.nombre = cargado.nombre;
            this.deporte = cargado.deporte;
            this.cantidadEquipos = cargado.cantidadEquipos;
            this.tiempoPorPartido = cargado.tiempoPorPartido;
            this.equipos = cargado.equipos != null ? cargado.equipos : new ArrayList<>();
            this.partidos = cargado.partidos != null ? cargado.partidos : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}