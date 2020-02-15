
package Restaurante.bl;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Cliente {
    private final ArrayList<Pedido> Orden;
    
    public Cliente() {
        Orden = new ArrayList<>();
        
        //crearDatosdePrueba();
    }

    public ArrayList<Pedido> obtenerPedidos() {
        return Orden;
    }
    
    public void guardar(Pedido pedido) {
        if (pedido.getId().equals(0)) {
            Integer id = obtenerSiguienteId();
            
            pedido.setId(id);
            
            Orden.add(pedido);
        }
    }
    /**
    private void crearDatosdePrueba() {
        Platillo pedido1 = new Platillo();
        pedido1.setId(1);
        pedido1.setNombre("iPhone X");
        pedido1.setDescripcion("MUY CARO");
        pedido1.setPrecio(1400.0);
        //pedido1.setTamano("Grande");
        //pedido1.setcantidadPersonas(3);

        Bebida pedido2 = new Bebida();
        pedido2.setId(2);
        pedido2.setNombre("Samsung Galaxy S10");
        pedido2.setDescripcion("MUY BUENO");
        pedido2.setPrecio(1200.0);
        //pedido2.setMarca("Coca Cola");
        //pedido2.setCantidadMiliLitros(400);
        
        Orden.add(pedido1);
        Orden.add(pedido2);
    }   
    */ 
    private Integer obtenerSiguienteId() {
        Integer maxId = 1;
        for(Pedido pedido: Orden) {
            if (pedido.getId() >= maxId) {
                maxId = pedido.getId() + 1;
            }
        }
        return maxId;
    }
}
