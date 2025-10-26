package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>AnimalConVacuna</h1>
 * <p>
 * Decorador concreto que implementa el patrón <b>Decorator</b> para extender la funcionalidad
 * de un {@link Animal} con un sistema de registro y control de vacunación.
 * Permite registrar la aplicación de vacunas, calcular la próxima dosis y determinar
 * si el animal requiere revacunación.
 * </p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Registrar el tipo y la fecha de vacunación aplicada.</li>
 *   <li>Calcular automáticamente la próxima dosis (cada 6 meses por defecto).</li>
 *   <li>Actualizar el historial médico del animal decorado.</li>
 *   <li>Mostrar información detallada sobre el estado de vacunación.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Sebastian Ordoñez</p>
 * <p><b>Versión:</b> 1.0</p>
 * <p><b>Patrón:</b> Structural → Decorator</p>
 */
public class AnimalConVacuna extends AnimalDecorator {

    /** Logger para trazabilidad de eventos de vacunación. */
    private static final Logger LOGGER = Logger.getLogger(AnimalConVacuna.class.getName());

    /** Tipo de vacuna administrada (por ejemplo, rabia, fiebre aftosa, etc.). */
    private final String tipoVacuna;

    /** Fecha en la que se aplicó la vacuna más reciente. */
    private LocalDate fechaVacunacion;

    /** Fecha programada para la próxima dosis de refuerzo. */
    private LocalDate proximaDosis;

    /**
     * Crea un decorador de vacunación para el animal especificado.
     *
     * @param animal instancia base de {@link Animal} que será decorada
     * @param tipoVacuna tipo de vacuna aplicada
     * @throws IllegalArgumentException si {@code tipoVacuna} es nula o vacía
     */
    public AnimalConVacuna(final Animal animal, final String tipoVacuna) {
        super(animal);

        if (tipoVacuna == null || tipoVacuna.isBlank()) {
            throw new IllegalArgumentException("El tipo de vacuna no puede ser nulo ni vacío.");
        }

        this.tipoVacuna = tipoVacuna;
        this.fechaVacunacion = LocalDate.now();
        this.proximaDosis = fechaVacunacion.plusMonths(6); // intervalo estándar de revacunación
    }

    /**
     * Muestra la información del animal junto con su estado de vacunación.
     * <p>Extiende el método {@link Animal#mostrarInfo()} con detalles sobre la vacuna aplicada,
     * fechas relevantes y estado actual del refuerzo.</p>
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        LOGGER.log(Level.INFO, "--- Estado de Vacunación ---");
        LOGGER.log(Level.INFO, "Vacuna: {0}", tipoVacuna);
        LOGGER.log(Level.INFO, "Fecha de Vacunación: {0}", fechaVacunacion);
        LOGGER.log(Level.INFO, "Próxima Dosis: {0}", proximaDosis);
        LOGGER.log(Level.INFO, "Estado: {0}",
                necesitaRevacunacion() ? "⚠ Requiere revacunación" : "✓ Al día");
    }

    /**
     * Determina si el animal requiere una nueva dosis de vacunación.
     *
     * @return {@code true} si la fecha actual es posterior a la próxima dosis;
     *         {@code false} si aún está al día.
     */
    public boolean necesitaRevacunacion() {
        return LocalDate.now().isAfter(proximaDosis);
    }

    /**
     * Registra una nueva aplicación de vacuna para el animal.
     * <p>Actualiza la fecha de vacunación y programa la próxima dosis
     * para dentro de 6 meses. También actualiza el historial médico.</p>
     */
    public void revacunar() {
        this.fechaVacunacion = LocalDate.now();
        this.proximaDosis = fechaVacunacion.plusMonths(6);
        animalDecorado.agregarHistorialMedico("Revacunación aplicada: " + tipoVacuna + " - " + fechaVacunacion);

        LOGGER.log(Level.INFO, "💉 Animal {0} revacunado con {1}",
                new Object[]{getId(), tipoVacuna});
    }

    /**
     * Devuelve el tipo de vacuna aplicada al animal.
     *
     * @return tipo de vacuna
     */
    public String getTipoVacuna() {
        return tipoVacuna;
    }

    /**
     * Devuelve la fecha programada para la próxima dosis.
     *
     * @return fecha de próxima dosis de vacunación
     */
    public LocalDate getProximaDosis() {
        return proximaDosis;
    }
}
