package com.grupo2.patterns.behavioral.strategy;

import com.grupo2.entity.animales.Animal;

/**
 * Patrón Strategy: Define la interfaz para diferentes estrategias de alimentación
 */
public interface EstrategiaAlimentacion {
    double calcularCantidadAlimento(Animal animal);
    int calcularFrecuencia(); // veces por día
    String obtenerDescripcion();
}