package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;

/**
 * <h1>AnimalDecorator</h1>
 * <p>
 * Clase base abstracta para la implementación del patrón estructural <b>Decorator</b>.
 * Permite extender dinámicamente las funcionalidades de la clase {@link Animal} sin
 * modificar su código fuente original. Todos los decoradores concretos deben heredar
 * de esta clase y delegar las operaciones comunes al objeto {@code animalDecorado}.
 * </p>
 *
 * <h2>Rol en el patrón Decorator</h2>
 * <ul>
 *   <li>Actúa como una superclase intermedia entre {@link Animal} y los decoradores concretos.</li>
 *   <li>Contiene una referencia al objeto {@link Animal} decorado, al que delega la funcionalidad base.</li>
 *   <li>Permite agregar comportamientos adicionales de manera flexible y combinable.</li>
 * </ul>
 *
 * <p><b>Ejemplo de decoradores concretos:</b></p>
 * <ul>
 *   <li>{@link AnimalConGPS}: agrega rastreo satelital.</li>
 *   <li>{@link AnimalConVacuna}: gestiona el estado de vacunación.</li>
 *   <li>{@link AnimalConHistorialReproductivo}: controla el historial reproductivo.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Sebastian Ordoñez</p>
 * <p><b>Versión:</b> 1.0</p>
 * <p><b>Patrón:</b> Structural → Decorator</p>
 */
public abstract class AnimalDecorator extends Animal {

    /**
     * Referencia al objeto {@link Animal} que está siendo decorado.
     * <p>Se recomienda que los decoradores concretos accedan a esta variable
     * mediante composición para extender funcionalidad sin romper la encapsulación.</p>
     */
    protected final Animal animalDecorado;

    /**
     * Constructor base para cualquier decorador de {@link Animal}.
     *
     * @param animal instancia de {@link Animal} que se desea decorar
     * @throws IllegalArgumentException si el parámetro {@code animal} es {@code null}
     */
    public AnimalDecorator(final Animal animal) {
        super();
        if (animal == null) {
            throw new IllegalArgumentException("El animal decorado no puede ser null.");
        }
        this.animalDecorado = animal;
    }

    /**
     * Muestra la información general del animal decorado.
     * <p>Los decoradores concretos pueden sobrescribir este método
     * para añadir información adicional.</p>
     */
    @Override
    public void mostrarInfo() {
        animalDecorado.mostrarInfo();
    }

    /**
     * Devuelve el identificador único del animal decorado.
     *
     * @return ID del animal
     */
    @Override
    public String getId() {
        return animalDecorado.getId();
    }

    /**
     * Devuelve el tipo o especie del animal decorado.
     *
     * @return tipo de animal
     */
    @Override
    public String getTipo() {
        return animalDecorado.getTipo();
    }

    /**
     * Devuelve el peso actual del animal decorado.
     *
     * @return peso del animal en kilogramos
     */
    @Override
    public double getPeso() {
        return animalDecorado.getPeso();
    }

    /**
     * Alimenta al animal decorado con una cantidad específica de alimento.
     *
     * @param cantidad cantidad de alimento suministrado
     */
    @Override
    public void alimentar(final double cantidad) {
        animalDecorado.alimentar(cantidad);
    }
}
