package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Clase:</b> AnimalConHistorialReproductivo <br>
 * <b>Patrón:</b> Decorator (Decorador concreto) <br><br>
 *
 * Este decorador agrega al objeto {@link Animal} la capacidad de registrar,
 * almacenar y mostrar su historial reproductivo. Permite llevar el control de las
 * fechas de reproducción, el número total de descendientes y el estado de
 * aptitud reproductiva del animal. <br><br>
 *
 * Este componente forma parte de la arquitectura estructural del sistema,
 * ampliando las funcionalidades del objeto base sin alterar su implementación.
 *
 * @author Sebastian Ordoñez
 * @version 1.0
 * @since 2025-10
 */
public class AnimalConHistorialReproductivo extends AnimalDecorator {

    /** Lista con las fechas de cada evento reproductivo del animal. */
    private List<LocalDate> fechasReproduccion;

    /** Número total de crías o descendientes que ha tenido el animal. */
    private int numeroDescendientes;

    /** Indica si el animal está actualmente apto para reproducción. */
    private boolean aptoReproduccion;

    /**
     * Constructor del decorador. Inicializa los valores por defecto del historial reproductivo.
     *
     * @param animal Objeto base {@link Animal} al que se le añadirá la funcionalidad de historial reproductivo.
     */
    public AnimalConHistorialReproductivo(Animal animal) {
        super(animal);
        this.fechasReproduccion = new ArrayList<>();
        this.numeroDescendientes = 0;
        this.aptoReproduccion = true;
    }

    /**
     * Muestra la información general del animal junto con su historial reproductivo.
     *
     * Sobrescribe el método {@link Animal#mostrarInfo()} para incluir datos de reproducción.
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("--- Historial Reproductivo ---");
        System.out.println("Apto para reproducción: " + (aptoReproduccion ? "Sí" : "No"));
        System.out.println("Número de descendientes: " + numeroDescendientes);
        System.out.println("Reproducciones registradas: " + fechasReproduccion.size());
    }

    /**
     * Registra un nuevo evento de reproducción en el historial del animal.
     *
     * @param numCrias Número de crías nacidas en el evento reproductivo.
     */
    public void registrarReproduccion(int numCrias) {
        fechasReproduccion.add(LocalDate.now());
        numeroDescendientes += numCrias;
        animalDecorado.agregarHistorialMedico("Reproducción exitosa: " + numCrias + " crías");
        System.out.println("✓ Reproducción registrada para " + getId() + ": " + numCrias + " crías");
    }

    /**
     * Verifica si el animal está actualmente apto para la reproducción.
     *
     * @return true si el animal está apto; false en caso contrario.
     */
    public boolean isAptoReproduccion() {
        return aptoReproduccion;
    }

    /**
     * Define si el animal está apto para reproducción.
     *
     * @param apto Valor booleano que indica el nuevo estado reproductivo.
     */
    public void setAptoReproduccion(boolean apto) {
        this.aptoReproduccion = apto;
    }

    /**
     * Devuelve el número total de descendientes del animal.
     *
     * @return Número acumulado de crías registradas.
     */
    public int getNumeroDescendientes() {
        return numeroDescendientes;
    }
}
