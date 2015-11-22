/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbgrupoxasignaturaxprofesorJpaController;
import DAO.TbpreguntaJpaController;
import DAO.TbrespuestaxpreguntasxbloquesxencuestaJpaController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbpregunta;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class GenerarInformeController implements Initializable {
    @FXML
    private ComboBox<String> profesor;
    @FXML
    private ComboBox<String> asignatura;
    @FXML
    private ComboBox<Integer> grupo;
    List<Tbgrupoxasignaturaxprofesor> results;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TbgrupoxasignaturaxprofesorJpaController userDB = new TbgrupoxasignaturaxprofesorJpaController();
        Tbgrupoxasignaturaxprofesor profe = new Tbgrupoxasignaturaxprofesor();

        EntityManager em = userDB.getEntityManager();
        TypedQuery<Tbgrupoxasignaturaxprofesor> query = em.createNamedQuery("Tbgrupoxasignaturaxprofesor.findAll", Tbgrupoxasignaturaxprofesor.class);
//        System.out.println(query);
        results = query.getResultList();
//        System.out.println(results);
        em.close();
        
        for(int i=0;i<results.size();i++){
            String nompro = results.get(i).getCedula().getNombre() + " " + results.get(i).getCedula().getApellido();
            profesor.getItems().add(nompro);
        } 
    }    

    @FXML
    private void CargaPorProfesor(ActionEvent event) {
        profesor.setDisable(true);
        asignatura.setDisable(false);
        for(int i=0;i<results.size();i++){
            String nompro = results.get(i).getCedula().getNombre() + " " + results.get(i).getCedula().getApellido();
            if(nompro.equals(profesor.getValue())){
                asignatura.getItems().add(results.get(i).getIdasignatura().getNombre());
            }
        }
    }

    @FXML
    private void CargarPorAsignatura(ActionEvent event) {
        asignatura.setDisable(true);
        grupo.setDisable(false);
        for(int i=0;i<results.size();i++){
            if(results.get(i).getIdasignatura().getNombre().equals(asignatura.getValue())){
                grupo.getItems().add(results.get(i).getIdgrupo().getIdentificacion());
            }
        }
    }

    @FXML
    private void AccionGenerar(ActionEvent event) {
        int sumarespuesta =0;
        int cantidadrespuesta =0;
        int promedio = 0;
        int respuestas = 0;
        
        TbrespuestaxpreguntasxbloquesxencuestaJpaController userDB = new TbrespuestaxpreguntasxbloquesxencuestaJpaController();
        Tbrespuestaxpreguntasxbloquesxencuesta respuesta = new Tbrespuestaxpreguntasxbloquesxencuesta();

        EntityManager em2 = userDB.getEntityManager();
        TypedQuery<Tbrespuestaxpreguntasxbloquesxencuesta> query2 = em2.createNamedQuery("Tbrespuestaxpreguntasxbloquesxencuesta.findAll", Tbrespuestaxpreguntasxbloquesxencuesta.class);
//        System.out.println(query);
        List<Tbrespuestaxpreguntasxbloquesxencuesta> results2 = query2.getResultList();
//        System.out.println(results);
        em2.close();
        
        TbpreguntaJpaController preg = new TbpreguntaJpaController();
        Tbpregunta pregunta = new Tbpregunta();

        EntityManager em = preg.getEntityManager();
        TypedQuery<Tbpregunta> query = em.createNamedQuery("Tbpregunta.findAll", Tbpregunta.class);
//        System.out.println(query);
        List<Tbpregunta> results3 = query.getResultList();
//        System.out.println(results);
        em.close();

//        System.out.println(results);
//        System.out.println(results2);
//        System.out.println(results3);
        for(int i=0;i<results.size();i++){
            
            respuestas = 0;
            sumarespuesta = 0;
            cantidadrespuesta = 0;
            String nompro = results.get(i).getCedula().getNombre() + " " + results.get(i).getCedula().getApellido();
            if(nompro.equals(profesor.getValue()) && results.get(i).getIdasignatura().getNombre().equals(asignatura.getValue()) && results.get(i).getIdgrupo().getIdentificacion() == grupo.getValue()){

                for(int j=0;j<results2.size();j++){
                    if(results2.get(j).getIdgrupoxasignaturaxprofesor().getId() == results.get(i).getId()){
                        if(results2.get(j).getRespuesta() > 0 && results2.get(j).getIdbloquexpregunta().getIdpregunta().getId() == results3.get(j).getId()){
                            sumarespuesta += results2.get(j).getRespuesta();
                            cantidadrespuesta++;
                            respuestas++;
                        }else{
                            respuestas++;
                        }
                    }
                }
                promedio = sumarespuesta/cantidadrespuesta;
                System.out.println(promedio + " " + results2.get(i).getIdbloquexpregunta().getIdpregunta().getNombre() + "y la cantidad de estudiantes que respondieron fueron " + respuestas);
            }
        }
        
    }
}
