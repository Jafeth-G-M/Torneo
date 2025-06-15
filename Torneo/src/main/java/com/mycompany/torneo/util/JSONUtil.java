/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.util;

/**
 *
 * @author jafet
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void guardarObjeto(String ruta, T objeto) {
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }

    public static <T> T cargarObjeto(String ruta, Class<T> clase) {
        try (FileReader reader = new FileReader(ruta)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
            return null;
        }
    }    
}
