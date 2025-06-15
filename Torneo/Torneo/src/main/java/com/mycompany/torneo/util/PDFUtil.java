/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.util;

/**
 *
 * @author jafet
 */

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.mycompany.torneo.model.Equipo;
import com.mycompany.torneo.model.Estadistica;
import com.mycompany.torneo.model.Torneo;

import java.io.File;
import java.io.IOException;

public class PDFUtil {

    public static void generarCertificado(Equipo equipo, Torneo torneo, Estadistica estadistica) {
        String nombreEquipo = equipo.getNombre().replaceAll("[^a-zA-Z0-9]", "_");
        String rutaArchivo = "certificados" + File.separator + "Certificado_" + nombreEquipo + ".pdf";

        try {
            // Asegurarse que el directorio exista
            File directorio = new File("certificados");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Crear PDF
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("CERTIFICADO DE PARTICIPACIÓN")
                    .setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Equipo: " + equipo.getNombre()));
            document.add(new Paragraph("Torneo: " + torneo.getNombre()));
            document.add(new Paragraph("Deporte: " + torneo.getDeporte().getNombre()));
            document.add(new Paragraph("\nResumen de Estadísticas:\n" + estadistica.getResumen()));

            document.close();

            System.out.println("Certificado creado: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el certificado: " + e.getMessage());
        }
    }
}