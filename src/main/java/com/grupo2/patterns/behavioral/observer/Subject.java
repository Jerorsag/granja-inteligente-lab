package com.grupo2.patterns.behavioral.observer;

/**
 * Patrón Observer: Interfaz Subject que define métodos para manejar observadores
 */
public interface Subject {
    void agregarObservador(Observer observador);
    void eliminarObservador(Observer observador);
    void notificarObservadores(String mensaje);
}
