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
import javafx.scene.shape.Circle;
import vista.Activo;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class MenuController implements Initializable {
    
    @FXML
    private Button Regresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Regresar.setStyle("-fx-background-radius: 100%;");
    }    


    @FXML
    private void AccionRegresar(ActionEvent event) {
        String ruta = "Admin/PrincipalAdmin.fxml";
        new Activo().cambioAnchor(event,ruta);
    }
   
   
}
