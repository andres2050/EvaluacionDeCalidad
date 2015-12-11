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
import java.math.BigDecimal;
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
import javafx.scene.control.Label;
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
    @FXML private TextField casignaturatf;
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
    TbgrupoJpaController grupobd = new TbgrupoJpaController();
    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
    @FXML
    private Button cargar1bt;
    @FXML
    private Button cargar2bt;
    @FXML
    private Button cargar3bt;
    Tbgrupoxasignaturaxprofesor profesor;
    @FXML
    private Label mensaje2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosTabla();
        cargarcombogrupo();
        limpiar();
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
                    profesor = query.setParameter("id", Integer.valueOf(id)).getSingleResult();


                    cdocentetf.setText(profesor.getCedula().getCedula().toString());
                    casignaturatf.setText(profesor.getIdasignatura().getCodigo());
                    cupotf.setText(Integer.toString(profesor.getCupo()));
                    grupocb.setValue(profesor.getIdgrupo().getIdentificacion().toString());
                    modificarbt.setDisable(false);
                    asignarbt.setDisable(true);


                }


            }

        });
        
    }
    
    public void limpiar(){
        modificarbt.setDisable(true);
        asignarbt.setDisable(false);
        cargarDatosTabla();
        cdocentetf.setText("");
        casignaturatf.setText("");
        cupotf.setText("");
        grupocb.setValue("");
        mensaje2.setVisible(false);
    }
     
    @FXML
    public void cargarDatosTabla() {
        asignartb.getColumns().clear();
        EntityManager em = gpabd.getEntityManager();
        TypedQuery<Tbgrupoxasignaturaxprofesor> query = em.createNamedQuery("Tbgrupoxasignaturaxprofesor.findAll", Tbgrupoxasignaturaxprofesor.class);
        results = query.getResultList();

        String[] titulos = {
            "ID",
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
            String id = gap.getId().toString();
            row.add(id);
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
        asignartb.getColumns().clear();
        EntityManager em = profesorbd.getEntityManager();
        TypedQuery<Tbprofesor> query = em.createNamedQuery("Tbprofesor.findAll", Tbprofesor.class);
        List<Tbprofesor> results = query.getResultList();

        String[] titulos = {
            "Cedula",
            "Nombre",
            "Apellido",
           
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
//             Asignamos un tamaño a ls columnnas
            titulo.setMinWidth(160);

//             Centrar los datos de la tabla
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

        tbprofesor = FXCollections.observableArrayList();
        for (Tbprofesor profe : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(profe.getCedula().toString());
            row.add(profe.getNombre());
            row.add(profe.getApellido());
            
           tbprofesor.addAll(row);
        }
        asignartb.setItems(tbprofesor);
       
        em.close();

          
    }
    
    @FXML 
    public void Buscarasignatura (ActionEvent event) throws Exception {
        asignartb.getColumns().clear();
        EntityManager em = materiabd.getEntityManager();
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
        for (Tbasignatura asig : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(asig.getCodigo());
            row.add(asig.getNombre());
            row.add(df1.format(asig.getFechacreacion()));
            System.out.print(asig.getFechacreacion());
            String fecha = asig.getFechamodificacion() == null ? asig.getFechamodificacion()+"":df1.format(asig.getFechamodificacion());
            row.add(fecha);
           tbasignatura.addAll(row);
        }
        asignartb.setItems(tbasignatura);
        em.close();
    }
    
    @FXML 
    public void Crearasignacion (ActionEvent event) throws Exception {
        if(!cdocentetf.getText().isEmpty() && !casignaturatf.getText().isEmpty() && grupocb.getValue() != null && !cupotf.getText().isEmpty()){
            Tbprofesor docente = new Tbprofesor();
            Tbasignatura asignatura = new Tbasignatura();
            Tbgrupo grupo = new Tbgrupo();
            docente.setCedula(new BigDecimal(cdocentetf.getText()));
            asignatura.setCodigo(casignaturatf.getText());
            grupo.setIdentificacion(Integer.parseInt(grupocb.getValue()));
            EntityManager em = profesorbd.getEntityManager();
            TypedQuery<Tbprofesor> query = em.createNamedQuery("Tbprofesor.findByCedula", Tbprofesor.class);
//            System.out.println(query);
            docente = query.setParameter("cedula", docente.getCedula()).getSingleResult();
//            System.out.println(results);
            em.close();
            em = materiabd.getEntityManager();
            TypedQuery<Tbasignatura> query2 = em.createNamedQuery("Tbasignatura.findByCodigo", Tbasignatura.class);
            asignatura = query2.setParameter("codigo", asignatura.getCodigo()).getSingleResult();
            em.close();
            em = grupobd.getEntityManager();
            TypedQuery<Tbgrupo> query3 = em.createNamedQuery("Tbgrupo.findByIdentificacion", Tbgrupo.class);
            grupo = query3.setParameter("identificacion", grupo.getIdentificacion()).getSingleResult();
            em.close();
            Tbgrupoxasignaturaxprofesor asignard = new Tbgrupoxasignaturaxprofesor();
            TbgrupoxasignaturaxprofesorJpaController asignardbd = new TbgrupoxasignaturaxprofesorJpaController();
            asignard.setCedula(docente);
            asignard.setIdasignatura(asignatura);
            asignard.setIdgrupo(grupo);
            asignard.setCupo(Integer.parseInt(cupotf.getText()));
            boolean crear = true;
            for(int i=0;i<results.size();i++){
                if(results.get(i).getCedula().getCedula().equals(docente.getCedula()) && results.get(i).getIdasignatura().getCodigo().equals(asignatura.getCodigo()) && results.get(i).getIdgrupo().getIdentificacion().equals(grupo.getIdentificacion())){
                    crear = false;
                }
            }
            
            if(crear == true){
                asignardbd.create(asignard);
                cargarDatosTabla();
                limpiar();
            }else{
                limpiar();
                mensaje2.setVisible(true);
            }
        }
    }
    
    @FXML 
    public void Modificarasignacion(ActionEvent event) throws Exception {
        if(!cdocentetf.getText().isEmpty() && !casignaturatf.getText().isEmpty() && grupocb.getValue() != null && !cupotf.getText().isEmpty()){
            Tbprofesor docente = new Tbprofesor();
            Tbasignatura asignatura = new Tbasignatura();
            Tbgrupo grupo = new Tbgrupo();
            docente.setCedula(new BigDecimal(cdocentetf.getText()));
            asignatura.setCodigo(casignaturatf.getText());
            grupo.setIdentificacion(Integer.parseInt(grupocb.getValue()));
            EntityManager em = profesorbd.getEntityManager();
            TypedQuery<Tbprofesor> query = em.createNamedQuery("Tbprofesor.findByCedula", Tbprofesor.class);
//            System.out.println(query);
            docente = query.setParameter("cedula", docente.getCedula()).getSingleResult();
//            System.out.println(results);
            em.close();
            em = materiabd.getEntityManager();
            TypedQuery<Tbasignatura> query2 = em.createNamedQuery("Tbasignatura.findByCodigo", Tbasignatura.class);
            asignatura = query2.setParameter("codigo", asignatura.getCodigo()).getSingleResult();
            em.close();
            em = grupobd.getEntityManager();
            TypedQuery<Tbgrupo> query3 = em.createNamedQuery("Tbgrupo.findByIdentificacion", Tbgrupo.class);
            grupo = query3.setParameter("identificacion", grupo.getIdentificacion()).getSingleResult();
            em.close();
            
            TbgrupoxasignaturaxprofesorJpaController asignardbd = new TbgrupoxasignaturaxprofesorJpaController();
            profesor.setCedula(docente);
            profesor.setIdasignatura(asignatura);
            profesor.setIdgrupo(grupo);
            profesor.setCupo(Integer.parseInt(cupotf.getText()));
            asignardbd.edit(profesor);
            limpiar();
        }
    }
    
}
