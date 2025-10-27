package com.grupo2.patterns.creational.factory;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.animales.Gallina;

public class GallinaPonedoraFactory implements AbstractAnimalFactory {
    @Override
    public Animal crearAnimal(String id, double peso, int edad) {
        return new Gallina(id, "Leghorn", peso, edad, 6);
    }

    @Override
    public String crearAlimento() {
        return "Alimento para Aves de Postura con Calcio";
    }

    @Override
    public String crearEntorno() {
        return "Gallinero con nidos automáticos";
    }
}
