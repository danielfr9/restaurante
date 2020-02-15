
package Restaurante.bl;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Cliente {
    private final ArrayList<Item> Orden;
    
    public Cliente() {
        Orden = new ArrayList();
        crearDatosdePrueba();
    }

    public ArrayList<Item> getOrden(){
        return Orden;
    }
    
    public void guardar(Item item) {
            Integer id = obtenerSiguienteId();
            
            item.setId(id);
            
            Orden.add(item);
    }
    
    private void crearDatosdePrueba() {
        //id,nombre,descripcion,precio,categorias,tamano
        Item item1 = new Item();
        Item item2 = new Item();
        
        
        item1.setId(1);
        item1.setNombre("Pollo Frito");
        item1.setDescripcion("Pollo Frito con tajadas y chimol");
        item1.setPrecio(120.0);
        item1.setCategoria("Carnes");
        item1.setTamaño("Pechuga");
        
        item2.setId(2);
        item2.setNombre("Ensalada Verde");
        item2.setDescripcion("Ensalada de verduras verdes");
        item2.setPrecio(150.0);
        item2.setCategoria("Ensalada");
        item2.setTamaño("Mediana");
        
        Orden.add(item1);
        Orden.add(item2);
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
