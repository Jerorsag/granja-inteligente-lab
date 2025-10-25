package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Patrón State: Define una interfaz para encapsular el comportamiento
 * asociado con un estado particular de un Animal.
 */
public interface Estado {
    void alimentar(Animal animal, double cantidad);
    void aplicarTratamiento(Animal animal);
    String obtenerDescripcion();
}
