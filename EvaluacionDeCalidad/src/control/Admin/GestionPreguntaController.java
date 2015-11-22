/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbpreguntaJpaController;
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
import modelo.Tbpregunta;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */

public class GestionPreguntaController implements Initializable {
    
    // Declaramos los botones
    @FXML private Button aniadirbt;
    @FXML private Button modificarbt;
    @FXML private Button eliminarbt;
    @FXML private Button consultarbt;
    @FXML private Button limpiar;

    // Declaramos los textfileds
    @FXML private TextField numerotf;
    @FXML private TextField preguntatf;
    @FXML private TextField fechactf;
    @FXML private TextField fechamtf;
    
    // Declaramos la tabla y las columnas
    @FXML
    private TableView tablapreguntas;
    private TableColumn titulo;

    //Otros
    ObservableList<ObservableList> tbpregunta;
    Tbpregunta pregunta = new Tbpregunta();
    TbpreguntaJpaController preguntabd = new TbpreguntaJpaController(); 
    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);

   
    @FXML 
    public void anadir (ActionEvent event) throws Exception {
            
        Tbpregunta nuevapregunta = new Tbpregunta();
        nuevapregunta.setId(Integer.valueOf(numerotf.getText()));
        nuevapregunta.setNombre(preguntatf.getText().trim().toUpperCase());
        nuevapregunta.setFechacreacion(fecha);
     
   
        preguntabd.create(nuevapregunta);
        cargarDatosTabla();
        limpiar();

    }
   
   @FXML
    public void modificar (ActionEvent event) throws Exception {
        numerotf.setDisable(true);
        fechactf.setDisable(true);
        EntityManager em = preguntabd.getEntityManager();
        Tbpregunta nuevapregunta = new Tbpregunta();
        nuevapregunta.setId(Integer.valueOf(numerotf.getText()));
        
        TypedQuery<Tbpregunta> query = em.createNamedQuery("Tbpregunta.findById", Tbpregunta.class);
        nuevapregunta = query.setParameter("id", nuevapregunta.getId()).getSingleResult();
        nuevapregunta.setNombre(preguntatf.getText().trim().toUpperCase());
        nuevapregunta.setFechamodificacion(fecha);
        
     
        preguntabd.edit(nuevapregunta);
        cargarDatosTabla();
        limpiar();
        numerotf.setDisable(false);
        fechactf.setDisable(false);
       em.close();
       
    }
    
      @FXML
    public void seleccionar(MouseEvent event) {
        tablapreguntas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                if (tablapreguntas != null) {
                    modificarbt.setDisable(false);
                    String[] seleccionado = tablapreguntas.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    int id = Integer.parseInt(seleccionado[0].substring(1));
                    System.out.print(id);
                    EntityManager em = preguntabd.getEntityManager();
                    TypedQuery<Tbpregunta> query = em.createNamedQuery("Tbpregunta.findById", Tbpregunta.class);
                    Tbpregunta pregunta = query.setParameter("id", Integer.valueOf(id)).getSingleResult();
                    numerotf.setText(pregunta.getId().toString());
                    preguntatf.setText(pregunta.getNombre());
                    fechactf.setText(df1.format(pregunta.getFechacreacion()));
                    String fecha = pregunta.getFechamodificacion() == null ? pregunta.getFechamodificacion()+"":df1.format(pregunta.getFechamodificacion());
                    fechamtf.setText(fecha);
                     
                   
                    //lo mismo con todo los campos
                }

               
            }

        });
       
    }
    
     
  
    private void cargarDatosTabla() {
        tablapreguntas.getColumns().clear();
        EntityManager em = preguntabd.getEntityManager();
        TypedQuery<Tbpregunta> query = em.createNamedQuery("Tbpregunta.findAll", Tbpregunta.class);
        List<Tbpregunta> results = query.getResultList();

        String[] titulos = {
            "Numero",
            "Pregunta",
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
            tablapreguntas.getColumns().addAll(titulo);
            // Asignamos un tamaño a ls columnnas
            titulo.setMinWidth(170);

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

        tbpregunta = FXCollections.observableArrayList();
        for (Tbpregunta pregu : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(pregu.getId().toString());
            row.add(pregu.getNombre());
            row.add(df1.format(pregu.getFechacreacion()));
            System.out.print(pregu.getFechacreacion());
            String fecha = pregu.getFechamodificacion() == null ? pregu.getFechamodificacion()+"":df1.format(pregu.getFechamodificacion());
            row.add(fecha);


            
            
           tbpregunta.addAll(row);
          
        }
        tablapreguntas.setItems(tbpregunta);
        em.close();
    }



    @FXML
    public void limpiar() {
       numerotf.setText("");
       preguntatf.setText("");
       fechactf.setText("");
       fechamtf.setText("");
    }

    @FXML
    public void limpiar (ActionEvent event) throws Exception {
   numerotf.setText("");
       preguntatf.setText("");
       fechactf.setText("");
       fechamtf.setText("");
//       @FXML private Button limpiar;
       
    }
    
  
    
   public void consultar (ActionEvent event) throws Exception {
       
        Tbpregunta pre = new Tbpregunta();
        pre.setId(Integer.valueOf(numerotf.getText()));
        EntityManager em = preguntabd.getEntityManager();
        TypedQuery<Tbpregunta> query = em.createNamedQuery("Tbpregunta.findById", Tbpregunta.class);
        Tbpregunta pregunta = query.setParameter("id",pre.getId()).getSingleResult();
        numerotf.setText(pregunta.getId().toString());
        preguntatf.setText(pregunta.getNombre());
        fechactf.setText(df1.format(pregunta.getFechacreacion()));
        String fecha = pregunta.getFechamodificacion() == null ? pregunta.getFechamodificacion()+"":df1.format(pregunta.getFechamodificacion());
        fechamtf.setText(fecha);
        em.close();
     
   }
   

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarDatosTabla();

        
        
        
    }    

    
    
}
