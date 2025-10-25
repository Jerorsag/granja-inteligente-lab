package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Estado concreto: Animal Sano
 */
public class EstadoSano implements Estado {
    
    @Override
    public void alimentar(Animal animal, double cantidad) {
        System.out.println("Animal " + animal.getId() + " sano alimentándose normalmente con " + cantidad + " kg");
        animal.setPeso(animal.getPeso() + (cantidad * 0.1));
        animal.setNivelActividad(100);
    }

    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("Animal " + animal.getId() + " está sano, no requiere tratamiento.");
    }

    @Override
    public String obtenerDescripcion() {
        return "Sano - Estado óptimo de salud";
    }
}
