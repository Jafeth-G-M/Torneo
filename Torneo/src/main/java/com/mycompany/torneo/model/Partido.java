/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.model;

/**
 *
 * @author jafet
 */

import java.util.Objects; 

public class Partido {
    
    private Equipo equipo1;
    private Equipo equipo2;
    private int goles1;
    private int goles2;
    private Resultado resultado;
    private boolean finalizado;

    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.goles1 = 0;
        this.goles2 = 0;
        this.finalizado = false;
    }

    public void anotarGol(Equipo equipo) {
        if (finalizado) return;

        if (Objects.equals(equipo, equipo1)) {
            goles1++;
        } else if (Objects.equals(equipo, equipo2)) {
            goles2++;
        }
    }

    public void finalizarPartido() {
        if (finalizado) return;

        if (goles1 > goles2) {
            resultado = new Resultado(equipo1, goles1, goles2, false);
        } else if (goles2 > goles1) {
            resultado = new Resultado(equipo2, goles1, goles2, false);
        } else {
            resultado = null; // empate, pendiente desempate
        }
        finalizado = true;
    }

    public void aplicarDesempate(Equipo ganadorDesempate) {
        if (finalizado && resultado == null && 
            (ganadorDesempate.equals(equipo1) || ganadorDesempate.equals(equipo2))) {
            resultado = new Resultado(ganadorDesempate, goles1, goles2, true);
        }
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getGoles1() {
        return goles1;
    }

    public int getGoles2() {
        return goles2;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public Equipo getGanador() {
        return (resultado != null) ? resultado.getGanador() : null;
    }

    @Override
    public String toString() {
        return equipo1.getNombre() + " " + goles1 + " - " + goles2 + " " + equipo2.getNombre();
    }
}