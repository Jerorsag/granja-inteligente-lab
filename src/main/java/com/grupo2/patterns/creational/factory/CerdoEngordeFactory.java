package com.grupo2.patterns.creational.factory;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.animales.Cerdo;

public class CerdoEngordeFactory implements AbstractAnimalFactory {
    @Override
    public Animal crearAnimal(String id, double peso, int edad) {
        return new Cerdo(id, "Landrace", peso, edad, peso * 0.80);
    }

    @Override
    public String crearAlimento() {
        return "Concentrado para Engorde Alto en Proteínas";
    }

    @Override
    public String crearEntorno() {
        return "Corral de engorde con temperatura controlada";
    }
}
