package com.grupo2.patterns.behavioral.observer;

/**
 * Patrón Observer: Interfaz Observer que recibe notificaciones
 */
public interface Observer {
    void actualizar(Subject sujeto, String mensaje);
}
