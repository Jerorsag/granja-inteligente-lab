package com.grupo2.patterns.creational.builder;

import com.grupo2.entity.animales.Animal;

public class AnimalDirector {
    private AnimalBuilder builder;

    public AnimalDirector(AnimalBuilder builder) {
        this.builder = builder;
    }

    /**
     * Construye una vaca lechera estándar
     */
    public Animal construirVacaLechera(String id) {
        return builder
            .setId(id)
            .setTipo("vaca")
            .setRaza("Holstein")
            .setPeso(600)
            .setEdad(24)
            .setNivelActividad(100)
            .setParametroEspecifico(25.0) // 25 litros/día
            .build();
    }

    /**
     * Construye un cerdo de engorde estándar
     */
    public Animal construirCerdoEngorde(String id) {
        return builder
            .setId(id)
            .setTipo("cerdo")
            .setRaza("Duroc")
            .setPeso(120)
            .setEdad(6)
            .setNivelActividad(100)
            .setParametroEspecifico(90.0) // 90 kg rendimiento
            .build();
    }

    /**
     * Construye una gallina ponedora estándar
     */
    public Animal construirGallinaPonedora(String id) {
        return builder
            .setId(id)
            .setTipo("gallina")
            .setRaza("Leghorn")
            .setPeso(2.0)
            .setEdad(5)
            .setNivelActividad(100)
            .setParametroEspecifico(6.0) // 6 huevos/semana
            .build();
    }

    /**
     * Construye un animal con historial médico
     */
    public Animal construirAnimalConHistorial(String id, String tipo, String raza, 
                                              double peso, int edad, String historial) {
        return builder
            .setId(id)
            .setTipo(tipo)
            .setRaza(raza)
            .setPeso(peso)
            .setEdad(edad)
            .setNivelActividad(80)
            .setHistorialMedico(historial)
            .build();
    }

    public void setBuilder(AnimalBuilder builder) {
        this.builder = builder;
    }
}
