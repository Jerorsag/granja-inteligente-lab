package com.grupo2.entity.animales;

public class Gallina extends Animal {
    private int huevosPorSemana;

    public Gallina(String id, String raza, double peso, int edad, int huevosPorSemana) {
        super(id, "Gallina", raza, peso, edad, "huevos");
        this.huevosPorSemana = huevosPorSemana;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Producción de Huevos: " + huevosPorSemana + " huevos/semana");
    }

    public int getHuevosPorSemana() {
        return huevosPorSemana;
    }

    public void setHuevosPorSemana(int huevosPorSemana) {
        this.huevosPorSemana = huevosPorSemana;
    }

    public void recolectarHuevos() {
        System.out.println("Recolectando huevos de gallina " + getId() + ": " + huevosPorSemana + " huevos");
    }
}
