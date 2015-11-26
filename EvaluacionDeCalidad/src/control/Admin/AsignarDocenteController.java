/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbasignaturaJpaController;
import DAO.TbgrupoJpaController;
import DAO.TbgrupoxasignaturaxprofesorJpaController;
import DAO.TbprofesorJpaController;
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
import modelo.Tbgrupo;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbprofesor;

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
    Tbgrupo usuario = new Tbgrupo();
    TbgrupoJpaController userbd = new TbgrupoJpaController(); 
   
    //listas
    List<Tbgrupo> resultsgrupo;
    List<Tbasignatura> resultsasignatura;
    List<Tbprofesor> resultsprofesor;
    List<Tbgrupoxasignaturaxprofesor> results;
    
  
   // Datos docente
   ObservableList<ObservableList> tbprofesor;
   Tbprofesor prefesor  = new Tbprofesor();
   TbprofesorJpaController profesorbd = new TbprofesorJpaController(); 
   
    //Datoa Asignatura
   
   ObservableList<ObservableList> tbasignatura;
    
   TbgrupoxasignaturaxprofesorJpaController gpabd = new TbgrupoxasignaturaxprofesorJpaController(); 
   Tbgrupoxasignaturaxprofesor gpa = new   Tbgrupoxasignaturaxprofesor();
   TbasignaturaJpaController materiabd = new TbasignaturaJpaController(); 
   Date fecha = new Date();
   DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    cargarDatosTabla();
    cargarcombogrupo();
    }   
    
    
    public void cargarcombogrupo() {
          
        TbgrupoJpaController grupobd = new TbgrupoJpaController();


        EntityManager em = grupobd.getEntityManager();
        TypedQuery<Tbgrupo> query = em.createNamedQuery("Tbgrupo.findAll", Tbgrupo.class);

        resultsgrupo = query.getResultList();

        em.close();
        for(int i=0;i<resultsgrupo.size();i++){
           grupocb.getItems().add(resultsgrupo.get(i).getIdentificacion().toString());
        }
    }

       @FXML
    public void seleccionardocente(MouseEvent event) {
   asignartb.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                if (asignartb != null) {
                    
                    String[] seleccionado = asignartb.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    int id = Integer.parseInt(seleccionado[0].substring(1));
                    System.out.print(id);
                   EntityManager em = gpabd.getEntityManager();
        TypedQuery<Tbgrupoxasignaturaxprofesor> query = em.createNamedQuery("Tbgrupoxasignaturaxprofesor.findById", Tbgrupoxasignaturaxprofesor.class);
                    Tbgrupoxasignaturaxprofesor profesor = query.setParameter("id", Integer.valueOf(id)).getSingleResult();

                  
                    cdocentetf.setText(profesor.getCedula().toString());
                    ndocentetf.setText(profesor.getCedula().getNombre() + profesor.getCedula().getApellido() );
                    casignaturatf.setText(profesor.getIdasignatura().getCodigo());
                    nasignaturatf.setText(profesor.getIdasignatura().getNombre());
                    cupotf.setText(profesor.getIdgrupo().toString());
                     
                   
                    
                }

               
            }

        });
         
    }
    
     
    public void cargarDatosTabla() {
        asignartb.getColumns().clear();
        EntityManager em = gpabd.getEntityManager();
        TypedQuery<Tbgrupoxasignaturaxprofesor> query = em.createNamedQuery("Tbgrupoxasignaturaxprofesor.findAll", Tbgrupoxasignaturaxprofesor.class);
        results = query.getResultList();

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
            asignartb.getColumns().addAll(titulo);
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
        asignartb.setItems(tbasignatura);
        em.close();
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
