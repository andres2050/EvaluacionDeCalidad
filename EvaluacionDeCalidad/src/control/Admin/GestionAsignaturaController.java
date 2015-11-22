/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbasignaturaJpaController;
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
import modelo.Tbasignatura;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */
public class GestionAsignaturaController implements Initializable {

  // Declaramos los botones
    @FXML private Button crear;
    @FXML private Button modificar;
    @FXML private Button consultar;
    @FXML private Button limpiar;
    
    // Declaramos los textfileds
    @FXML private TextField codigo;
    @FXML private TextField nombre;
    @FXML private TextField fechac;
    @FXML private TextField fecham;
    
   // Declaramos la tabla y las columnas
   @FXML
   private TableView tablamateria;
   private TableColumn titulo;
   
   //Otros
   ObservableList<ObservableList> tbmateria;
   Tbasignatura asignatura = new Tbasignatura();
   TbasignaturaJpaController asignaturabd = new TbasignaturaJpaController(); 
   Date fecha = new Date();
   DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
   
    @FXML 
    public void crear (ActionEvent event) throws Exception {
            
        Tbasignatura nueva = new Tbasignatura();
        nueva.setCodigo(codigo.getText());
        nueva.setNombre(nombre.getText().trim().toUpperCase());
        nueva.setFechacreacion(fecha);
     
   
        asignaturabd.create(nueva);
        cargarDatosTabla();
        limpiar();

    }
   
   @FXML
    public void modificar (ActionEvent event) throws Exception {
      
        EntityManager em = asignaturabd.getEntityManager();
        Tbasignatura modi = new Tbasignatura();
        modi.setCodigo(codigo.getText());
        
        TypedQuery<Tbasignatura> query = em.createNamedQuery("Tbasignatura.findByCodigo", Tbasignatura.class);
        modi = query.setParameter("codigo", modi.getCodigo()).getSingleResult();
        modi.setNombre(nombre.getText().trim().toUpperCase());
        modi.setFechamodificacion(fecha);
        
     
        asignaturabd.edit(modi);
        cargarDatosTabla();
        limpiar();
        
       em.close();
       
    }
    
      @FXML
    public void seleccionar(MouseEvent event) {
        codigo.setDisable(true);
        crear.setDisable(true);
        consultar.setDisable(true);
        tablamateria.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                if (tablamateria != null) {
                    modificar.setDisable(false);
                    String[] seleccionado = tablamateria.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    String id = seleccionado[0].substring(1);
                    System.out.print(id);
                    EntityManager em = asignaturabd.getEntityManager();
                    TypedQuery<Tbasignatura> query = em.createNamedQuery("Tbasignatura.findByCodigo", Tbasignatura.class);
                    Tbasignatura asignatura = query.setParameter("codigo", id).getSingleResult();
                    codigo.setText(asignatura.getCodigo());
                    nombre.setText(asignatura.getNombre());
                    fechac.setText(df1.format(asignatura.getFechacreacion()));
                    String fecha = asignatura.getFechamodificacion() == null ? asignatura.getFechamodificacion()+"":df1.format(asignatura.getFechamodificacion());
                    fecham.setText(fecha);
                     
                   
                    //lo mismo con todo los campos
                }

               
            }

        });
       
    }
    
     
  
    private void cargarDatosTabla() {
        tablamateria.getColumns().clear();
        EntityManager em = asignaturabd.getEntityManager();
        TypedQuery<Tbasignatura> query = em.createNamedQuery("Tbasignatura.findAll", Tbasignatura.class);
        List<Tbasignatura> results = query.getResultList();

        String[] titulos = {
            "Codigo",
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
            tablamateria.getColumns().addAll(titulo);
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

        tbmateria = FXCollections.observableArrayList();
        for (Tbasignatura asig : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(asig.getCodigo());
            row.add(asig.getNombre());
            row.add(df1.format(asig.getFechacreacion()));
            System.out.print(asig.getFechacreacion());
            String fecha = asig.getFechamodificacion() == null ? asig.getFechamodificacion()+"":df1.format(asig.getFechamodificacion());
            row.add(fecha);
           tbmateria.addAll(row);
        }
        tablamateria.setItems(tbmateria);
        em.close();
    }



    @FXML
    public void limpiar() {
       codigo.setText("");
       nombre.setText("");
       fechac.setText("");
       fecham.setText("");
       codigo.setDisable(false);
       crear.setDisable(false);
       consultar.setDisable(false);
    }
      
   public void consultar (ActionEvent event) throws Exception {
       
        Tbasignatura asi = new Tbasignatura();
        asi.setCodigo(codigo.getText());

                  
                    EntityManager em = asignaturabd.getEntityManager();
                    TypedQuery<Tbasignatura> query = em.createNamedQuery( "Tbasignatura.findByCodigo", Tbasignatura.class);
                    Tbasignatura asignatura = query.setParameter("codigo",asi.getCodigo()).getSingleResult();
                    codigo.setText(asignatura.getCodigo());
                    nombre.setText(asignatura.getNombre());
                    fechac.setText(df1.format(asignatura.getFechacreacion()));
                    String fecha = asignatura.getFechamodificacion() == null ? asignatura.getFechamodificacion()+"":df1.format(asignatura.getFechamodificacion());
                    fecham.setText(fecha);
                           
        
        
        em.close();
     
   }
   
   
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarDatosTabla();
        fechac.setDisable(true);
        fecham.setDisable(true);
        
        
    }    

    
    
}
