package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Estado concreto: Animal Sano
 * Estado óptimo con máxima absorción de alimento y actividad al 100%.
 * Este es el estado ideal para productividad.
 */
public class EstadoSano implements Estado {
    
    /**
     * Alimentación normal al 100%, ganancia de peso del 10%.
     * Mantiene nivel de actividad constante al 100%.
     */
    @Override
    public void alimentar(Animal animal, double cantidad) {
        System.out.println("Animal " + animal.getId() + " sano alimentándose normalmente con " + cantidad + " kg");
        // Absorción óptima de nutrientes
        animal.setPeso(animal.getPeso() + (cantidad * 0.1));
        // Mantiene actividad al máximo
        animal.setNivelActividad(100);
    }

    /**
     * No aplica tratamiento porque el animal está sano.
     * Evita medicación innecesaria.
     */
    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("Animal " + animal.getId() + " está sano, no requiere tratamiento.");
    }

    @Override
    public String obtenerDescripcion() {
        return "Sano - Estado óptimo de salud";
    }
}
