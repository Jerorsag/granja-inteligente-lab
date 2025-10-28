package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Estado concreto: Animal Enfermo
 * Reduce la absorción de alimento y nivel de actividad del animal.
 * Transición: Enfermo -> EnTratamiento (al aplicar tratamiento)
 */
public class EstadoEnfermo implements Estado {
    
    /**
     * Alimentación reducida al 50%, ganancia de peso del 5%.
     * El nivel de actividad disminuye 10 puntos (mínimo 30).
     */
    @Override
    public void alimentar(Animal animal, double cantidad) {
        System.out.println("Animal " + animal.getId() + " enfermo, alimentación reducida: " + (cantidad * 0.5) + " kg");
        // Ganancia de peso reducida por mala absorción
        animal.setPeso(animal.getPeso() + (cantidad * 0.05));
        // Reduce actividad pero garantiza mínimo de 30
        animal.setNivelActividad(Math.max(30, animal.getNivelActividad() - 10));
    }

    /**
     * Inicia el tratamiento y cambia el estado a EnTratamiento.
     */
    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("Aplicando tratamiento al animal " + animal.getId());
        animal.agregarHistorialMedico("Tratamiento iniciado por enfermedad");
        // Transición de estado
        animal.cambiarEstado(new EstadoEnTratamiento());
    }

    @Override
    public String obtenerDescripcion() {
        return "Enfermo - Requiere atención veterinaria";
    }
}
