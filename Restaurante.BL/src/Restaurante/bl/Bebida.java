
package Restaurante.bl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Daniel
 */
//Heredaba de Orden
public class Bebida{
    private final SimpleStringProperty marca;
    private final SimpleIntegerProperty cantidadMiliLitros;

    public Bebida() {
        marca = new SimpleStringProperty();
        cantidadMiliLitros = new SimpleIntegerProperty();
    }
    
    public Integer getCantidadMiliLitros() {
        return cantidadMiliLitros.get();
    }

    public void setCantidadMiliLitros(Integer cantidadLitros) {
        this.cantidadMiliLitros.set(cantidadLitros);
    }
    
    public SimpleIntegerProperty cantidadLitrosProperty() {
        return cantidadMiliLitros;
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }
    
    public SimpleStringProperty marcaProperty() {
        return marca;
    }
    
    
}
