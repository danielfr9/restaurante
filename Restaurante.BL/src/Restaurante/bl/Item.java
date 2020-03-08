
package Restaurante.bl;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name="Item")
public class Item {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;
    private final SimpleObjectProperty categoria;
    private final SimpleObjectProperty tamaño;
    //private final SimpleStringProperty tamaño;
    private final SimpleIntegerProperty existencia;
    private final SimpleDoubleProperty precio;
    private final SimpleBooleanProperty activo;
    
    public Item() {
        id = new SimpleIntegerProperty();
        nombre = new SimpleStringProperty();
        descripcion = new SimpleStringProperty();
        categoria = new SimpleObjectProperty();
        tamaño = new SimpleObjectProperty();
        existencia = new SimpleIntegerProperty();
        precio = new SimpleDoubleProperty();
        activo = new SimpleBooleanProperty();
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public SimpleStringProperty nombreProperty() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    public SimpleStringProperty descripcionProperty() {
        return descripcion;
    }

    @ManyToOne
    @JoinColumn(name="categoriaId", nullable=false)
    public Categoria getCategoria(){
        return (Categoria) categoria.get();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria.set(categoria);
    }
    
    public SimpleObjectProperty categoriaProperty() {
        return categoria;
    }
    
    @ManyToOne
    @JoinColumn(name="tamañoId", nullable=false)
    public Tamaño getTamaño(){
        return (Tamaño) tamaño.get();
    }

    public void setTamaño(Tamaño tamaño) {
        this.tamaño.set(tamaño);
    }
    
    public SimpleObjectProperty tamañoProperty() {
        return tamaño;
    }
    
    public Integer getExistencia() {
        return existencia.get();
    }

    public void setExistencia(Integer existencia) {
        this.existencia.set(existencia);
    }
    
    public SimpleIntegerProperty existenciaProperty() {
        return existencia;
    }
    
     public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    
    public SimpleDoubleProperty precioProperty() {
        return precio;
    }
    
    public Boolean getActivo(){
        return activo.get();
    }

    public void setActivo(Boolean activo) {
        this.activo.set(activo);
    }
    
    public SimpleBooleanProperty activoProperty() {
        return activo;
    }  
}

