package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Patrón State: Define una interfaz para encapsular el comportamiento
 * asociado con un estado particular de un Animal.
 * Los estados posibles son: Sano, Enfermo y EnTratamiento.
 */
public interface Estado {
    
    /**
     * Alimenta al animal según su estado actual.
     * @param animal Animal a alimentar
     * @param cantidad Kilogramos de alimento
     */
    void alimentar(Animal animal, double cantidad);
    
    /**
     * Aplica tratamiento médico al animal.
     * @param animal Animal a tratar
     */
    void aplicarTratamiento(Animal animal);
    
    /**
     * Retorna la descripción del estado actual.
     * @return Descripción del estado
     */
    String obtenerDescripcion();
}
