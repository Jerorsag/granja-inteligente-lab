package com.grupo2.entity.animales;

public class Cerdo extends Animal {
    private double rendimientoCarne; // kg

    public Cerdo(String id, String raza, double peso, int edad, double rendimientoCarne) {
        super(id, "Cerdo", raza, peso, edad, "carne");
        this.rendimientoCarne = rendimientoCarne;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Rendimiento de Carne: " + rendimientoCarne + " kg");
    }

    public double getRendimientoCarne() {
        return rendimientoCarne;
    }

    public void setRendimientoCarne(double rendimientoCarne) {
        this.rendimientoCarne = rendimientoCarne;
    }
}
