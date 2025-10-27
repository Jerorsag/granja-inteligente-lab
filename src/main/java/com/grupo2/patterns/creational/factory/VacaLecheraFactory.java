package com.grupo2.patterns.creational.factory;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.animales.Vaca;

public class VacaLecheraFactory implements AbstractAnimalFactory {
     @Override
    public Animal crearAnimal(String id, double peso, int edad) {
        return new Vaca(id, "Holstein", peso, edad, 25.0);
    }

    @Override
    public String crearAlimento() {
        return "Alimento Balanceado Lechero Premium";
    }

    @Override
    public String crearEntorno() {
        return "Establo con sistema de ordeño automático";
    }
}
