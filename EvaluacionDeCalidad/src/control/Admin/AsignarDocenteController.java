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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */
public class AsignarDocenteController implements Initializable {
    
     // Declaramos los botones
    @FXML private Button asignarbt;
    @FXML private Button modificarbt;
  
    
    
    // Declaramos los textfileds
    @FXML private TextField cdocentetf;
    @FXML private TextField ndocentetf;
    @FXML private TextField casignaturatf;
    @FXML private TextField nasignaturatf;
    @FXML private TextField cupotf;
    @FXML private ComboBox<String> grupocb;
  

   // Declaramos la tabla y las columnas
   @FXML
   private TableView asignartb;
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