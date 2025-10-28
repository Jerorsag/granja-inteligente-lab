package com.grupo2.entity.animales;

public class Vaca extends Animal {
    private double produccionLeche; // litros por día

    public Vaca(String id, String raza, double peso, int edad, double produccionLeche) {
        super(id, "Vaca", raza, peso, edad, "leche");
        this.produccionLeche = produccionLeche;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Producción de Leche: " + produccionLeche + " L/día");
    }

    public double getProduccionLeche() {
        return produccionLeche;
    }

    public void setProduccionLeche(double produccionLeche) {
        this.produccionLeche = produccionLeche;
    }

    public void ordeñar() {
        System.out.println("Ordeñando vaca " + getId() + ": " + produccionLeche + " litros");
    }
}
