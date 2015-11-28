/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbencuestaJpaController;
import DAO.TbencuestaxasignaturaJpaController;
import DAO.TbgrupoxasignaturaxprofesorJpaController;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tbasignatura;
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
    @FXML private ComboBox<String> cencuestacb;
    @FXML private ComboBox<String> casignaturacb;
    private TableColumn titulo;
    @FXML
    private Button buscar2bt;
    @FXML
    private Button buscar1bt;
    @FXML
    private TableView encuestatb;
    @FXML
    private Button limpiarbt;
    @FXML
    private Button cargarbt;
    TbencuestaxasignaturaJpaController exabd = new TbencuestaxasignaturaJpaController();
    TbgrupoxasignaturaxprofesorJpaController gpabd = new TbgrupoxasignaturaxprofesorJpaController();
    TbasignaturaJpaController asig = new TbasignaturaJpaController(); 
    TbencuestaJpaController encuestabd = new TbencuestaJpaController();
    ObservableList<ObservableList> tbencuesta;
    ObservableList<ObservableList> tbasignatura;
    ObservableList<ObservableList> tbencuestaxasignatura;
    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
    List<Tbasignatura> resultasignatura;
    List<Tbencuesta> resultencuesta;
    Tbencuestaxasignatura exa = new Tbencuestaxasignatura();
    List<Tbencuestaxasignatura> resultsexa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarcomboencuesta();
        cargarcomboasignatura();
        cargarDatosTabla();
        limpiar();
        // TODO
    }
    
    @FXML
    public void seleccionar(MouseEvent event) {
        
    }
    
    @FXML
    public void cargarDatosTabla() {
        encuestatb.getColumns().clear();
        EntityManager em = exabd.getEntityManager();
        TypedQuery<Tbencuestaxasignatura> query = em.createNamedQuery("Tbencuestaxasignatura.findAll", Tbencuestaxasignatura.class);
        List<Tbencuestaxasignatura> results = query.getResultList();

        String[] titulos = {
            "Numero",
            "Codigo",
            "Nombre",
            "Encuesta",
            "Nombre",
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
            encuestatb.getColumns().addAll(titulo);
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

        tbencuestaxasignatura = FXCollections.observableArrayList();
        for (Tbencuestaxasignatura exa : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(exa.getId().toString());
            row.add(exa.getIdasignatura().getCodigo());
            row.add(exa.getIdasignatura().getNombre());
            row.add(exa.getIdencuesta().getId().toString());
            row.add(exa.getIdencuesta().getNombre());        
           tbencuestaxasignatura.addAll(row);
        }
        encuestatb.setItems(tbencuestaxasignatura);
        em.close();
    }
    
    @FXML 
    public void Buscardocente (ActionEvent event) throws Exception {
        encuestatb.getColumns().clear();
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
            encuestatb.getColumns().addAll(titulo);
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
        encuestatb.setItems(tbencuesta);
        em.close();
    }
    
    @FXML 
    public void Buscarasignatura (ActionEvent event) throws Exception {
        encuestatb.getColumns().clear();
        EntityManager em = gpabd.getEntityManager();
        TypedQuery<Tbgrupoxasignaturaxprofesor> query = em.createNamedQuery("Tbgrupoxasignaturaxprofesor.findAll", Tbgrupoxasignaturaxprofesor.class);
        List<Tbgrupoxasignaturaxprofesor> results   = query.getResultList();

        String[] titulos = {
            "Codigo",
            "Nombre",
            "Cedula",
            "Profesor",
            "Grupo",
            "Cupo",
            
            
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
            encuestatb.getColumns().addAll(titulo);
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

        tbasignatura = FXCollections.observableArrayList();
        for (Tbgrupoxasignaturaxprofesor gap : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            String codigo = gap.getIdasignatura().getCodigo();
            row.add(codigo);
            String nombre = gap.getIdasignatura().getNombre();
            row.add(nombre);
            String nombrep = gap.getCedula().getNombre();
            String apellidop = gap.getCedula().getApellido();
            row.add(gap.getCedula().getCedula().toString());
            row.add(nombrep + apellidop);
            row.add(gap.getIdgrupo().getIdentificacion().toString());
            row.add(gap.getCupo() + "");
          
            
           tbasignatura.addAll(row);
        }
        encuestatb.setItems(tbasignatura);
        em.close();
    }
    
    @FXML 
    public void Crearasignacion (ActionEvent event) throws Exception {
        if(cencuestacb.getValue() != null && casignaturacb.getValue() != null){
            EntityManager em = exabd.getEntityManager();
            TypedQuery<Tbencuestaxasignatura> query = em.createNamedQuery("Tbencuestaxasignatura.findAll", Tbencuestaxasignatura.class);
            Tbencuestaxasignatura[] filtro = new Tbencuestaxasignatura[resultsexa.size()];
            resultsexa = query.getResultList();
            em.close();
            
            int j=0;
            for(int i=0;i<resultsexa.size();i++){
                if(resultsexa.get(i).getIdasignatura().getCodigo().equals(casignaturacb.getValue())){
                    filtro[j] = resultsexa.get(i);
                    j++;
                }
            }
            
            

        
        }
    }
    
    @FXML 
    public void Modificarasignacion(ActionEvent event) throws Exception {
          
    }

    @FXML
    private void limpiar() {
    }
    
    public void cargarcomboencuesta() {

        EntityManager em = encuestabd.getEntityManager();
        TypedQuery<Tbencuesta> query = em.createNamedQuery("Tbencuesta.findAll", Tbencuesta.class);

        resultencuesta = query.getResultList();

        em.close();
        for(int i=0;i<resultencuesta.size();i++){
           cencuestacb.getItems().add(resultencuesta.get(i).getId().toString());
        }
    }
    
   
    
    public void cargarcomboasignatura(){

        EntityManager em = asig.getEntityManager();
        TypedQuery<Tbasignatura> query = em.createNamedQuery("Tbasignatura.findAll", Tbasignatura.class);

        resultasignatura = query.getResultList();

        em.close();
        for(int i=0;i<resultasignatura.size();i++){
           casignaturacb.getItems().add(resultasignatura.get(i).getCodigo());
        }
    }
    
}
