/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Admin;

import DAO.TbencuestaxasignaturaJpaController;
import DAO.TbgrupoxasignaturaxprofesorJpaController;
import DAO.TbinformesJpaController;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tbencuestaxasignatura;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbinformes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    private final String Logo = "../../imagenes/univalle.png";
    private final String archivo = "Reporte/Reporte.jasper";
    @FXML
    private Button generarbt;
    Date fecha = new Date();
    DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);

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
        limpiar();
    }    

    @FXML
    private void CargaPorProfesor(ActionEvent event) {
        profesor.setDisable(true);
        asignatura.setDisable(false);
        asignatura.getItems().clear();
        for(int i=0;i<results.size();i++){
            String nompro = results.get(i).getCedula().getNombre() + " " + results.get(i).getCedula().getApellido();
            if(nompro.equals(profesor.getValue())){
                asignatura.getItems().add(results.get(i).getIdasignatura().getNombre());
            }
        }
    }

    @FXML
    private void limpiar(){
        profesor.setValue(null);
        asignatura.setValue(null);
        grupo.setValue(null);
        generarbt.setDisable(true);
        profesor.setDisable(false);
        asignatura.setDisable(true);
        grupo.setDisable(true);
    }
    
    @FXML
    private void CargarPorAsignatura(ActionEvent event) {
        asignatura.setDisable(true);
        grupo.setDisable(false);
        grupo.getItems().clear();
        for(int i=0;i<results.size();i++){
            if(results.get(i).getIdasignatura().getNombre().equals(asignatura.getValue())){
                grupo.getItems().add(results.get(i).getIdgrupo().getIdentificacion());
            }
        }
    }

    @FXML
    private void AccionGenerar(ActionEvent event) throws SQLException {
        grupo.setDisable(true);
        for(int i=0;i<results.size();i++){
            String nompro = results.get(i).getCedula().getNombre() + " " + results.get(i).getCedula().getApellido();
            if(nompro.equals(profesor.getValue()) && results.get(i).getIdasignatura().getNombre().equals(asignatura.getValue()) && results.get(i).getIdgrupo().getIdentificacion() == grupo.getValue()){
                JasperReport jr = null;
                try {
                    Map parametro = new HashMap();
                    parametro.put("FiltroProfesor", results.get(i).getCedula().getCedula());
                    parametro.put("FiltroMateria", results.get(i).getIdasignatura().getCodigo());
                    parametro.put("FiltroGrupo", results.get(i).getIdgrupo().getIdentificacion());
                    parametro.put("Logo", this.getClass().getResourceAsStream(Logo));
                    
                    jr = (JasperReport) JRLoader.loadObjectFromFile(archivo);
                    JasperPrint jp = JasperFillManager.fillReport(jr, parametro, DriverManager.getConnection("jdbc:postgresql://localhost:5432/calidad", "postgres", "postgres"));
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setVisible(true);
                    jv.setTitle("Reporte Evaluacion Docente");
                    limpiar();
                    
                    
                    TbinformesJpaController informebd = new TbinformesJpaController();
                    Tbinformes informe = new Tbinformes();
                    TbencuestaxasignaturaJpaController encuxasigbd = new TbencuestaxasignaturaJpaController();

                    EntityManager em = encuxasigbd.getEntityManager();
                    TypedQuery<Tbencuestaxasignatura> query = em.createNamedQuery("Tbencuestaxasignatura.findAll", Tbencuestaxasignatura.class);
                    List<Tbencuestaxasignatura> results2 = query.getResultList();
                    em.close();
                    
                    for(int j=0;j<results2.size();j++){
                        if(results2.get(j).getIdasignatura().getCodigo().equals(results.get(i).getIdasignatura().getCodigo())){
                            informe.setIdencuestaxasignatura(results2.get(j));
                        }
                    }
                    
                    informe.setFechacreacion(fecha);
                    informe.setIdgrupoxasignaturaxprofesor(results.get(i));
                    informebd.create(informe);
                    
                } catch (JRException ex) {
                    Logger.getLogger(GenerarInformeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }      
    }

    @FXML
    private void CargarPorGrupo(ActionEvent event) {
        generarbt.setDisable(false);
        grupo.setDisable(true);
    }
}
