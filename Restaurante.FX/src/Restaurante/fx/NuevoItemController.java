
package Restaurante.fx;

import Restaurante.bl.Categoria;
import Restaurante.bl.CategoriaServicio;
import Restaurante.bl.Item;
import Restaurante.bl.Tamaño;
import Restaurante.bl.TamañoServicio;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class NuevoItemController implements Initializable {
    @FXML
    JFXButton btnCancelar;
    
    @FXML
    JFXButton btnAgregar;
    
    @FXML
    JFXTextField txtId;
    
    @FXML
    JFXTextField txtNombre;
    
    @FXML
    JFXTextField txtDescripcion;    
    
    @FXML
    JFXComboBox cmbCategoria;
    
    @FXML
    JFXComboBox cmbTamaño;
    
    @FXML
    JFXTextField txtPrecio;
    
    @FXML
    JFXCheckBox chActivo;
    
    private FormMenuController controller;
    private Item item;
    private CategoriaServicio categoriaServicio;
    private TamañoServicio tamañoServicio;
    
    
    public void setController(FormMenuController controller) {
        this.controller = controller;
    } 
    
    public void setItem(Item item) {
        this.item = item;
              
        txtId.textProperty().bindBidirectional(item.idProperty(), new NumberStringConverter());
        txtNombre.textProperty().bindBidirectional(item.nombreProperty());
        txtDescripcion.textProperty().bindBidirectional(item.descripcionProperty()); 
        cmbCategoria.valueProperty().bindBidirectional(item.categoriaProperty());
        cmbCategoria.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria == null ? "" : categoria.getDescripcion();
            }

            @Override
            public Categoria fromString(String string) {
                return new Categoria(string);
            }
            
        });
        cmbTamaño.valueProperty().bindBidirectional(item.tamañoProperty());
        cmbTamaño.setConverter(new StringConverter<Tamaño>() {
            @Override
            public String toString(Tamaño tamaño) {
                return tamaño == null ? "" : tamaño.getDescripcion();
            }

            @Override
            public Tamaño fromString(String string) {
                return new Tamaño(string);
            }
            
        });
        txtPrecio.textProperty().bindBidirectional(item.precioProperty(), new NumberStringConverter());
        chActivo.selectedProperty().bindBidirectional(item.activoProperty());    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriaServicio = new CategoriaServicio();
        tamañoServicio = new TamañoServicio();
        
        ObservableList<Categoria> data = FXCollections.observableArrayList(categoriaServicio.getListaDeCategoria());
        ObservableList<Tamaño> data2 = FXCollections.observableArrayList(tamañoServicio.getListaDeTamaño());
        
        cmbCategoria.setItems(data);
        cmbTamaño.setItems(data2);
        
    }    
    //agregando alertas para errores de validacion
    public void agregar() { 
        String resultado = controller.guardar(item);
        if (resultado.equals("")){
            cerrar();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Items");
            alert.setHeaderText("Errores de validacion de los datos");
            alert.setContentText(resultado);
            alert.showAndWait();
        }
    }
    
    public void cancelar() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }   
}
