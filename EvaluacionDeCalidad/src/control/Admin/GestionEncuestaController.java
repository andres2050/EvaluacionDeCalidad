/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbencuestaJpaController;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tbencuesta;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */
public class GestionEncuestaController implements Initializable {
    // Declaramos los botones
    @FXML private Button modificarbt;
    @FXML private Button consultarbt;
    @FXML private Button limpiarbt;
    
    
    // Declaramos los textfileds
    @FXML private TextField numerotf;
    @FXML private TextField nombretf;
    @FXML private TextField fechactf;
    @FXML private TextField fechamtf;
    
    // Declaramos la tabla y las columnas
    @FXML
    private TableView tablaencuesta;
    private TableColumn titulo;
   
    //Otros
    ObservableList<ObservableList> tbencuesta;
    Tbencuesta encuesta = new Tbencuesta();
    TbencuestaJpaController encuestabd = new TbencuestaJpaController(); 
    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
    @FXML
    private Button anadirbt;

 
   
    @FXML 
    public void anadir (ActionEvent event) throws Exception {
        
        if(!nombretf.getText().isEmpty()){      
            Tbencuesta nueva = new Tbencuesta();
            nueva.setNombre(nombretf.getText().trim().toUpperCase());
            nueva.setFechacreacion(fecha);
            encuestabd.create(nueva);
            cargarDatosTabla();
            limpiar();
        }
    }
   
   @FXML
    public void modificar (ActionEvent event) throws Exception {
        numerotf.setDisable(true);
        fechactf.setDisable(true);
        EntityManager em = encuestabd.getEntityManager();
        Tbencuesta nueva = new Tbencuesta();
        nueva.setId(Integer.valueOf(numerotf.getText()));
        
        TypedQuery<Tbencuesta> query = em.createNamedQuery("Tbencuesta.findById", Tbencuesta.class);
        nueva = query.setParameter("id", nueva.getId()).getSingleResult();
        nueva.setNombre(nombretf.getText().trim().toUpperCase());
        nueva.setFechamodificacion(fecha);
        
     
        encuestabd.edit(nueva);
        em.close();
        cargarDatosTabla();
        limpiar();
       
    }
    
    @FXML
    public void seleccionar(MouseEvent event) {
        tablaencuesta.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                if (tablaencuesta != null) {
                    modificarbt.setDisable(false);
                    String[] seleccionado = tablaencuesta.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    int id = Integer.parseInt(seleccionado[0].substring(1));
                    System.out.print(id);
                    EntityManager em = encuestabd.getEntityManager();
                    TypedQuery<Tbencuesta> query = em.createNamedQuery("Tbencuesta.findById", Tbencuesta.class);
                    Tbencuesta encuesta = query.setParameter("id", Integer.valueOf(id)).getSingleResult();
                    numerotf.setText(encuesta.getId().toString());
                    nombretf.setText(encuesta.getNombre());
                    fechactf.setText(df1.format(encuesta.getFechacreacion()));
                    String fecha = encuesta.getFechamodificacion() == null ? encuesta.getFechamodificacion()+"":df1.format(encuesta.getFechamodificacion());
                    fechamtf.setText(fecha);
                    anadirbt.setDisable(true);
                    consultarbt.setDisable(true);
                   
                    //lo mismo con todo los campos
                }

               
            }

        });
       
    }

    private void cargarDatosTabla() {
        tablaencuesta.getColumns().clear();
        EntityManager em = encuestabd.getEntityManager();
        TypedQuery<Tbencuesta> query = em.createNamedQuery("Tbencuesta.findAll", Tbencuesta.class);
        List<Tbencuesta> results = query.getResultList();

        String[] titulos = {
            "Numero",
            "Nombre",
            "Fecha De Creacion",
            "Fecha De Modificacion",
        };

        for (int i = 0; i < titulos.length; i++) {
            final int j = i;
            this.titulo = new TableColumn(titulos[i]);
            this.titulo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> parametro) {
                    return new SimpleStringProperty((String) parametro.getValue().get(j));
                }
            });
            tablaencuesta.getColumns().addAll(titulo);
            // Asignamos un tamaño a ls columnnas
            titulo.setMinWidth(120);

            // Centrar los datos de la tabla
            titulo.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                @Override
                public TableCell<String, String> call(TableColumn<String, String> p) {
                    TableCell cell = new TableCell() {
                        @Override
                        protected void updateItem(Object t, boolean bln) {
                            if (t != null) {
                                super.updateItem(t, bln);
                                setText(t.toString());
                                setAlignment(Pos.CENTER); //Setting the Alignment
                            }
                        }
                    };
                    return cell;
                }
            });
        }

        tbencuesta = FXCollections.observableArrayList();
        for (Tbencuesta encuesta1 : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(encuesta1.getId().toString());
            row.add(encuesta1.getNombre());
            row.add(df1.format(encuesta1.getFechacreacion()));
           
            String fecha = encuesta1.getFechamodificacion() == null ? encuesta1.getFechamodificacion()+"":df1.format(encuesta1.getFechamodificacion());
            row.add(fecha);
           tbencuesta.addAll(row);
        }
        tablaencuesta.setItems(tbencuesta);
        em.close();
    }



    @FXML
    public void limpiar() {
       numerotf.setText("");
       nombretf.setText("");
       fechactf.setText("");
       fechamtf.setText("");
       anadirbt.setDisable(false);
       consultarbt.setDisable(false);
       modificarbt.setDisable(true);
    }
  
    @FXML
   public void consultar (ActionEvent event) throws Exception {
       
        Tbencuesta encu = new Tbencuesta();
        encu.setId(Integer.valueOf(numerotf.getText()));   
        EntityManager em = encuestabd.getEntityManager();
        TypedQuery<Tbencuesta> query = em.createNamedQuery("Tbencuesta.findById", Tbencuesta.class);
        Tbencuesta encuest = query.setParameter("id",encu.getId()).getSingleResult();
        numerotf.setText(encuest.getId().toString());
        nombretf.setText(encuest.getNombre());
        fechactf.setText(df1.format(encuest.getFechacreacion()));
        String fecha = encuest.getFechamodificacion() == null ? encuest.getFechamodificacion()+"":df1.format(encuest.getFechamodificacion());
        fechamtf.setText(fecha);
        em.close();
     
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarDatosTabla();
        limpiar();
        fechactf.setDisable(true);
        fechamtf.setDisable(true);
        numerotf.setDisable(true);
        
    }    

}
