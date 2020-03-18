
package Restaurante.bl;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Daniel
 */
@Entity
@Table(name="Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria() {
    }
    
    @OneToMany(mappedBy="categoria")
    private Set<Item> item;

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
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
}
