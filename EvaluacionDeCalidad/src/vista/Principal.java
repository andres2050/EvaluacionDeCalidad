package vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andres
 */
public class Principal extends Application {
    
    private Stage stage;
    private Parent root;
    private Scene scene;
    
    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        try {
            root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(root);
        this.stage.setTitle("Evaluacion De Profesores");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}