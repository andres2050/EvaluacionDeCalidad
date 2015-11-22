/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Estudiante;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import vista.Activo;
import DAO.TbencuestasxusuarioJpaController;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import modelo.Tbencuestasxusuario;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class PrincipalUsuarioController implements Initializable {
    @FXML
    private Button Materia1;
    @FXML
    private Button Materia2;
    @FXML
    private Button Materia3;
    @FXML
    private Button Materia4;
    @FXML
    private Button Materia5;
    @FXML
    private Button Materia6;
    @FXML
    private Button Materia7;
    @FXML
    private Button Materia8;
    private List<Tbencuestasxusuario> results;
    @FXML
    private Label Espacio;
    @FXML
    private AnchorPane fondo;
    
    //Se debe tener un arreglo de asignatura+grupo+profesor+encuesta con los datos de cada materia traida por el boton para poder decidir que accion tomar  
    
    //private Button[] Materia = {Materia1, Materia2, Materia3, Materia4, Materia5, Materia6, Materia7, Materia8}; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //quiero buscar una manera de hacer esto con los 8 botones y usar MateriaNumero.setVisible(false) y MateriaNumero.setDisable(true) con los que no se usen
        Espacio.setVisible(false);
        Button[] Materia = {Materia1, Materia2, Materia3, Materia4, Materia5, Materia6, Materia7, Materia8}; 
        Activo.filtrado = new Tbencuestasxusuario[8];
        TbencuestasxusuarioJpaController userDB = new TbencuestasxusuarioJpaController();
        EntityManager em = userDB.getEntityManager();
        TypedQuery<Tbencuestasxusuario> query = em.createNamedQuery("Tbencuestasxusuario.findAll", Tbencuestasxusuario.class);
//        System.out.println(query);
        results = query.getResultList();
//        System.out.println();
        
        em.close();
        
        int ocupado = 0;
        for(int i=0; i<results.size(); i++){
            boolean coincide = results.get(i).getCodigousuario().getCodigo().toString().equals(Activo.usuario.getCodigo().toString());
            if(coincide){
                Materia[ocupado].setStyle("-fx-font: 18 arial ; -fx-base: #ef3b46;");
                Activo.filtrado[ocupado] = results.get(i);
                Materia[ocupado].setText(Activo.filtrado[ocupado].getIdgrupoxasignaturaxprofesor().getIdasignatura().getCodigo() + " " + Activo.filtrado[ocupado].getIdgrupoxasignaturaxprofesor().getIdasignatura().getNombre() + " GRUPO: " + Activo.filtrado[ocupado].getIdgrupoxasignaturaxprofesor().getIdgrupo().getIdentificacion() + " \n" + "PROFESOR: " + Activo.filtrado[ocupado].getIdgrupoxasignaturaxprofesor().getCedula().getNombre() + " " + Activo.filtrado[ocupado].getIdgrupoxasignaturaxprofesor().getCedula().getApellido());
                if(results.get(i).getEstado().equals("RESPONDIDA")){
                    Materia[ocupado].setDisable(true);
                }
                ocupado++;
            }
        }
        
        for(int i=ocupado; i<Materia.length; i++){
            Materia[i].setVisible(false);
            Materia[i].setDisable(true);
            Materia[i].setText(null);
            Materia[i].setLayoutY(0);
            Espacio.setLayoutY(Espacio.getLayoutY()- 60);
        }
        
        fondo.setStyle("-fx-background-image: url(/imagenes/fondousu.jpg); -fx-background-size: 100% 100%;");
    }

    @FXML
    private void AccionCalificar(ActionEvent event) {
        String boton = ((Button)event.getSource()).getId();
        String Ruta = "Estudiante/Evaluacion.fxml";
        
        if(boton.equals("Materia1")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 0;
        }
        if(boton.equals("Materia2")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 1;
        }
        if(boton.equals("Materia3")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 2;
        }
        if(boton.equals("Materia4")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 3;
        }
        if(boton.equals("Materia5")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 4;
        }
        if(boton.equals("Materia6")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 5;
        }
        if(boton.equals("Materia7")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 6;
        }
        if(boton.equals("Materia8")){
            new Activo().cambioAnchor(event, Ruta);
            Activo.botonActivo = 7;
        }
        //Un solo metodo De accion para los 8 botones, se reconoce la accion por medio de sus identificadores
    }
    
}
