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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */
public class AsignarbloqueController implements Initializable {

   // Declaramos los botones
    @FXML private Button asignarbt;
    @FXML private Button modificarbt;
  
    
    
    // Declaramos los textfileds
    @FXML private TextField cencuestatf;
    @FXML private TextField nencuestatf;
    @FXML private TextField cbloquetf;
    @FXML private TextField nbloquetf;
   
  

   // Declaramos la tabla y las columnas
   @FXML
   private TableView bloquetb;
   private TableColumn titulo;
//   List<Tbtipo> results;
    
    
    
    
    
    
    

    @FXML 
      public void Buscardocente (ActionEvent event) throws Exception {
          
      }
    
      @FXML 
      public void Buscarasignatura (ActionEvent event) throws Exception {
          
      }
      @FXML 
      public void Crearasignacion (ActionEvent event) throws Exception {
          
      }
       @FXML 
      public void Modificarasignacion(ActionEvent event) throws Exception {
          
      }
       
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
