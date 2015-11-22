/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Tbencuestasxusuario;
import modelo.Tbusuario;

/**
 *
 * @author Andres
 */
public class Activo {
    public static Tbusuario usuario;
    public static Tbencuestasxusuario[] filtrado;
    public static int botonActivo;
    public static String bloquecreado;
    
    public void cambioAnchor(ActionEvent event, String ruta){
        try {
            // cargamos la scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource(ruta));
            AnchorPane Usuario = (AnchorPane) loader.load();

            // agregamos a la ventana
            Scene scene = new Scene(Usuario);
            Node node = (Node) event.getSource();
            Stage primaryStage = (Stage) node.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    public void cambioBorder(ActionEvent event, String ruta){
        try {
            // cargamos la scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource(ruta));
            BorderPane Usuario = (BorderPane) loader.load();

            // agregamos a la ventana
            Scene scene = new Scene(Usuario);
            Node node = (Node) event.getSource();
            Stage primaryStage = (Stage) node.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
