/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.torneo.controller;

/**
 *
 * @author jafet
 */

import com.mycompany.torneo.model.Equipo;
import com.mycompany.torneo.model.Partido;
import com.mycompany.torneo.model.Resultado;
import com.mycompany.torneo.model.Torneo;
import com.mycompany.torneo.util.PDFUtil;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PartidoController {

    @FXML private Label lblEquipo1;
    @FXML private Label lblEquipo2;
    @FXML private Label lblMarcador;
    @FXML private Label lblTiempo;
    @FXML private ImageView imgEquipo1;
    @FXML private ImageView imgEquipo2;
    @FXML private ImageView imgBalon;
    @FXML private Button btnFinalizar;
    @FXML private Label zonaGol1;
    @FXML private Label zonaGol2;

    private Partido partido;
    private int tiempoRestante;
    private Timeline reloj;
    private Torneo torneo;
    
    public void setTorneo(Torneo torneo) {
    this.torneo = torneo;
}
 
    //crea la pantalla de juego con los datos del partido y se inicializa el tiempo y partido
    public void inicializarPartido(Partido partido, int tiempo) {
        this.partido = partido;

        // Drag del balón
imgBalon.setOnDragDetected(e -> {
    Dragboard db = imgBalon.startDragAndDrop(TransferMode.MOVE);
    ClipboardContent content = new ClipboardContent();
    content.putImage(imgBalon.getImage());
    db.setContent(content);
    e.consume();
});

// Área del equipo 1
zonaGol1.setOnDragOver(e -> {
    if (e.getGestureSource() != zonaGol1 && e.getDragboard().hasImage()) {
        e.acceptTransferModes(TransferMode.MOVE);
    }
    e.consume();
});
zonaGol1.setOnDragDropped(e -> {
    anotarEquipo1();
    e.setDropCompleted(true);
    e.consume();
});

// Área del equipo 2
zonaGol2.setOnDragOver(e -> {
    if (e.getGestureSource() != zonaGol2 && e.getDragboard().hasImage()) {
        e.acceptTransferModes(TransferMode.MOVE);
    }
    e.consume();
});
zonaGol2.setOnDragDropped(e -> {
    anotarEquipo2();
    e.setDropCompleted(true);
    e.consume();
});
        
        Equipo e1 = partido.getEquipo1();
        Equipo e2 = partido.getEquipo2();

        lblEquipo1.setText(e1.getNombre());
        lblEquipo2.setText(e2.getNombre());
        lblMarcador.setText("0 - 0");

        if (e1.getFotoPreferida() != null)
            imgEquipo1.setImage(new Image("file:" + e1.getFotoPreferida()));
        if (e2.getFotoPreferida() != null)
            imgEquipo2.setImage(new Image("file:" + e2.getFotoPreferida()));

        tiempoRestante = tiempo;
        lblTiempo.setText(tiempoRestante + " min");

        reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarTiempo()));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }

    private void actualizarTiempo() {
        tiempoRestante--;
        lblTiempo.setText(tiempoRestante + " min");
        if (tiempoRestante <= 0) {
            reloj.stop();
            finalizarPartido();
        }
    }

    @FXML
    private void anotarEquipo1() {
        if (partido.isFinalizado()) return;

        partido.anotarGol(partido.getEquipo1());
        actualizarMarcador();
    }

@FXML
private void finalizarPartido() {
    if (partido.isFinalizado()) return;

    reloj.stop();
    partido.finalizarPartido();
    Resultado resultado = partido.getResultado();

    // Si es empate se mira para que inicie el desempate
    if (resultado == null) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Desempate");
        dialog.setHeaderText("Empate detectado");
        dialog.setContentText("Nombre del equipo ganador:");

        dialog.showAndWait().ifPresent(nombre -> {
            if (nombre.equalsIgnoreCase(partido.getEquipo1().getNombre())) {
                partido.aplicarDesempate(partido.getEquipo1());
            } else if (nombre.equalsIgnoreCase(partido.getEquipo2().getNombre())) {
                partido.aplicarDesempate(partido.getEquipo2());
            }
        });
    }

    lblMarcador.setText("Final: " + partido.toString());
    btnFinalizar.setDisable(true);

    // verfica si es el ultimo partido del torneo
    if (torneo != null) {
        long pendientes = torneo.getPartidos().stream()
            .filter(p -> !p.isFinalizado())
            .count();

        if (pendientes == 0) {
            Equipo equipoGanador = partido.getGanador(); // define el ganador

            try {
                // animacion del que gano
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/torneo/view/CampeonView.fxml"));
                Parent root = loader.load();

                CampeonController controller = loader.getController();
                controller.mostrarCampeon(equipoGanador);

                Stage stage = new Stage();
                stage.setTitle("¡Equipo Campeón!");
                stage.setScene(new Scene(root, 500, 400));
                stage.showAndWait(); // Espera a que se cierre

                // muestra el puntaje despues de la animacion de partido
                FXMLLoader rankingLoader = new FXMLLoader(getClass().getResource("/com/mycompany/torneo/view/RankingView.fxml"));
                Parent rankingRoot = rankingLoader.load();

                RankingController rankingController = rankingLoader.getController();
                rankingController.setTorneo(torneo);

                Stage rankingStage = new Stage();
                rankingStage.setTitle("Ranking Final");
                rankingStage.setScene(new Scene(rankingRoot, 400, 400));
                rankingStage.show();
                // crea el pdf para el certificado
                String rutaPDF = "certificados/" + equipoGanador.getNombre() + "_certificado.pdf";
                PDFUtil.generarCertificado(equipoGanador, torneo, rutaPDF);
  
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Certificado generado");
                alert.setHeaderText("El certificado ha sido guardado correctamente.");
                alert.setContentText("Ruta: " + rutaPDF);
                alert.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Cierra la ventana si esta stage
    Stage stage = (Stage) btnFinalizar.getScene().getWindow();
    if (stage != null) {
        stage.close();
    }
}

    private void actualizarMarcador() {
        lblMarcador.setText(partido.getGoles1() + " - " + partido.getGoles2());
    }

    private void anotarEquipo2() {
        throw new UnsupportedOperationException("Not supported yet."); 
}