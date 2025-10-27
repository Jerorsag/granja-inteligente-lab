package com.grupo2.patterns.creational.factory;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.animales.Cerdo;
import com.grupo2.entity.animales.Gallina;
import com.grupo2.entity.animales.Vaca;

public class AnimalFactoryImpl {
    public static Animal crearAnimal(String tipoCorral, String id, String raza, double peso, int edad) {
        switch (tipoCorral.toLowerCase()) {
            case "lechero":
            case "vaca":
                return new Vaca(id, raza, peso, edad, 20.0); // 20 litros/día
            case "carne":
            case "cerdo":
                return new Cerdo(id, raza, peso, edad, peso * 0.75); // 75% rendimiento
            case "avicola":
            case "gallina":
                return new Gallina(id, raza, peso, edad, 5); // 5 huevos/semana
            default:
                throw new IllegalArgumentException("Tipo de corral no reconocido: " + tipoCorral);
        }
    }
    
    /**
     * Método alternativo que crea con parámetros específicos
     */
    public static Animal crearAnimalPersonalizado(String tipo, String id, String raza, 
                                                  double peso, int edad, double parametroEspecial) {
        switch (tipo.toLowerCase()) {
            case "vaca":
                return new Vaca(id, raza, peso, edad, parametroEspecial);
            case "cerdo":
                return new Cerdo(id, raza, peso, edad, parametroEspecial);
            case "gallina":
                return new Gallina(id, raza, peso, edad, (int) parametroEspecial);
            default:
                throw new IllegalArgumentException("Tipo de animal no reconocido: " + tipo);
        }
    }
}

/**
 * Factory específica para Vacas
 */
class VacaFactory implements AnimalFactory {
    @Override
    public Animal crearAnimal(String id, String raza, double peso, int edad) {
        return new Vaca(id, raza, peso, edad, 20.0);
    }

    @Override
    public String getTipoAnimal() {
        return "Vaca";
    }
}

/**
 * Factory específica para Cerdos
 */
class CerdoFactory implements AnimalFactory {
    @Override
    public Animal crearAnimal(String id, String raza, double peso, int edad) {
        return new Cerdo(id, raza, peso, edad, peso * 0.75);
    }

    @Override
    public String getTipoAnimal() {
        return "Cerdo";
    }
}

/**
 * Factory específica para Gallinas
 */
class GallinaFactory implements AnimalFactory {
    @Override
    public Animal crearAnimal(String id, String raza, double peso, int edad) {
        return new Gallina(id, raza, peso, edad, 5);
    }

    @Override
    public String getTipoAnimal() {
        return "Gallina";
    }
}
