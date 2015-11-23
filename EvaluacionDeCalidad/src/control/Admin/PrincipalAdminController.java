/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import vista.Activo;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class PrincipalAdminController implements Initializable {
    @FXML
    private AnchorPane MenuPrincipal;
    @FXML
    private Button CargPlano;
    @FXML
    private Button GenInforme;
    @FXML
    private Button acercade;
    @FXML
    private Button Gusuario;
    @FXML
    private Button Gasignatura;
    @FXML
    private Button Gencusta;
    @FXML
    private Button Gbloque;
    @FXML
    private Button Csesion;
    @FXML
    private Button Gpregunta;
    @FXML
    private Button Gdocente;
    @FXML
    private Button Abloque;
    @FXML
    private Button Apregunta;
    @FXML
    private Button Aasignatura;
    @FXML
    private Button Aencuesta;
    @FXML
    private Button Aestudiante;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gusuario.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Gasignatura.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Gdocente.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        CargPlano.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        GenInforme.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        acercade.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Gencusta.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Gbloque.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Csesion.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Gpregunta.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Gdocente.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Abloque.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Apregunta.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Aasignatura.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Aencuesta.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        Aestudiante.setStyle("-fx-font: 14 arial ; -fx-base: #d93434; -fx-border-color : #b82b2b");
        MenuPrincipal.setStyle("-fx-background-image: url(/imagenes/fondoadmi.jpg); -fx-background-size: 900px 600px;");
    }    
    
    @FXML
    public void AccionCargPlano(ActionEvent event) {
        String ruta = "Admin/Menu/CargarArchivoPlano.fxml";
        new Activo().cambioBorder(event,ruta);
    }
    
    @FXML
    public void AccionGenInforme(ActionEvent event) {
        String ruta = "Admin/Menu/GenerarInforme.fxml";
        new Activo().cambioBorder(event,ruta);
    }
    
    @FXML
    public void AccionaMacercade(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Software desarrollado por: \n \n"
                                            + "Andres Felipe Herrera \n"
                                            + "Juan David Eslava \n"
                                            + "Kevin Collazos Rojas \n"
                                            + "Nicolas Murillo \n"
                                            + "Juan David De la Pava \n");
    }

    @FXML
    private void AccionGusuario(ActionEvent event) {
        String ruta = "Admin/Menu/GestionUsuario.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void accionGasignatura(ActionEvent event) {
        String ruta = "Admin/Menu/GestionAsignatura.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void AccionGencuesta(ActionEvent event) {
        String ruta = "Admin/Menu/GestionEncuesta.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void AccionGbloque(ActionEvent event) {
        String ruta = "Admin/Menu/GestionBloque.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void AccionCsesion(ActionEvent event) {
        String ruta = "IniciarSesion.fxml";
        new Activo().cambioAnchor(event,ruta);
    }

    @FXML
    private void accionGpregunta(ActionEvent event) {
        String ruta = "Admin/Menu/GestionPregunta.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void accionGdocente(ActionEvent event) {
        String ruta = "Admin/Menu/GestionDocente.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void accionAbloque(ActionEvent event) {
        String ruta = "Admin/Menu/AsignarBloque.fxml";
        new Activo().cambioBorder(event, ruta);
    }

    @FXML
    private void accionApregunta(ActionEvent event) {
        String ruta = "Admin/Menu/AsignarPregunta.fxml";
        new Activo().cambioBorder(event, ruta);
    }

    @FXML
    private void accionAasignatura(ActionEvent event) {
        String ruta = "Admin/Menu/AsignarDocente.fxml";
        new Activo().cambioBorder(event,ruta);   
    }
    
    @FXML
    private void accionAencuesta(ActionEvent event){
        String ruta = "Admin/Menu/AsignarEncuesta.fxml";
        new Activo().cambioBorder(event,ruta);
    }

    @FXML
    private void AccionAestudiante(ActionEvent event) {
        String ruta = "Admin/Menu/AsignarEstudiante.fxml";
        new Activo().cambioBorder(event,ruta);
    }
    
    
}
