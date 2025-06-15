/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.util;

/**
 *
 * @author jafet
 */

import java.io.File;

public class FileUtil {
    public static boolean eliminarArchivo(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists() && archivo.delete();
    }

    public static boolean archivoExiste(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists();
    }

    public static long obtenerTamanoArchivo(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists() ? archivo.length() : -1;
    }    
}
