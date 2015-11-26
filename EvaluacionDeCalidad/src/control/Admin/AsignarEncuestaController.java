/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbencuestaJpaController;
import DAO.TbencuestaxasignaturaJpaController;
import DAO.TbgrupoxasignaturaxprofesorJpaController;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tbencuesta;
import modelo.Tbencuestaxasignatura;
import modelo.Tbgrupoxasignaturaxprofesor;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */
public class AsignarEncuestaController implements Initializable {
    // Declaramos los botones
    @FXML private Button asignarbt;
    @FXML private Button modificarbt;
  
    
    
    // Declaramos los textfileds
    @FXML private TextField cencuestatf;
    @FXML private TextField nencuestatf;
    @FXML private TextField casignaturatf;
    @FXML private TextField nasignaturatf;

    // Declaramos la tabla y las columnas
    @FXML
    private TableView asignartb;
    private TableColumn titulo;
    


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarDatosTabla();
        // TODO
    }
    
      public void cargarDatosTabla() {
      }
    
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

}
