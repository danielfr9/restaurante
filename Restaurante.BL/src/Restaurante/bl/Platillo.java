/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante.bl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Daniel
 */
public class Platillo extends Pedido{
    private final SimpleStringProperty tamano;
    private final SimpleIntegerProperty cantidadPersonas;

    public Platillo() {
        tamano = new SimpleStringProperty();
        cantidadPersonas = new SimpleIntegerProperty();
    }
    
    public Integer getCantidadPersonas() {
        return cantidadPersonas.get();
    }
    
    public void setcantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas.set(cantidadPersonas);
    }
    
    public SimpleIntegerProperty cantidadPersonasProperty() {
        return cantidadPersonas;
    }

    public String getTamano() {
        return tamano.get();
    }

    public void setTamano(String tamano) {
        this.tamano.set(tamano);
    }
    
    public SimpleStringProperty tamanoProperty() {
        return tamano;
    }
    
}
