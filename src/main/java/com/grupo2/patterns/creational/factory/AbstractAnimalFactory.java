package com.grupo2.patterns.creational.factory;

import com.grupo2.entity.animales.Animal;

public interface AbstractAnimalFactory {
    Animal crearAnimal(String id, double peso, int edad);
    String crearAlimento();
    String crearEntorno();
}
