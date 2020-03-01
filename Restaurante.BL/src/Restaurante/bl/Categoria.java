
package Restaurante.bl;

/**
 *
 * @author Daniel
 */
public class Categoria {
    private Integer id;
    private String descripcion;

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /*
    @Override
    public String toString(){
        return descripcion;
    }
    */
}
