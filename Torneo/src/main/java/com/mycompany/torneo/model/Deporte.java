/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.model;

/**
 *
 * @author jafet
 */
public class Deporte {
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

     
}
