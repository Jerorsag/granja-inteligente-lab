package com.grupo2.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Observer: Implementación base de Subject para sensores
 */
public abstract class SensorSubject implements Subject {
    private List<Observer> observadores;
    protected String id;
    protected String tipo;
    
    public SensorSubject(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.observadores = new ArrayList<>();
    }
    
    @Override
    public void agregarObservador(Observer observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
            System.out.println("✓ Observador agregado al sensor " + id);
        }
    }
    
    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
        System.out.println("✓ Observador eliminado del sensor " + id);
    }
    
    @Override
    public void notificarObservadores(String mensaje) {
        for (Observer observador : observadores) {
            observador.actualizar(this, mensaje);
        }
    }
    
    public String getId() {
        return id;
    }
    
    public String getTipo() {
        return tipo;
    }
}
