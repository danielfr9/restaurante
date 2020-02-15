/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante.fx;

import Restaurante.bl.Pedido;
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
public class NuevoEditarPedidoController implements Initializable {
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
    ComboBox cmbPedido;
    
    @FXML
    ComboBox cmbTipo;
    
    private FormPedidoController controller;
    private Pedido pedido;
    
    
    public void setController(FormPedidoController controller) {
        this.controller = controller;
    } 
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        
        txtId.textProperty().bindBidirectional(pedido.idProperty(), new NumberStringConverter());
        txtNombre.textProperty().bindBidirectional(pedido.nombreProperty());
        txtDescripcion.textProperty().bindBidirectional(pedido.descripcionProperty()); 
        txtPrecio.textProperty().bindBidirectional(pedido.precioProperty(), new NumberStringConverter());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void aceptar() {
        controller.guardar(pedido);
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
