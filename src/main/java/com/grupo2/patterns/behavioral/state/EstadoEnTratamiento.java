package com.grupo2.patterns.behavioral.state;

import com.grupo2.entity.animales.Animal;

/**
 * Estado concreto: Animal en Tratamiento
 * Dieta especial con recuperación progresiva de actividad.
 * Transición: EnTratamiento -> Sano (después de 3 días)
 */
public class EstadoEnTratamiento implements Estado {
    
    /** Contador de días de tratamiento (se completa en 3 días) */
    private int diasTratamiento = 0;
    
    /**
     * Dieta especial al 70%, ganancia de peso del 7%.
     * El nivel de actividad aumenta +5 por día (máximo 100).
     * Después de 3 días, el animal se recupera completamente.
     */
    @Override
    public void alimentar(Animal animal, double cantidad) {
        System.out.println("Animal " + animal.getId() + " en tratamiento, dieta especial: " + (cantidad * 0.7) + " kg");
        // Mejor absorción que enfermo, pero no óptima
        animal.setPeso(animal.getPeso() + (cantidad * 0.07));
        // Recuperación gradual de actividad (máximo 100)
        animal.setNivelActividad(Math.min(100, animal.getNivelActividad() + 5));
        
        // Incrementa contador de días
        diasTratamiento++;
        // Verifica si completó el tratamiento (3 días)
        if (diasTratamiento >= 3) {
            System.out.println("Tratamiento completado para animal " + animal.getId());
            animal.agregarHistorialMedico("Tratamiento completado exitosamente");
            // Transición a estado Sano
            animal.cambiarEstado(new EstadoSano());
        }
    }

    /**
     * Informa que el animal ya está en tratamiento.
     * No inicia un nuevo tratamiento para evitar sobremedicación.
     */
    @Override
    public void aplicarTratamiento(Animal animal) {
        System.out.println("Animal " + animal.getId() + " ya está en tratamiento. Día " + diasTratamiento);
    }

    @Override
    public String obtenerDescripcion() {
        return "En Tratamiento - Recuperándose (Día " + diasTratamiento + ")";
    }
}
