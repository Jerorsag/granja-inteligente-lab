package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;

/**
 * <b>Clase: AnimalConVacuna</b><br>
 * <br>
 * Decorator concreto que extiende la funcionalidad de la clase {@link Animal}
 * para incorporar el registro de vacunación.
 *
 * <p>Este decorador agrega información sobre el tipo de vacuna aplicada,
 * la fecha de vacunación y la fecha programada para la próxima dosis.
 * También permite verificar si el animal requiere revacunación
 * y registrar dicho evento en el historial médico.</p>
 *
 * <p><b>Patrón aplicado:</b> Structural – Decorator</p>
 * <p><b>Uso:</b> Permite añadir dinámicamente responsabilidades
 * relacionadas con la gestión de vacunas sin modificar la clase base {@link Animal}.</p>
 *
 * @author Grupo 2
 * @version 1.0
 */
public class AnimalConVacuna extends AnimalDecorator {

    /** Tipo de vacuna aplicada al animal (ej. “Antirrábica”, “Parvovirus”) */
    private String tipoVacuna;

    /** Fecha en la que se aplicó la vacuna */
    private LocalDate fechaVacunacion;

    /** Fecha programada para la próxima dosis de la vacuna */
    private LocalDate proximaDosis;

    /**
     * Constructor del decorador.
     *
     * @param animal instancia base del animal que será decorada.
     * @param tipoVacuna nombre de la vacuna aplicada.
     */
    public AnimalConVacuna(Animal animal, String tipoVacuna) {
        super(animal);
        this.tipoVacuna = tipoVacuna;
        this.fechaVacunacion = LocalDate.now();
        this.proximaDosis = fechaVacunacion.plusMonths(6);
    }

    /**
     * Muestra la información general del animal junto con
     * el estado actual de vacunación.
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("--- Estado de Vacunación ---");
        System.out.println("Vacuna: " + tipoVacuna);
        System.out.println("Fecha de Vacunación: " + fechaVacunacion);
        System.out.println("Próxima Dosis: " + proximaDosis);
        System.out.println("Estado: " + (necesitaRevacunacion()
                ? "⚠ Requiere revacunación"
                : "✓ Al día"));
    }

    /**
     * Verifica si el animal requiere una nueva dosis de vacunación.
     *
     * @return true si la fecha actual es posterior a la fecha de la próxima dosis.
     */
    public boolean necesitaRevacunacion() {
        return LocalDate.now().isAfter(proximaDosis);
    }

    /**
     * Registra una nueva vacunación, actualizando las fechas y
     * añadiendo el evento al historial médico del animal decorado.
     */
    public void revacunar() {
        this.fechaVacunacion = LocalDate.now();
        this.proximaDosis = fechaVacunacion.plusMonths(6);
        animalDecorado.agregarHistorialMedico("Revacunación: " + tipoVacuna + " - " + fechaVacunacion);
        System.out.println("💉 Animal " + getId() + " revacunado con " + tipoVacuna);
    }

    /** @return el tipo de vacuna aplicada */
    public String getTipoVacuna() {
        return tipoVacuna;
    }

    /** @return la fecha de la próxima dosis */
    public LocalDate getProximaDosis() {
        return proximaDosis;
    }
}
