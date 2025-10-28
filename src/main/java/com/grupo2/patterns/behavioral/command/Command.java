package com.grupo2.patterns.behavioral.command;

/**
 * Patrón Command: Interfaz para encapsular operaciones
 */
public interface Command {
    void ejecutar();
    void deshacer();
    String getDescripcion();
}