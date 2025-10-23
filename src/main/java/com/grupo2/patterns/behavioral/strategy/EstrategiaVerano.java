package com.grupo2.patterns.behavioral.strategy;

import com.grupo2.entity.animales.Animal;

/**
 * Estrategia concreta: Alimentación en verano (menos cantidad, más hidratación)
 */
public class EstrategiaVerano implements EstrategiaAlimentacion {

    @Override
    public double calcularCantidadAlimento(Animal animal) {
        // En verano, 2.5% del peso corporal (menos por el calor)
        return animal.getPeso() * 0.025;
    }

    @Override
    public int calcularFrecuencia() {
        return 3; // 3 veces al día en verano
    }

    @Override
    public String obtenerDescripcion() {
        return "Estrategia Verano: Menor cantidad, enfoque en hidratación";
    }
}