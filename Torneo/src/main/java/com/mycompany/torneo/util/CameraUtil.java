/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.util;

/**
 *
 * @author jafet
 */

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CameraUtil {
    public static boolean capturarFoto(String rutaSalida) {
        Webcam webcam = Webcam.getDefault();

        if (webcam == null) {
            System.err.println("No se encontró ninguna cámara.");
            return false;
        }

        try {
            Dimension size = WebcamResolution.VGA.getSize();
            webcam.setViewSize(size);
            webcam.open();

            BufferedImage imagen = webcam.getImage();
            if (imagen == null) {
                System.err.println("No se pudo capturar imagen.");
                return false;
            }

            ImageIO.write(imagen, "PNG", new File(rutaSalida));
            return true;

        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return false;

        } finally {
            if (webcam.isOpen()) {
                webcam.close();
            }
        }
    }
}