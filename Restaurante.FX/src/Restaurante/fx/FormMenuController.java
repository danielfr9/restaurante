package Restaurante.fx;

import Restaurante.bl.ItemServicio;
import Restaurante.bl.Item;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FormMenuController implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Item, Integer> colId;
  
    @FXML
    private TableColumn<Item, String> colNombre;

    @FXML
    private TableColumn<Item, String> colDescripcion;
    
    @FXML
    private TableColumn<Item, String> colCategoria;
    
    @FXML
    private TableColumn<Item, String> colTamaño;

    @FXML
    private TableColumn<Item, Double> colPrecio;
    
    @FXML
    private TableColumn<Item, Boolean> colActivo;

    @FXML
    private TableColumn colEditar;

    @FXML
    private TableColumn colEliminar;
    
    @FXML
    private JFXTextField txtBuscar;
    
    ObservableList<Item> dataOrden;
    ItemServicio servicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicio = new ItemServicio();

        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        colCategoria.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategoria().getDescripcion()));
        colTamaño.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getTamaño().getDescripcion()));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        colActivo.setCellValueFactory(new PropertyValueFactory("activo"));

        //CREANDO METODO EDITAR Y ELIMINAR
        definirColumnaEditar();
        definirColumnaEliminar();

        cargarDatos();
    }

    public void nuevoItem() throws IOException {
        Item nuevoItem = new Item();
        abrirVentanaModal(nuevoItem, "Nuevo Item");
    }

    public String guardar(Item item) {
        String resultado = servicio.guardar(item);
        if(resultado.equals("")){
            cargarDatos();
        }
        return resultado;
    }
    
    public void buscar(){
        cargarDatos();
    }   

    private void abrirVentanaModal(Item item, String titulo) throws IOException {
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
        if (txtBuscar.getText() == null || txtBuscar.getText().equals("")){
            dataOrden = FXCollections.observableArrayList(servicio.getMenu());
        }else {
            dataOrden = FXCollections.observableArrayList(servicio.getMenu(txtBuscar.getText())); 
        }
       
        tableView.setItems(dataOrden);
        tableView.refresh();
    }
    
//LOGICA PARA EDITAR
    private void definirColumnaEditar() {
        colEditar.setCellFactory(param -> new TableCell<String, String>() {
            final JFXButton btn = new JFXButton("Editar");

            @Override
            public void updateItem(String cosa, boolean empty) {
                super.updateItem(cosa, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.getStyleClass().add("jfx-button-info-outline");
                    btn.setOnAction(event -> {
                    tableView.getSelectionModel().select(getTableRow().getItem());    
                        Item item = (Item) getTableRow().getItem();
                        try {
                            abrirVentanaModal(item, "Editar Item");
                        } catch (IOException ex) {
                            Logger.getLogger(FormMenuController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
    }

    private void definirColumnaEliminar() {
        colEliminar.setCellFactory(param -> new TableCell<String, String>() {
        final JFXButton btn = new JFXButton("Eliminar");

            @Override
            public void updateItem(String cosa, boolean empty) {
                super.updateItem(cosa, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.getStyleClass().add("jfx-button-danger-outline");
                    btn.setOnAction(event -> {
                        tableView.getSelectionModel().select(getTableRow().getItem());
                        Item item = (Item) getTableRow().getItem();
                        eliminar(item);                  
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
    }
    
    private void eliminar(Item item){
        Alert alert = new Alert(AlertType.CONFIRMATION,
            "Esta seguro que desea eliminar el Item " + item.getDescripcion() + "?",
            ButtonType.YES, ButtonType.NO);
        
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES){
           servicio.eliminar(item);
           cargarDatos(); 
        }     
    }
}
