package com.grupo2.entity.sensores;

import com.grupo2.patterns.behavioral.observer.SensorSubject;

/**
 * Clase base de sensores que extiende SensorSubject para el patrón Observer
 */
public abstract class Sensor extends SensorSubject {
    protected String ubicacion;
    protected double valorActual;
    protected double umbralMinimo;
    protected double umbralMaximo;
    
    public Sensor(String id, String tipo, String ubicacion) {
        super(id, tipo);
        this.ubicacion = ubicacion;
    }
    
    public abstract void tomarLectura();
    
    protected void verificarUmbrales() {
        String mensaje = "";
        
        if (valorActual < umbralMinimo) {
            mensaje = "Sensor " + id + " [" + tipo + "] en " + ubicacion + 
                     ": Nivel BAJO (" + valorActual + ") - Umbral mínimo: " + umbralMinimo;
            notificarObservadores(mensaje);
        } else if (valorActual > umbralMaximo) {
            mensaje = "Sensor " + id + " [" + tipo + "] en " + ubicacion + 
                     ": Nivel ALTO (" + valorActual + ") - Umbral máximo: " + umbralMaximo;
            notificarObservadores(mensaje);
        }
    }
    
    public double getValorActual() {
        return valorActual;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUmbrales(double minimo, double maximo) {
        this.umbralMinimo = minimo;
        this.umbralMaximo = maximo;
    }
}
