package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>AnimalConHistorialReproductivo</h1>
 * <p>
 * Decorador concreto que implementa el patrón <b>Decorator</b> para extender la funcionalidad
 * de un {@link Animal} añadiendo un historial reproductivo. Este decorador permite registrar
 * eventos de reproducción, contar descendientes y determinar la aptitud reproductiva del animal.
 * </p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Registrar fechas y número de crías por evento reproductivo.</li>
 *   <li>Actualizar el historial médico del animal decorado.</li>
 *   <li>Controlar el estado de aptitud reproductiva.</li>
 *   <li>Mostrar información ampliada del historial reproductivo.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Sebastian Ordoñez</p>
 * <p><b>Versión:</b> 1.0</p>
 * <p><b>Patrón:</b> Structural → Decorator</p>
 */
public class AnimalConHistorialReproductivo extends AnimalDecorator {

    /** Logger para registrar eventos y trazabilidad. */
    private static final Logger LOGGER = Logger.getLogger(AnimalConHistorialReproductivo.class.getName());

    /** Fechas en las que se registraron eventos reproductivos. */
    private final List<LocalDate> fechasReproduccion;

    /** Número total de descendientes del animal. */
    private int numeroDescendientes;

    /** Indica si el animal está actualmente apto para reproducción. */
    private boolean aptoReproduccion;

    /**
     * Crea un decorador de historial reproductivo para el animal especificado.
     *
     * @param animal instancia base de {@link Animal} que será decorada
     */
    public AnimalConHistorialReproductivo(final Animal animal) {
        super(animal);
        this.fechasReproduccion = new ArrayList<>();
        this.numeroDescendientes = 0;
        this.aptoReproduccion = true;
    }

    /**
     * Muestra la información del animal junto con su historial reproductivo.
     * <p>Extiende el método {@link Animal#mostrarInfo()} con detalles sobre descendencia
     * y estado reproductivo.</p>
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        LOGGER.log(Level.INFO, "--- Historial Reproductivo ---");
        LOGGER.log(Level.INFO, "Apto para reproducción: {0}", (aptoReproduccion ? "Sí" : "No"));
        LOGGER.log(Level.INFO, "Número de descendientes: {0}", numeroDescendientes);
        LOGGER.log(Level.INFO, "Reproducciones registradas: {0}", fechasReproduccion.size());
    }

    /**
     * Registra un nuevo evento de reproducción.
     * <p>Se añade la fecha actual al historial, se incrementa el contador de descendientes
     * y se actualiza el historial médico del animal decorado.</p>
     *
     * @param numCrias número de crías nacidas en el evento
     * @throws IllegalArgumentException si {@code numCrias} es negativo
     */
    public void registrarReproduccion(final int numCrias) {
        if (numCrias < 0) {
            throw new IllegalArgumentException("El número de crías no puede ser negativo.");
        }

        fechasReproduccion.add(LocalDate.now());
        numeroDescendientes += numCrias;

        // Actualización del historial médico del animal base.
        animalDecorado.agregarHistorialMedico("Reproducción exitosa: " + numCrias + " crías");

        LOGGER.log(Level.INFO, "✓ Reproducción registrada para {0}: {1} crías",
                new Object[]{getId(), numCrias});
    }

    /**
     * Indica si el animal está apto para reproducción.
     *
     * @return {@code true} si el animal está apto; {@code false} en caso contrario
     */
    public boolean isAptoReproduccion() {
        return aptoReproduccion;
    }

    /**
     * Permite modificar el estado de aptitud reproductiva del animal.
     *
     * @param apto nuevo estado de aptitud
     */
    public void setAptoReproduccion(final boolean apto) {
        this.aptoReproduccion = apto;
    }

    /**
     * Devuelve el número total de descendientes registrados.
     *
     * @return cantidad acumulada de crías
     */
    public int getNumeroDescendientes() {
        return numeroDescendientes;
    }
}
