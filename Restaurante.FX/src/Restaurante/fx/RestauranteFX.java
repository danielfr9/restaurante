
package Restaurante.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class RestauranteFX extends Application {
    public static boolean isSplashLoaded = false;
    static Stage stage;
    
    public static Stage getStage(){
        return stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        RestauranteFX.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Restaurante/fx/Menu/main.fxml"));
        Scene scene = new Scene(root,990,555);
   
        stage.setScene(scene);
        
        stage.setTitle("Restaurante");
        stage.setHeight(scene.getHeight());
        stage.setWidth(scene.getWidth());
        stage.show();
        //System.out.println("Height: " + stage.getHeight() + " Width: " + stage.getWidth());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
