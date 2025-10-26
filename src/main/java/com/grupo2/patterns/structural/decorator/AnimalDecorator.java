package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;

/**
 * Patrón Decorator: Clase base abstracta para decoradores de animales
 */
public abstract class AnimalDecorator extends Animal {
    protected Animal animalDecorado;

    public AnimalDecorator(Animal animal) {
        super();
        this.animalDecorado = animal;
    }

    @Override
    public void mostrarInfo() {
        animalDecorado.mostrarInfo();
    }

    @Override
    public String getId() {
        return animalDecorado.getId();
    }

    @Override
    public String getTipo() {
        return animalDecorado.getTipo();
    }

    @Override
    public double getPeso() {
        return animalDecorado.getPeso();
    }

    @Override
    public void alimentar(double cantidad) {
        animalDecorado.alimentar(cantidad);
    }
}
