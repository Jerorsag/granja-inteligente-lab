package com.grupo2.patterns.behavioral.strategy;

import com.grupo2.entity.animales.Animal;

/**
 * Estrategia concreta: Alimentación de ahorro (optimización de recursos)
 */
public class EstrategiaAhorro implements EstrategiaAlimentacion {

    @Override
    public double calcularCantidadAlimento(Animal animal) {
        // Modo ahorro: 2% del peso corporal
        return animal.getPeso() * 0.02;
    }

    @Override
    public int calcularFrecuencia() {
        return 2; // 2 veces al día en modo ahorro
    }

    @Override
    public String obtenerDescripcion() {
        return "Estrategia Ahorro: Optimización de recursos, mínimo necesario";
    }
}