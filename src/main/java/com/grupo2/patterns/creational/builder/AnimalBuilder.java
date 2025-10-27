package com.grupo2.patterns.creational.builder;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.animales.Vaca;
import com.grupo2.entity.animales.Cerdo;
import com.grupo2.entity.animales.Gallina;

public class AnimalBuilder {
    private String id;
    private String tipo;
    private String raza;
    private double peso;
    private int edad;
    private String historialMedico;
    private int nivelActividad;
    private String proposito;
    private double parametroEspecifico; // producción leche, rendimiento carne, huevos

    public AnimalBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public AnimalBuilder setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public AnimalBuilder setRaza(String raza) {
        this.raza = raza;
        return this;
    }

    public AnimalBuilder setPeso(double peso) {
        this.peso = peso;
        return this;
    }

    public AnimalBuilder setEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public AnimalBuilder setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
        return this;
    }

    public AnimalBuilder setNivelActividad(int nivelActividad) {
        this.nivelActividad = nivelActividad;
        return this;
    }

    public AnimalBuilder setProposito(String proposito) {
        this.proposito = proposito;
        return this;
    }

    public AnimalBuilder setParametroEspecifico(double parametroEspecifico) {
        this.parametroEspecifico = parametroEspecifico;
        return this;
    }

    /**
     * Construye el animal según el tipo especificado
     */
    public Animal build() {
        Animal animal;
        
        switch (tipo.toLowerCase()) {
            case "vaca":
                animal = new Vaca(id, raza, peso, edad, parametroEspecifico > 0 ? parametroEspecifico : 20.0);
                break;
            case "cerdo":
                animal = new Cerdo(id, raza, peso, edad, parametroEspecifico > 0 ? parametroEspecifico : peso * 0.75);
                break;
            case "gallina":
                animal = new Gallina(id, raza, peso, edad, parametroEspecifico > 0 ? (int) parametroEspecifico : 5);
                break;
            default:
                throw new IllegalArgumentException("Tipo de animal no válido: " + tipo);
        }

        if (historialMedico != null && !historialMedico.isEmpty()) {
            animal.setHistorialMedico(historialMedico);
        }
        
        if (nivelActividad > 0) {
            animal.setNivelActividad(nivelActividad);
        }

        return animal;
    }

    /**
     * Resetea el builder para construir otro animal
     */
    public void reset() {
        this.id = null;
        this.tipo = null;
        this.raza = null;
        this.peso = 0;
        this.edad = 0;
        this.historialMedico = null;
        this.nivelActividad = 0;
        this.proposito = null;
        this.parametroEspecifico = 0;
    }
}
