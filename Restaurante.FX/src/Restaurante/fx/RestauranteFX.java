
package Restaurante.fx;

import Restaurante.bl.Usuario;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Daniel
 */
public class RestauranteFX extends Application {
  public static Boolean isSplashLoaded = false;
    static Stage stage;
    static Usuario usuario;
    
    public static Stage getStage() {
        return stage;
    }
    
    public static Usuario getUsuarioAutenticado() {
        return usuario;
    }
    
    public static void setUsuarioAutenticado(Usuario usuario) {
        RestauranteFX.usuario = usuario;
    }
    
    @Override
    public void start(Stage stage) throws Exception {        
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        
        RestauranteFX.stage = stage;
        Parent root = FXMLLoader.load(getClass()
                .getResource("/Restaurante/fx/FormLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Ingresar al Sistema");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
