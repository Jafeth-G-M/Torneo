/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.model;

/**
 *
 * @author jafet
 */
public class Resultado {
       private Equipo ganador;
    private int goles1;
    private int goles2;
    private boolean porDesempate;

    public Resultado(Equipo ganador, int goles1, int goles2, boolean porDesempate) {
        this.ganador = ganador;
        this.goles1 = goles1;
        this.goles2 = goles2;
        this.porDesempate = porDesempate;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public int getGoles1() {
        return goles1;
    }

    public void setGoles1(int goles1) {
        this.goles1 = goles1;
    }

    public int getGoles2() {
        return goles2;
    }

    public void setGoles2(int goles2) {
        this.goles2 = goles2;
    }

    public boolean isPorDesempate() {
        return porDesempate;
    }

    public void setPorDesempate(boolean porDesempate) {
        this.porDesempate = porDesempate;
    }

    @Override
    public String toString() {
        return "Ganador: " + ganador + (porDesempate ? " (por desempate)" : "");
    }
}
