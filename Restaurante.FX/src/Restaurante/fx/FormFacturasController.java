
package Restaurante.fx;

import Restaurante.bl.Categoria;
import Restaurante.bl.CategoriaServicio;
import Restaurante.bl.Factura;
import Restaurante.bl.FacturaDetalle;
import Restaurante.bl.FacturaServicio;
import Restaurante.bl.Item;
import Restaurante.bl.ItemServicio;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FormFacturasController implements Initializable {

    @FXML
    TableView tableView;
    
    @FXML
    TabPane tabPaneItems;
    
    @FXML
    TableColumn <Item, String> colNombre;
    
    @FXML
    TableColumn <Item, Double> colPrecio;
    
    @FXML
    TableColumn colEliminar;
    
    @FXML
    Label lblTotal;
    
    @FXML
    Label lblImpuesto;
    
    ObservableList <Item> data;
    
    Factura nuevaFactura;
    
    ItemServicio servicioItems;
    CategoriaServicio servicioCategorias;
    FacturaServicio servicioFacturas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicioItems = new ItemServicio();
        servicioCategorias = new CategoriaServicio();
        servicioFacturas = new FacturaServicio();
        
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        definirColumnaEliminar();
        
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        
        crearTabs();
        nuevaFactura();
    }    
    
    
    public void nuevaFactura(){
        nuevaFactura = new Factura();
        data.clear();
        calcularFactura();
    }
    
    public void guardar(){
        servicioFacturas.guardar(nuevaFactura);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Factura Guardada");
        alert.showAndWait();
        
        nuevaFactura();
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
                        removerItem(item);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
    }

    private void crearTabs() {
        ArrayList<Categoria> categorias = servicioCategorias.getListaDeCategoria();
        ArrayList<Item> items = servicioItems.getMenuActivos();
        
        for(Categoria categoria: categorias){
            TilePane tilePane = new TilePane();
            tilePane.setPadding(new Insets(10,10,10,10));
            tilePane.setHgap(10);
            tilePane.setVgap(10);
            
            List<Item> itemPorCategoria = items.stream()
                .filter(p-> Objects.equals(p.getCategoria().getId(), categoria.getId()))
                    .collect(Collectors.toList());
            
            for(Item item: itemPorCategoria){
                VBox vbox = new VBox();
                Label lblNombre = new Label();
                Label lblPrecio = new Label();
                
                lblNombre.setText(item.getNombre());
                lblPrecio.setText(item.getPrecio().toString());
                
                ImageView imagen = new ImageView(item.getImageView());
                imagen.setUserData(item);
                imagen.setFitWidth(100);
                imagen.setPreserveRatio(true);
                
                vbox.getChildren().add(imagen);
                vbox.getChildren().add(lblNombre);
                vbox.getChildren().add(lblPrecio);
                vbox.setAlignment(Pos.CENTER);
                
                imagen.addEventHandler(MouseEvent.MOUSE_CLICKED, 
                        new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Object object = event.getSource();
                        ImageView image = (ImageView)object;
                        Item userData = (Item)image.getUserData();
                        
                        agregarItem(userData);
                    }
                });
                
                tilePane.getChildren().add(vbox);
            }
            
            Tab tab = new Tab(categoria.getDescripcion(), tilePane);
            
            tabPaneItems.getTabs().add(tab);        
        }
    }
    
    private void agregarItem(Item item){
        data.add(item);
        calcularFactura();
    }
    
    private void removerItem(Item item){
        data.remove(item);
        calcularFactura();
    }
 
    private void calcularFactura(){
        Double total = 0.0;
        Double impuesto = 0.0;
        
        nuevaFactura.getFacturaDetalle().clear();
        
        for(Item item: data){
            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setFactura(nuevaFactura);
            detalle.setItem(item);
            detalle.setCantidad(1);
            detalle.setPrecio(item.getPrecio());
            
            nuevaFactura.getFacturaDetalle().add(detalle);
            
            total += item.getPrecio();
        }
        
        impuesto = total - (total/1.15);
        
        nuevaFactura.setTotal(total);
        nuevaFactura.setImpuesto(impuesto);
        
        lblTotal.setText("Total: " + formatoMoneda(total));
        lblImpuesto.setText("Impuesto: " + formatoMoneda(impuesto));
    }
    
    private String formatoMoneda(Double valor){
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(valor);
    }
}
