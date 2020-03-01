
package Restaurante.bl;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class TamañoServicio {
    private final ArrayList<Tamaño> listaDeTamaño;

    public TamañoServicio() {
        listaDeTamaño = new ArrayList<>();    
        crearDatosPrueba();
    }

    public ArrayList<Tamaño> getListaDeTamaño() {
        return listaDeTamaño;
    }
    
    private void crearDatosPrueba() {
        Tamaño tamaño1 = new Tamaño("Pequeño");
        Tamaño tamaño2 = new Tamaño("Mediano");
        Tamaño tamaño3 = new Tamaño("Grande");
        tamaño1.setId(1);
        tamaño2.setId(2);
        tamaño3.setId(3);
        
        listaDeTamaño.add(tamaño1);
        listaDeTamaño.add(tamaño2);
        listaDeTamaño.add(tamaño3);
    }
    
    
}
