/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante.fx;

import Restaurante.bl.Cliente;
import Restaurante.bl.Pedido;
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
public class FormPedidoController implements Initializable {
    @FXML
    private TableView tableView;
    
    @FXML
    private TableColumn<Pedido, Integer> colId;

    @FXML
    private TableColumn<Pedido, String> colDescripcion;
    
    @FXML
    private TableColumn<Pedido, Double> colPrecio;

    @FXML
    private TableColumn<Pedido, String> colNombre;
    
    ObservableList<Pedido> dataPedidos;
    
    Cliente servicioCliente;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       servicioCliente = new Cliente();
       
       colId.setCellValueFactory(new PropertyValueFactory("id"));
       colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
       colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       
       cargarDatos();
    }    
    
    public void nuevoPedido() throws IOException {
        Pedido nuevoPedido = new Pedido();
        abrirVentanaModal(nuevoPedido, "Nuevo Pedido");
    }
    
    public void guardar(Pedido pedido) {
        servicioCliente.guardar(pedido);
        cargarDatos();
    }
    
    private void abrirVentanaModal(Pedido pedido , String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoEditarPedido.fxml"));
        Parent root = (Parent) loader.load();
        
        NuevoEditarPedidoController controller = loader.getController();
        controller.setController(this);
        controller.setPedido(pedido);
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titulo);
        stage.show();
    }
    
    private void cargarDatos() {
       dataPedidos = FXCollections.observableArrayList(servicioCliente.obtenerPedidos());
       
       tableView.setItems(dataPedidos);
       tableView.refresh();
    }
    
}
