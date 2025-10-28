package com.grupo2.patterns.structural.adapter;

/**
 * Clase Legacy: Sensor antiguo con interfaz incompatible
 */
public class SensorLegacy {
    private String codigo;
    private String lugar;
    private double medicion;

    public SensorLegacy(String codigo, String lugar) {
        this.codigo = codigo;
        this.lugar = lugar;
        this.medicion = 0.0;
    }

    // Métodos con nombres antiguos e incompatibles
    public void realizarMedicion() {
        medicion = Math.random() * 100;
        System.out.println("[LEGACY] Sensor " + codigo + " midió: " + medicion);
    }

    public double obtenerDato() {
        return medicion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getLugar() {
        return lugar;
    }

    public void ajustarCodigo(String codigo) {
        this.codigo = codigo;
    }
}
