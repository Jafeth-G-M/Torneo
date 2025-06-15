/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.util;

/**
 *
 * @author jafet
 */

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.mycompany.torneo.model.Equipo;
import com.mycompany.torneo.model.Torneo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.mycompany.torneo.model.Estadistica;

public class PDFUtil {

    public static void generarCertificado(Equipo equipo, Torneo torneo, String rutaSalida) {
        try {
            File file = new File(rutaSalida);
            file.getParentFile().mkdirs(); // crear directorios

            PdfWriter writer = new PdfWriter(rutaSalida);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf, PageSize.A4);
            doc.setMargins(70, 50, 70, 50);

            // Título
            Paragraph titulo = new Paragraph("Certificado de Campeonato")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(24)
                    .setBold()
                    .setFontColor(ColorConstants.BLUE);
            doc.add(titulo);

            doc.add(new Paragraph("\n"));

            // desarrollo
            Paragraph texto = new Paragraph(String.format(
                    "Se otorga el presente certificado al equipo\n\n%s\n\npor haber ganado el torneo\n\n%s\n\nde la disciplina de %s.",
                    equipo.getNombre(),
                    torneo.getNombre(),
                    torneo.getDeporte().getNombre()))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14);

            doc.add(texto);

            // Espacio
            doc.add(new Paragraph("\n\n"));

            // Imagen del equipo
            if (equipo.getFotoPreferida() != null) {
                try {
                    Image imagen = new Image(ImageDataFactory.create(equipo.getFotoPreferida()));
                    imagen.setWidth(200);
                    imagen.setAutoScale(true);
                    imagen.setHorizontalAlignment(HorizontalAlignment.CENTER);
                    doc.add(imagen);
                } catch (IOException ex) {
                    System.err.println("No se pudo cargar la imagen del equipo: " + ex.getMessage());
                }
            }

            // Pie
            doc.add(new Paragraph("\n\n"));
            doc.add(new Paragraph("_________________________\nFirma del Organizador")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12));

            doc.close();
            System.out.println("✅ Certificado generado en: " + rutaSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarCertificado(Equipo equipo, Torneo torneo, Estadistica estadistica) {
        throw new UnsupportedOperationException("Not supported yet.");
}