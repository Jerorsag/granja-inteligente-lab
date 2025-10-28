package com.grupo2.patterns.behavioral.strategy;

import com.grupo2.entity.animales.Animal;

/**
 * Estrategia concreta: Alimentación en invierno (mayor cantidad, más frecuencia)
 */
public class EstrategiaInvierno implements EstrategiaAlimentacion {

    @Override
    public double calcularCantidadAlimento(Animal animal) {
        // En invierno, 3% del peso corporal + factor extra por clima
        double cantidadBase = animal.getPeso() * 0.03;
        double factorInvierno = 1.5; // 50% más en invierno
        return cantidadBase * factorInvierno;
    }

    @Override
    public int calcularFrecuencia() {
        return 4; // 4 veces al día en invierno
    }

    @Override
    public String obtenerDescripcion() {
        return "Estrategia Invierno: Mayor cantidad y frecuencia por clima frío";
    }
}