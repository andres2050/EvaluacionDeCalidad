/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class CargarArchivoPlanoController implements Initializable {
    @FXML
    private Button Cargar;
    FileChooser explorador = new FileChooser();
    @FXML
    private TextField ruta;
    @FXML
    private Button Examinar;
    File archivo;
    private Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        explorador.setTitle("Open Resource File");
        
    }    

    @FXML
    private void AccionExaminar(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        archivo = explorador.showOpenDialog(primaryStage);
        ruta.setText(archivo.getAbsolutePath());
    }

    @FXML
    private void AccionCargar(ActionEvent event) throws IOException {
        openFile(archivo);
    }
    
    private void openFile(File archivo) throws IOException{
        String Texto = "";
        BufferedReader lectura = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
        String temp = "";
        String lecturaRead;
        while((lecturaRead = lectura.readLine()) == null){
            temp += temp + lecturaRead;
        }
        Texto = temp;
        System.out.println(Texto);
    }
    
}
