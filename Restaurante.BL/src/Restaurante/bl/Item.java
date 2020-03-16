
package Restaurante.bl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    private final SimpleIntegerProperty existencia;
    private final SimpleDoubleProperty precio;
    private final SimpleBooleanProperty activo;
    private final SimpleObjectProperty imageView;
    private byte[] imagen;
    
    public Item() {
        id = new SimpleIntegerProperty();
        nombre = new SimpleStringProperty();
        descripcion = new SimpleStringProperty();
        categoria = new SimpleObjectProperty();
        tamaño = new SimpleObjectProperty();
        existencia = new SimpleIntegerProperty();
        precio = new SimpleDoubleProperty();
        activo = new SimpleBooleanProperty();
        imageView = new SimpleObjectProperty();
        imagen = "0".getBytes();
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
    
    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    public byte[] getImagen(){
        return imagen;
    }
    
    public void setImagen(byte[] imagen){
        this.imagen = imagen;
    }
    
    @Transient
    public Image getImageView(){
        Image img = new Image(new ByteArrayInputStream(imagen));
        return img;
    }
    
    public void setImageView(Image image){
        if(image == null){
            setImagen("0".getBytes());
            imageView.set(image); 
            return;
        }
        
        BufferedImage bImage = SwingFXUtils.fromFXImage(image,null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        try{
            ImageIO.write(bImage, "png", stream);
            byte[] bytes = stream.toByteArray();
            stream.close();
            setImagen(bytes);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        
        imageView.set(image);      
    }
    
    public SimpleObjectProperty imageViewProperty(){
        return imageView;
    }
    
}

