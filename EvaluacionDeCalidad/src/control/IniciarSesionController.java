/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import modelo.Tbusuario;
import DAO.TbusuarioJpaController;
import java.math.BigDecimal;
import java.util.List;
import javafx.scene.layout.Pane;
import javax.persistence.TypedQuery;
import org.apache.commons.codec.digest.DigestUtils;
import vista.Activo;

/**
 * FXML Controller class
 *
 * @author Andres
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private PasswordField contrasenatf;
    @FXML
    private Button enviarbt;
    @FXML
    private TextField usuariotf;
    @FXML
    private Label Mensajelb;
    @FXML
    private AnchorPane fondo;
    @FXML
    private Pane panel;
    @FXML
    private Label Mensaje1lb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        enviarbt.setDefaultButton(true);
        enviarbt.setStyle("-fx-font: 22 arial ; -fx-base: #d93434;");
        fondo.setStyle("-fx-background-image: url(/imagenes/iniciarsesion1.png); -fx-background-size: 900px 600px;");
        panel.setStyle(" -fx-background-color: #FFFFFF;");
        // TODO
        

    }

    @FXML
    private void AccionEnviar(ActionEvent event) {
        Mensajelb.setVisible(false);
        Mensaje1lb.setVisible(false);
        try{
       
        TbusuarioJpaController userDB = new TbusuarioJpaController();
        Activo.usuario = new Tbusuario();
        Activo.usuario.setCodigo(new BigDecimal(usuariotf.getText()));
        Activo.usuario.setContrasena(DigestUtils.md5Hex(contrasenatf.getText()));

        EntityManager em = userDB.getEntityManager();
        TypedQuery<Tbusuario> query = em.createNamedQuery("Tbusuario.findByCodigo", Tbusuario.class);
//        System.out.println(query);
        List<Tbusuario> results = query.setParameter("codigo", Activo.usuario.getCodigo()).setMaxResults(1).getResultList();
//        System.out.println(results);
        em.close();
         
        if(!results.isEmpty()){
            if (results.get(0).getContrasena().equals(Activo.usuario.getContrasena())) {
//                String tipo = "Estudiante";
                String tipo = (results.get(0).getIdtipo()).getPrivilegios();
                if (tipo.equals("Estudiante")) {
                    String ruta = "Estudiante/PrincipalUsuario.fxml";
                    new Activo().cambioAnchor(event, ruta);
                }

                if (tipo.equals("Administrador")) {
                    String ruta = "Admin/PrincipalAdmin.fxml";
                    new Activo().cambioAnchor(event, ruta);
                }
                //System.out.println("conecto");
            }else {
                Mensajelb.setVisible(true);
            }
        } else {
                Mensajelb.setVisible(true);
        }
        }catch(Exception ex){
            Mensajelb.setVisible(true);
        }
    }

}
