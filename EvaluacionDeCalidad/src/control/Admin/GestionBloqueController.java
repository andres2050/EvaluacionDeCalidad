/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbbloqueJpaController;
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
import modelo.Tbbloque;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class GestionBloqueController implements Initializable {
    @FXML
    private Button anadirbt;
    @FXML
    private TableView tablabloques;
    @FXML
    private TextField numerotf;
    @FXML
    private TextField bloquetf;
    @FXML
    private Button modificarbt;
    @FXML
    private Button eliminarbt;
    @FXML
    private Button nuevobt;
    @FXML
    private Button consultarbt;
    @FXML
    private Button limpiarbt;
    @FXML
    private TextField fechactf;
    @FXML
    private TextField fechamtf;
    private TableColumn titulo;
   @FXML private Button limpiar;
   //Otros
   ObservableList<ObservableList> tbbloque;
   Tbbloque bloque = new Tbbloque();
   TbbloqueJpaController bloquebd = new TbbloqueJpaController(); 
   Date fecha = new Date();
   DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosTabla();
    }    

    @FXML
private void anadir(ActionEvent event) {
   
       if(!bloquetf.getText().isEmpty()){
        bloque = new Tbbloque();
        bloque.setNombre(bloquetf.getText().toUpperCase());

        EntityManager em = bloquebd.getEntityManager();
        TypedQuery<Tbbloque> query = em.createNamedQuery("Tbbloque.findByNombre", Tbbloque.class);
        System.out.println(query);
        List<Tbbloque> results = query.setParameter("nombre", bloque.getNombre()).getResultList();
        System.out.println(results);
        em.close();
        
        if(results.isEmpty()){
            bloque.setFechacreacion(fecha);
            bloquebd.create(bloque);
            cargarDatosTabla();
            limpiar();
        }
    }
}

    @FXML
    private void seleccionar(MouseEvent event) {
        tablabloques.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                if (tablabloques != null) {
                   
                    String[] seleccionado = tablabloques.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    int id = Integer.parseInt(seleccionado[0].substring(1));
                    System.out.print(id);
                    EntityManager em = bloquebd.getEntityManager();
                    TypedQuery<Tbbloque> query = em.createNamedQuery("Tbbloque.findById", Tbbloque.class);
                    Tbbloque bloque = query.setParameter("id", Integer.valueOf(id)).getSingleResult();
                    numerotf.setText(bloque.getId().toString());
                    bloquetf.setText(bloque.getNombre());
                    fechactf.setText(df1.format(bloque.getFechacreacion()));
                    String fecha = bloque.getFechamodificacion() == null ? bloque.getFechamodificacion()+"":df1.format(bloque.getFechamodificacion());
                    fechamtf.setText(fecha);
                   
                    //lo mismo con todo los campos
                }

               
            }

        });
    }

    @FXML
    private void modificar(ActionEvent event) throws Exception {
        EntityManager em = bloquebd.getEntityManager();
        Tbbloque nuevobloque = new Tbbloque();
        nuevobloque.setId(Integer.valueOf(numerotf.getText()));
        TypedQuery<Tbbloque> query = em.createNamedQuery("Tbbloque.findById", Tbbloque.class);
        nuevobloque = query.setParameter("id", nuevobloque.getId()).getSingleResult();
        nuevobloque.setNombre(bloquetf.getText().trim().toUpperCase());
        nuevobloque.setFechamodificacion(fecha);
        
     
        bloquebd.edit(nuevobloque);
        cargarDatosTabla();
        limpiar();
    }



  

    @FXML
    private void consultar(ActionEvent event) {
    }

    @FXML
    private void limpiar() {
       numerotf.setText("");
       bloquetf.setText("");
       fechactf.setText("");
       fechamtf.setText("");
      
    }
    
          @FXML
    public void limpiar (ActionEvent event) throws Exception {
  numerotf.setText("");
       bloquetf.setText("");
       fechactf.setText("");
       fechamtf.setText("");
//       @FXML private Button limpiar;
       
    }
    
    private void cargarDatosTabla() {
        tablabloques.getColumns().clear();
        EntityManager em = bloquebd.getEntityManager();
        TypedQuery<Tbbloque> query = em.createNamedQuery("Tbbloque.findAll", Tbbloque.class);
        List<Tbbloque> results = query.getResultList();

        String[] titulos = {
            "Numero",
            "bloque",
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
            tablabloques.getColumns().addAll(titulo);
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

        tbbloque = FXCollections.observableArrayList();
        for (Tbbloque bloq : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(bloq.getId().toString());
            row.add(bloq.getNombre());
            row.add(df1.format(bloq.getFechacreacion()));
            System.out.print(bloq.getFechacreacion());
            String fecha = bloq.getFechamodificacion() == null ? bloq.getFechamodificacion()+"":df1.format(bloq.getFechamodificacion());
            row.add(fecha);
           tbbloque.addAll(row);
        }
        tablabloques.setItems(tbbloque);
        em.close();
    }
}
