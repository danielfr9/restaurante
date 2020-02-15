
package Restaurante.bl;

import java.util.ArrayList;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Daniel
 */
public class Cliente {
    //private final ArrayList<Pedido> Orden;
    //private Orden Orden;
    private final ArrayList<Item> Orden;
    
    public Cliente() {
        Orden = new ArrayList();
        crearDatosdePrueba();
    }
/*
    public ArrayList<Pedido> obtenerPedidos() {
        return Orden;
    }
*/
    public ArrayList<Item> getOrden(){
        return Orden;
    }
    
    public void guardar(Item item) {
        if (item.getId().equals(0)) {
            Integer id = obtenerSiguienteId();
            
            item.setId(id);
            
            Orden.add(item);
        }
    }
    
    private void crearDatosdePrueba() {
        //id,nombre,descripcion,precio,categorias,tamano
        Item item1 = new Item();
        //Item item1 = new Item(1,"Pollo Frito","Pollo con tajadas y chimol",120.0,"Carnes","Pechuga");
        //Item item2 = new Item(2,"Ensalada com pollo","Ensalada de verduaras verdes con pollo",150.0,"Ensaladas","Mediana");
        
        item1.setId(1);
        item1.setNombre("Pollo Frito");
        item1.setDescripcion("Pollo Frito con Tajadas y chimol");
        item1.setPrecio(120.0);
        item1.setCategoria("Carnes");
        item1.setTamaño("Pechuga");
        
        /*
        pedido1.setId(1);
        pedido1.setNombre("iPhone X");
        pedido1.setDescripcion("MUY CARO");
        pedido1.setPrecio(1400.0);
        pedido1.setTamano("Grande");
        pedido1.setcantidadPersonas(3);
        */
        
        Orden.add(item1);
        //Orden.add(item2);
    }   
    
    private Integer obtenerSiguienteId() {
        Integer maxId = 1;
        for(Item item: Orden) {
            if (item.getId() >= maxId) {
                maxId = item.getId() + 1;
            }
        }
        return maxId;
    }
}
