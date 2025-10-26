package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Estado concreto: Animal Enfermo
 */
public class EstadoEnfermo implements Estado {
    
    @Override
    public void alimentar(Animal animal, double cantidad) {
        System.out.println("Animal " + animal.getId() + " enfermo, alimentación reducida: " + (cantidad * 0.5) + " kg");
        animal.setPeso(animal.getPeso() + (cantidad * 0.05));
        animal.setNivelActividad(Math.max(30, animal.getNivelActividad() - 10));
    }

    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("Aplicando tratamiento al animal " + animal.getId());
        animal.agregarHistorialMedico("Tratamiento iniciado por enfermedad");
        animal.cambiarEstado(new EstadoEnTratamiento());
    }

    @Override
    public String obtenerDescripcion() {
        return "Enfermo - Requiere atención veterinaria";
    }
}
