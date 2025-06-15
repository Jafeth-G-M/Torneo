/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.model;

/**
 *
 * @author jafet
 */
public class Estadistica {
       private Equipo equipo;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int goles;

    public Estadistica(Equipo equipo) {
        this.equipo = equipo;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosPerdidos = 0;
        this.goles = 0;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGoles() {
        return goles;
    }

    public void registrarPartidoGanado() {
        partidosJugados++;
        partidosGanados++;
    }

    public void registrarPartidoPerdido() {
        partidosJugados++;
        partidosPerdidos++;
    }

    public void registrarGoles(int cantidad) {
        goles += cantidad;
    }

    public int getPuntaje() {
        int empatesGanados = partidosJugados - partidosGanados - partidosPerdidos;
        return (partidosGanados * 3) + (empatesGanados * 2);
    }

    public String getResumen() {
        return equipo.getNombre() + ": " + partidosJugados + " PJ, " + partidosGanados + " PG, " +
               partidosPerdidos + " PP, " + goles + " Goles, " + getPuntaje() + " pts";
    }

    @Override
    public String toString() {
        return getResumen();
    }
}
