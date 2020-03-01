
package Restaurante.bl;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class CategoriaServicio {
    private final ArrayList<Categoria> listaDeCategoria;

    public CategoriaServicio() {
        listaDeCategoria = new ArrayList<>();    
        crearDatosPrueba();
    }

    public ArrayList<Categoria> getListaDeCategoria() {
        return listaDeCategoria;
    }
    
    private void crearDatosPrueba() {
        Categoria categoria1 = new Categoria("Carnes");
        Categoria categoria2 = new Categoria("Ensaladas");
        Categoria categoria3 = new Categoria("Sopas");
        categoria1.setId(1);
        categoria2.setId(2);
        categoria3.setId(3);
        
        listaDeCategoria.add(categoria1);
        listaDeCategoria.add(categoria2);
        listaDeCategoria.add(categoria3);
    }
    
    
    
}
