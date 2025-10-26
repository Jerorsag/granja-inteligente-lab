package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Decorator concreto: Agrega historial reproductivo
 */
public class AnimalConHistorialReproductivo extends AnimalDecorator {
    private List<LocalDate> fechasReproduccion;
    private int numeroDescendientes;
    private boolean aptoReproduccion;

    public AnimalConHistorialReproductivo(Animal animal) {
        super(animal);
        this.fechasReproduccion = new ArrayList<>();
        this.numeroDescendientes = 0;
        this.aptoReproduccion = true;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("--- Historial Reproductivo ---");
        System.out.println("Apto para reproducción: " + (aptoReproduccion ? "Sí" : "No"));
        System.out.println("Número de descendientes: " + numeroDescendientes);
        System.out.println("Reproducciones registradas: " + fechasReproduccion.size());
    }

    public void registrarReproduccion(int numCrias) {
        fechasReproduccion.add(LocalDate.now());
        numeroDescendientes += numCrias;
        animalDecorado.agregarHistorialMedico("Reproducción exitosa: " + numCrias + " crías");
        System.out.println("✓ Reproducción registrada para " + getId() + ": " + numCrias + " crías");
    }

    public boolean isAptoReproduccion() {
        return aptoReproduccion;
    }

    public void setAptoReproduccion(boolean apto) {
        this.aptoReproduccion = apto;
    }

    public int getNumeroDescendientes() {
        return numeroDescendientes;
    }
}
