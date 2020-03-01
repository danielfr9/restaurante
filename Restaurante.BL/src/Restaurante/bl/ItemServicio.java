
package Restaurante.bl;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class ItemServicio {
    private final ArrayList<Item> Menu;
    
    public ItemServicio() {
        Menu = new ArrayList();
        crearDatosdePrueba();
    }

    public ArrayList<Item> getMenu(){
        return Menu;
    }
    
    public ArrayList<Item> getMenu(String buscar){   
        //ESTA DE MAS
        if (buscar == null && buscar.equals("")){
            return Menu;
        }
        
        String buscarMinuscula = buscar.toLowerCase(); 
        ArrayList<Item> resultado = new ArrayList<>();
          
        Menu.forEach(item -> {
            if (item.getDescripcion().toLowerCase().contains(buscarMinuscula) == true){
                resultado.add(item);
            }
        });
        return resultado;
    }
    
    public String guardar(Item item) {     
        String resultado = validarItem(item);

        if (resultado.equals("")){
            if (item.getId().equals(0)){
                Integer id = obtenerSiguienteId();
                item.setId(id);
                Menu.add(item);
            }else{
                Menu.forEach(itemExistente -> {
                    if(itemExistente.getId().equals(item.getId())){
                        itemExistente.setDescripcion(item.getDescripcion());
                    }
                });
            }
            return "";
        }
        return resultado;
    }
    
    public void eliminar(Item item){
        Menu.remove(item);
    }
    
    private void crearDatosdePrueba() {
        Item item1 = new Item();
        Item item2 = new Item();
        CategoriaServicio categorias = new CategoriaServicio();
        TamañoServicio tamaños = new TamañoServicio();
        
        item1.setId(1);
        item1.setNombre("Pollo Frito");
        item1.setDescripcion("Pollo Frito con tajadas y chimol");
        item1.setCategoria(categorias.getListaDeCategoria().get(0));
        item1.setTamaño(tamaños.getListaDeTamaño().get(2));
        item1.setPrecio(120.0);
        item1.setActivo(true);
        
        item2.setId(2);
        item2.setNombre("Ensalada Verde");
        item2.setDescripcion("Ensalada de verduras verdes");
        item2.setCategoria(categorias.getListaDeCategoria().get(1));
        item2.setTamaño(tamaños.getListaDeTamaño().get(1));
        item2.setPrecio(150.0);
        item2.setActivo(true);
        
        Menu.add(item1);
        Menu.add(item2);
    }   
    
    private Integer obtenerSiguienteId() {
        Integer maxId = 1;
        for(Item item: Menu) {
            if (item.getId() >= maxId) {
                maxId = item.getId() + 1;
            }
        }
        return maxId;
    }
    
    private String validarItem(Item item) {
        if(item.getId().equals("")){
            return "Ingrese ID";
        }
        if (item.getNombre()== null || item.getNombre().equals("")){
            return "Ingrese Nombre";
        }
        if (item.getDescripcion() == null || item.getDescripcion().equals("")){
            return "Ingrese Descripcion";
        }
        if(item.getCategoria() == null || item.getCategoria().equals("")){
            return "Ingrese Categoria";
        }
        if(item.getTamaño() == null || item.getTamaño().equals("")){
            return "Ingrese Tamaño";
        }
        if(item.getPrecio() <= 0){
            return "El precio debe ser mayor que 0";
        }
        return "";
    }
}
