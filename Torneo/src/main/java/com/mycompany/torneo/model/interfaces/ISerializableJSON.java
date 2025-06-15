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
 
    // guarda el obejeto en josn y muestra la ruta en la que se va a guadar
    void guardarJSON(String ruta);

    void cargarJSON(String ruta);
}
