
package Restaurante.fx;

import Restaurante.bl.Item;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class NuevoItemController implements Initializable {
    @FXML
    Button btnCancelar;
    
    @FXML
    TextField txtId;
    
    @FXML
    TextField txtNombre;
    
    @FXML
    TextField txtDescripcion;    
    
    @FXML
    TextField txtPrecio;
    
    @FXML
    TextField txtCategoria;
    
    @FXML
    TextField txtTamaño;
    
    @FXML
    ComboBox cmbPedido;
    
    @FXML
    ComboBox cmbTipo;
    
    private FormOrdenController controller;
    private Item item;
    
    
    public void setController(FormOrdenController controller) {
        this.controller = controller;
    } 
    
    public void setItem(Item item) {
        this.item = item;
        
        txtId.textProperty().bindBidirectional(item.idProperty(), new NumberStringConverter());
        txtNombre.textProperty().bindBidirectional(item.nombreProperty());
        txtDescripcion.textProperty().bindBidirectional(item.descripcionProperty()); 
        txtPrecio.textProperty().bindBidirectional(item.precioProperty(), new NumberStringConverter());
        txtCategoria.textProperty().bindBidirectional(item.categoriaProperty());
        txtTamaño.textProperty().bindBidirectional(item.tamañoProperty());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void aceptar() {
        controller.guardar(item);
        cerrar();
    }
    
    public void cancelar() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }   
}
