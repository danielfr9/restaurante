
package Restaurante.fx;

import Restaurante.bl.Cliente;
import Restaurante.bl.Item;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FormOrdenController implements Initializable {
    @FXML
    private TableView tableView;
    
    @FXML
    private TableColumn<Item, Integer> colId;

    @FXML
    private TableColumn<Item, String> colDescripcion;
    
    @FXML
    private TableColumn<Item, Double> colPrecio;

    @FXML
    private TableColumn<Item, String> colNombre;
    
    @FXML
    private TableColumn<Item, String> colCategoria;
    
    ObservableList<Item> dataOrden;
    
    Cliente Cliente;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
       Cliente = new Cliente();
       
       colId.setCellValueFactory(new PropertyValueFactory("id"));
       colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       colCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
       colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
    
       cargarDatos();
    }    
    
    public void nuevoItem() throws IOException {
        Item nuevoItem = new Item();
        abrirVentanaModal(nuevoItem, "Nuevo Item");
    }
    
    public void guardar(Item item) {
        Cliente.guardar(item);
        cargarDatos();
    }
    
    private void abrirVentanaModal(Item item , String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoItem.fxml"));
        Parent root = (Parent) loader.load();
        
        NuevoItemController controller = loader.getController();
        controller.setController(this);
        controller.setItem(item);
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titulo);
        stage.show();
    }
    
    private void cargarDatos() {
       dataOrden = FXCollections.observableArrayList(Cliente.getOrden());
       
       tableView.setItems(dataOrden);
       tableView.refresh();
    }
    
}
