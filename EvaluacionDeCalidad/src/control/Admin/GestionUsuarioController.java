/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbusuarioJpaController;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.Tbtipo;
import modelo.Tbusuario;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Cecilia Segura
 */
public class GestionUsuarioController implements Initializable {
 // Declaramos los botones
    @FXML private Button crearbt;
    @FXML private Button modificarbt;
    @FXML private Button consultarbt;
    @FXML private Button limpiarbt;
    
    
    // Declaramos los textfileds
    @FXML private TextField codigotf;
    @FXML private TextField nombretf;
    @FXML private TextField apellidotf;
    @FXML private TextField contrasenatf;
    @FXML private TextField emailtf;
    @FXML private TextField telefonotf;
    @FXML private TextField direciontf;
    @FXML private ComboBox<String> tipocb;
    @FXML private Label mensajelb;
    @FXML private Label mensaje2lb;

   // Declaramos la tabla y las columnas
   @FXML
   private TableView usuariotb;
   private TableColumn titulo;
   List<Tbtipo> results;
   
   //Otros
   ObservableList<ObservableList> tbusuario;
   Tbusuario usuario = new Tbusuario();
   TbusuarioJpaController userbd = new TbusuarioJpaController(); 
   
 
   
   
      @FXML 
      public void Crearusuario (ActionEvent event) throws Exception {
        mensajelb.setVisible(false);
        mensaje2lb.setVisible(false);
            
        Tbusuario nuevo = new Tbusuario();
        
        if(!nombretf.getText().isEmpty() && !apellidotf.getText().isEmpty() && !codigotf.getText().isEmpty() && !contrasenatf.getText().isEmpty() && !emailtf.getText().isEmpty() && tipocb.getValue() != null){
            
           
            nuevo.setCodigo(new BigDecimal(codigotf.getText()));

            EntityManager em = userbd.getEntityManager();
            TypedQuery<Tbusuario> query = em.createNamedQuery("Tbusuario.findByCodigo", Tbusuario.class);
//            System.out.println(query);
            List<Tbusuario> results2 = query.setParameter("codigo", nuevo.getCodigo()).getResultList();
//            System.out.println(results);
            em.close();
        
            if(results2.isEmpty()){
                for(int i=0;i<results.size();i++){
                    if(tipocb.getValue().equals(results.get(i).getPrivilegios())){
                        nuevo.setIdtipo(results.get(i));
                    }
                }
                nuevo.setNombre(nombretf.getText());
                nuevo.setApellido(apellidotf.getText());
                nuevo.setContrasena(DigestUtils.md5Hex(contrasenatf.getText()));
                nuevo.setEmail(emailtf.getText());
                if(!telefonotf.getText().isEmpty()){
                    nuevo.setTelefono(telefonotf.getText());
                }
                    
                if(!direciontf.getText().isEmpty()){
                    nuevo.setDireccion(direciontf.getText());
                }
                try {
                    userbd.create(nuevo);
                } catch (Exception ex) {
                    Logger.getLogger(GestionUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                mensaje2lb.setVisible(true);
            }
        
            
        }else{
            mensajelb.setVisible(true);
            mensaje2lb.setVisible(false);
        }
        cargarDatosTabla();
        Limpiardatos();
    }
   
   @FXML
    public void Modificarusuario (ActionEvent event) throws Exception {
        if(!nombretf.getText().isEmpty() && !apellidotf.getText().isEmpty() && !codigotf.getText().isEmpty() && !emailtf.getText().isEmpty() && tipocb.getValue() != null){
            EntityManager em = userbd.getEntityManager();
            Tbusuario modificar = new Tbusuario();
            modificar.setCodigo(new BigDecimal(codigotf.getText()));
            codigotf.setDisable(true);
            TypedQuery<Tbusuario> query = em.createNamedQuery("Tbusuario.findByCodigo", Tbusuario.class);
            modificar = query.setParameter("codigo", modificar.getCodigo()).getSingleResult();
            modificar.setNombre(nombretf.getText().trim().toUpperCase());
            modificar.setApellido(apellidotf.getText());
            if(!contrasenatf.getText().isEmpty()){
                modificar.setContrasena(contrasenatf.getText());
            }
            modificar.setEmail(emailtf.getText());
            modificar.setTelefono(telefonotf.getText());
            modificar.setDireccion(direciontf.getText());

            userbd.edit(modificar);
            cargarDatosTabla();
            Limpiardatos();
            em.close();
        }else{
            mensajelb.setVisible(true);
        }
    }
    
     
    @FXML
    public void Limpiardatos() {
       codigotf.setText("");
       nombretf.setText("");
       apellidotf.setText("");
       direciontf.setText("");
       telefonotf.setText("");
       contrasenatf.setText("");
       emailtf.setText("");
       codigotf.setDisable(false);
       crearbt.setDisable(false);
       consultarbt.setDisable(false);
       tipocb.setValue(null);
       modificarbt.setDisable(true);
    }

    @FXML
    private void Consultarusuario(ActionEvent event) {
        
        Tbusuario us = new Tbusuario();
        us.setCodigo(new BigDecimal(codigotf.getText()));     
        EntityManager em = userbd.getEntityManager();
        TypedQuery<Tbusuario> query = em.createNamedQuery("Tbusuario.findByCodigo", Tbusuario.class);
        Tbusuario usuario = query.setParameter("codigo",us.getCodigo()).getSingleResult();
        codigotf.setText(usuario.getCodigo().toString());
        nombretf.setText(usuario.getNombre());
        apellidotf.setText(usuario.getApellido());
        direciontf.setText(usuario.getDireccion());
        telefonotf.setText(usuario.getTelefono());
        emailtf.setText(usuario.getTelefono());
        em.close();

    }
    
     @FXML
    public void Seleccionarusuario(MouseEvent event) {
        usuariotb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (usuariotb != null) {
                    codigotf.setDisable(true);
                    crearbt.setDisable(true);
                    consultarbt.setDisable(true);
                    modificarbt.setDisable(false);
                    String[] seleccionado = usuariotb.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    int id = Integer.parseInt(seleccionado[0].substring(1));
                    System.out.print(id);
                    EntityManager em = userbd.getEntityManager();
                    TypedQuery<Tbusuario> query = em.createNamedQuery("Tbusuario.findByCodigo", Tbusuario.class);
                    Tbusuario user = query.setParameter("codigo", Integer.valueOf(id)).getSingleResult();
                    codigotf.setText(user.getCodigo().toString());
                    nombretf.setText(user.getNombre());
                    apellidotf.setText(user.getApellido());
                    emailtf.setText(user.getEmail());
                    direciontf.setText(user.getDireccion());
                    telefonotf.setText(user.getTelefono());
                    tipocb.setValue(user.getIdtipo().getPrivilegios());
                    
                }

               
            }

        });
       
    }

    private void cargarDatosTabla() {
        usuariotb.getColumns().clear();
        EntityManager em = userbd.getEntityManager();
        TypedQuery<Tbusuario> query = em.createNamedQuery("Tbusuario.findAll", Tbusuario.class);
        List<Tbusuario> results = query.getResultList();

        String[] titulos = {
            "Codigo",
            "Nombre",
            "Apellido",
            "Correo",
            "Direccion",
            "Telefono",
            "Tipo de cuenta",
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
            usuariotb.getColumns().addAll(titulo);
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

        tbusuario = FXCollections.observableArrayList();
        for (Tbusuario usua : results) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(usua.getCodigo().toString());
            row.add(usua.getNombre());
            row.add(usua.getApellido());
            row.add(usua.getEmail()+ "");
            row.add(usua.getDireccion()+"");
            row.add(usua.getTelefono()+"");
            row.add(usua.getIdtipo().getPrivilegios());
           tbusuario.addAll(row);
        }
        usuariotb.setItems(tbusuario);
       
        em.close();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarDatosTabla();

        EntityManager em = userbd.getEntityManager();
        TypedQuery<Tbtipo> query = em.createNamedQuery("Tbtipo.findAll", Tbtipo.class);
        System.out.println(query);
        results = query.getResultList();
        System.out.println(results);
        em.close();
        for(int i=0;i<results.size();i++){
            tipocb.getItems().add(results.get(i).getPrivilegios());
        }
        mensajelb.setVisible(false);
        mensaje2lb.setVisible(false);
        modificarbt.setDisable(true);
    }   
     
    

    }   


