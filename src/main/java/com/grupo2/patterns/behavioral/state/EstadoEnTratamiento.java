package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Estado concreto: Animal en Tratamiento
 */
public class EstadoEnTratamiento implements Estado {
    private int diasTratamiento = 0;
    
    @Override
    public void alimentar(Animal animal, double cantidad) {
        System.out.println("Animal " + animal.getId() + " en tratamiento, dieta especial: " + (cantidad * 0.7) + " kg");
        animal.setPeso(animal.getPeso() + (cantidad * 0.07));
        animal.setNivelActividad(Math.min(100, animal.getNivelActividad() + 5));
        
        diasTratamiento++;
        if (diasTratamiento >= 3) {
            System.out.println("Tratamiento completado para animal " + animal.getId());
            animal.agregarHistorialMedico("Tratamiento completado exitosamente");
            animal.cambiarEstado(new EstadoSano());
        }
    }

    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("Animal " + animal.getId() + " ya está en tratamiento. Día " + diasTratamiento);
    }

    @Override
    public String obtenerDescripcion() {
        return "En Tratamiento - Recuperándose (Día " + diasTratamiento + ")";
    }
}
