
package Restaurante.bl;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Daniel
 */
public class Item {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty descripcion;
    private final SimpleDoubleProperty precio;
    private final SimpleStringProperty categoria;
    private final SimpleStringProperty tamaño;
    
    public Item() {
        id = new SimpleIntegerProperty();
        nombre = new SimpleStringProperty();
        descripcion = new SimpleStringProperty();
        precio = new SimpleDoubleProperty();
        categoria = new SimpleStringProperty();
        tamaño = new SimpleStringProperty();
    }
    
    //Para la clase Menu
    Item(Integer a, String b, String c, Double d, String e, String f) {
        id = new SimpleIntegerProperty();
        nombre = new SimpleStringProperty();
        descripcion = new SimpleStringProperty();
        precio = new SimpleDoubleProperty();
        categoria = new SimpleStringProperty();
        tamaño = new SimpleStringProperty();
        
        this.id.set(a);
        this.nombre.set(b);
        this.descripcion.set(c);
        this.precio.set(d);
        this.categoria.set(e);
        this.tamaño.set(f);    
    }
    
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
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
    
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public SimpleStringProperty nombreProperty() {
        return nombre;
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

    public String getCategoria(){
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }
    
    public SimpleStringProperty categoriaProperty() {
        return categoria;
    }
    
    public String getTamaño(){
        return tamaño.get();
    }

    public void setTamaño(String tamaño) {
        this.tamaño.set(tamaño);
    }
    
    public SimpleStringProperty tamañoProperty() {
        return tamaño;
    }
    
}

