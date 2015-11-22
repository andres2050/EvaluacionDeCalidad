/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Estudiante;

import DAO.TbbloquesxencuestaJpaController;
import DAO.TbbloquesxpreguntaJpaController;
import DAO.TbencuestasxusuarioJpaController;
import DAO.TbencuestaxasignaturaJpaController;
import DAO.TbrespuestaxpreguntasxbloquesxencuestaJpaController;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import modelo.Tbencuestasxusuario;
import vista.Activo;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tbbloquesxencuesta;
import modelo.Tbbloquesxpregunta;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Tbencuestaxasignatura;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class EvaluacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Tbencuestasxusuario seleccion;
    Calendar c = new GregorianCalendar();
    Date d = new Date();
    @FXML
    private Label evaluacion;
    @FXML
    private Label fecha;
    @FXML
    private Label asignatura;
    @FXML
    private Label grupo;
    @FXML
    private Label profesor;

    
   // Declaramos la tabla y las columnas
   private TableColumn titulo;
   ObservableList<ObservableList> tbpregunta;
   DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
//   Date fecha = new Date();
    @FXML
    private TableView tablabloques;
    @FXML
    private Button siguientebt;
    List<Tbbloquesxencuesta> bloqxencu;
    List<Tbbloquesxpregunta> bloqxpreg;
    List<Tbencuestaxasignatura> encuxasig;
    TbbloquesxencuestaJpaController bloqxencubd = new TbbloquesxencuestaJpaController();
    TbbloquesxpreguntaJpaController bloqxpregbd = new TbbloquesxpreguntaJpaController();
    TbencuestaxasignaturaJpaController encuxasigbd = new TbencuestaxasignaturaJpaController();
    Tbbloquesxencuesta[] filtrado;
    int bloqueActivo = 0;
    private Vector respu = new Vector();
    int Cantidad =0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c.setTime(d);
       
        evaluacion.setText(Activo.filtrado[Activo.botonActivo].getIdencuestaxasignatura().getIdencuesta().getNombre());
        fecha.setText(fecha.getText() + c.get(Calendar.DATE) + "/" + ((c.get(Calendar.MONTH))+1) + "/" + c.get(Calendar.YEAR));
        asignatura.setText(asignatura.getText() + Activo.filtrado[Activo.botonActivo].getIdgrupoxasignaturaxprofesor().getIdasignatura().getCodigo() + " " + Activo.filtrado[Activo.botonActivo].getIdgrupoxasignaturaxprofesor().getIdasignatura().getNombre());
        grupo.setText(grupo.getText() + Activo.filtrado[Activo.botonActivo].getIdgrupoxasignaturaxprofesor().getIdgrupo().getIdentificacion());
        profesor.setText(profesor.getText() + Activo.filtrado[Activo.botonActivo].getIdgrupoxasignaturaxprofesor().getCedula().getNombre() +  " " + Activo.filtrado[Activo.botonActivo].getIdgrupoxasignaturaxprofesor().getCedula().getApellido());
        cargar();
        filtrado = new Tbbloquesxencuesta[bloqxencu.size()];
        for(int i=0; i<bloqxencu.size();i++){
            if(Activo.filtrado[Activo.botonActivo].getIdencuestaxasignatura().getIdencuesta().getId() == bloqxencu.get(i).getIdencuesta().getId()){
                filtrado[i] = bloqxencu.get(i);
            }
        }
        cargarDatosTabla();
    }
    
    private void cargarDatosTabla() {
        tablabloques.getColumns().clear();
        String bloque = filtrado[bloqueActivo].getIdbloque().getNombre();
        String[] titulos = {
            bloque,
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
            titulo.setMinWidth(480);

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
        for (Tbbloquesxpregunta pregu : bloqxpreg) {
            ObservableList<String> row = FXCollections.observableArrayList();
            if(pregu.getIdbloque().getId() == filtrado[bloqueActivo].getIdbloque().getId()){
                row.add(pregu.getIdpregunta().getNombre());
                tbpregunta.addAll(row);
            }
          
        }
        tablabloques.setItems(tbpregunta);
        
        
        //Agregar los RadioButton a las Preguntas
        TableColumn c2 = new TableColumn("Calificacion");

            c2.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                @Override
                public TableCell<String, String> call(TableColumn<String, String> p) {
                    TableCell cell = new TableCell() {
                        @Override
                        protected void updateItem(Object t, boolean bln) {
                            super.updateItem(t, bln);
                            if (bln) {                               
                                setText(null);
                                setGraphic(null);
                            }else{
                                HBox hb = new HBox(6);
                                final ToggleGroup group = new ToggleGroup();
                                final RadioButton btn1 = new RadioButton("1");
                                final RadioButton btn2 = new RadioButton("2");
                                final RadioButton btn3 = new RadioButton("3");
                                final RadioButton btn4 = new RadioButton("4");
                                final RadioButton btn5 = new RadioButton("5");
                                final RadioButton btnNo = new RadioButton("No Aplica");
                                btn1.setToggleGroup(group);
                                btn2.setToggleGroup(group);
                                btn3.setToggleGroup(group);
                                btn4.setToggleGroup(group);
                                btn5.setToggleGroup(group);
                                btnNo.setToggleGroup(group);
                                hb.getChildren().add(btn1);
                                hb.getChildren().add(btn2);
                                hb.getChildren().add(btn3);
                                hb.getChildren().add(btn4);
                                hb.getChildren().add(btn5);
                                hb.getChildren().add(btnNo);
                                setGraphic(hb);
                                setAlignment(Pos.CENTER); //Setting the Alignment
                                btn1.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        p.getTableView().getSelectionModel().select(getIndex());
                                        String Pregunta = tablabloques.getSelectionModel().getSelectedItem().toString();
                                        if (Pregunta != null) {
                                            Coincidencia(new Respuestas(filtrado[bloqueActivo].getIdbloque().getNombre(), Pregunta, 1));
                                        }
                                    }
                                });
                                btn2.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        p.getTableView().getSelectionModel().select(getIndex());
                                        String Pregunta = tablabloques.getSelectionModel().getSelectedItem().toString();
                                        if (Pregunta != null) {
                                            Coincidencia(new Respuestas(filtrado[bloqueActivo].getIdbloque().getNombre(), Pregunta, 2));
                                        }
                                    }
                                });
                                btn3.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        p.getTableView().getSelectionModel().select(getIndex());
                                        String Pregunta = tablabloques.getSelectionModel().getSelectedItem().toString();
                                        if (Pregunta != null) {
                                            Coincidencia(new Respuestas(filtrado[bloqueActivo].getIdbloque().getNombre(), Pregunta, 3));
                                        }
                                    }
                                });
                                btn4.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        p.getTableView().getSelectionModel().select(getIndex());
                                        String Pregunta = tablabloques.getSelectionModel().getSelectedItem().toString();
                                        if (Pregunta != null) {
                                            Coincidencia(new Respuestas(filtrado[bloqueActivo].getIdbloque().getNombre(), Pregunta, 4));
                                        }
                                    }
                                });
                                btn5.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        p.getTableView().getSelectionModel().select(getIndex());
                                        String Pregunta = tablabloques.getSelectionModel().getSelectedItem().toString();
                                        if (Pregunta != null) {
                                            Coincidencia(new Respuestas(filtrado[bloqueActivo].getIdbloque().getNombre(), Pregunta, 5));
                                        }
                                    }
                                });
                                btnNo.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        p.getTableView().getSelectionModel().select(getIndex());
                                        String Pregunta = tablabloques.getSelectionModel().getSelectedItem().toString();
                                        if (Pregunta != null) {
                                            Coincidencia(new Respuestas(filtrado[bloqueActivo].getIdbloque().getNombre(), Pregunta, 0));
                                        }
                                    }
                                });
                            }
                        }
                    };
                    return cell;
                }
            });
            tablabloques.getColumns().add(c2);
        
    } 
    
    private void cargar(){
        EntityManager em;
        bloqxencubd = new TbbloquesxencuestaJpaController();
        em = bloqxencubd.getEntityManager();
        TypedQuery<Tbbloquesxencuesta> query = em.createNamedQuery("Tbbloquesxencuesta.findAll", Tbbloquesxencuesta.class);
        bloqxencu = query.getResultList();
        em.close();
        
        bloqxpregbd = new TbbloquesxpreguntaJpaController();
        em = bloqxpregbd.getEntityManager();
        TypedQuery<Tbbloquesxpregunta> query2 = em.createNamedQuery("Tbbloquesxpregunta.findAll", Tbbloquesxpregunta.class);
        bloqxpreg = query2.getResultList();
        em.close();
        
        encuxasigbd = new TbencuestaxasignaturaJpaController();
        em = encuxasigbd.getEntityManager();
        TypedQuery<Tbencuestaxasignatura> query3 = em.createNamedQuery("Tbencuestaxasignatura.findAll", Tbencuestaxasignatura.class);
        encuxasig = query3.getResultList();
        em.close();
    }
    

    @FXML
    private void AccionSiguiente(ActionEvent event) {
        if(siguientebt.getText().equals("Terminar") && Comprobante()){
            for(int i=0;i<respu.size();i++){
                TbrespuestaxpreguntasxbloquesxencuestaJpaController respuestabd = new TbrespuestaxpreguntasxbloquesxencuestaJpaController();
                Tbrespuestaxpreguntasxbloquesxencuesta respuesta = new Tbrespuestaxpreguntasxbloquesxencuesta();
                
                respuesta.setCodigousuario(Activo.filtrado[Activo.botonActivo].getCodigousuario());
                respuesta.setFecha(d);
                for(int j=0;j<bloqxencu.size();j++){
                    if(bloqxencu.get(j).getIdencuesta().getId() == Activo.filtrado[Activo.botonActivo].getIdencuestaxasignatura().getIdencuesta().getId() && bloqxencu.get(j).getIdbloque().getNombre().equals(((Respuestas)respu.get(i)).getGrupo())){
                        respuesta.setIdbloquexencuesta(bloqxencu.get(j));
//                        System.out.println("Coincide");
                    }
                }
                respuesta.setIdencuestaxasignatura(Activo.filtrado[Activo.botonActivo].getIdencuestaxasignatura());
                respuesta.setIdgrupoxasignaturaxprofesor(Activo.filtrado[Activo.botonActivo].getIdgrupoxasignaturaxprofesor());
                respuesta.setRespuesta(((Respuestas)respu.get(i)).getRespuesta());
                for(int j=0;j<bloqxpreg.size();j++){
                    String pre = "[" + bloqxpreg.get(j).getIdpregunta().getNombre() + "]";
                    if(bloqxpreg.get(j).getIdbloque().getNombre().equals(((Respuestas)respu.get(j)).getGrupo()) && pre.equals(((Respuestas)respu.get(j)).getPregunta().toString())){
                        respuesta.setIdbloquexpregunta(bloqxpreg.get(j));
//                        System.out.println("Imprime");
                    } 
                }
                respuestabd.create(respuesta);
                
            }
            TbencuestasxusuarioJpaController terminar = new TbencuestasxusuarioJpaController();
            Activo.filtrado[Activo.botonActivo].setEstado("RESPONDIDA");
            try {
                terminar.edit(Activo.filtrado[Activo.botonActivo]);
            } catch (Exception ex) {
                Logger.getLogger(EvaluacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String Ruta = "Estudiante/PrincipalUsuario.fxml";
            new Activo().cambioAnchor(event, Ruta);
            
        }
        if(siguientebt.getText().equals("Siguiente") && Comprobante()){
            if(bloqueActivo == filtrado.length-2){
                siguientebt.setText("Terminar");
            }
            bloqueActivo++;
            cargarDatosTabla();
        }
    }
    
    private void Coincidencia(Respuestas Pregunta){
        Boolean coincidencia = false;
        int reemplazar = 0;
        for(int i=0;i<respu.size();i++){
            if(Pregunta.getPregunta().equals(((Respuestas)respu.get(i)).getPregunta())){
                coincidencia = true;
                reemplazar = i;
            }
        }
        if(coincidencia){
            respu.remove(reemplazar);
            respu.add(reemplazar, Pregunta);
        }else{
            respu.addElement(Pregunta);
        }
    }
    
    private Boolean Comprobante(){
        Boolean cantidad = true;
        Cantidad += tablabloques.getItems().size();
        if(Cantidad != respu.size()){
            cantidad = false;
            Cantidad -= tablabloques.getItems().size();
        }
        return cantidad;
    }
    
}

       
        

   
