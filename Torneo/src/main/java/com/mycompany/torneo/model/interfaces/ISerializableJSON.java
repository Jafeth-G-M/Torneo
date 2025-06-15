/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.torneo.model.interfaces;

/**
 *
 * @author jafet
 */
public interface ISerializableJSON {
    /**
     * Guarda el objeto actual en un archivo JSON en la ruta dada.
     * @param ruta Ruta del archivo JSON donde se va a guardar.
     */
    void guardarJSON(String ruta);

    /**
     * Carga los datos del objeto desde un archivo JSON en la ruta dada.
     * @param ruta Ruta del archivo JSON desde donde se va a cargar.
     */
    void cargarJSON(String ruta);
}
