package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;

/**
 * Decorator concreto: Agrega capacidad de rastreo GPS
 */
public class AnimalConGPS extends AnimalDecorator {
    private String coordenadasGPS;
    private LocalDate fechaInstalacion;
    private boolean gpsActivo;

    public AnimalConGPS(Animal animal) {
        super(animal);
        this.coordenadasGPS = "0.0, 0.0";
        this.fechaInstalacion = LocalDate.now();
        this.gpsActivo = true;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("--- Características GPS ---");
        System.out.println("GPS Activo: " + (gpsActivo ? "Sí" : "No"));
        System.out.println("Coordenadas: " + coordenadasGPS);
        System.out.println("Instalado: " + fechaInstalacion);
    }

    public void actualizarPosicion(double latitud, double longitud) {
        this.coordenadasGPS = latitud + ", " + longitud;
        System.out.println("📍 GPS actualizado para " + getId() + ": " + coordenadasGPS);
    }

    public String rastrear() {
        if (gpsActivo) {
            System.out.println("🛰 Rastreando animal " + getId() + "...");
            return coordenadasGPS;
        }
        return "GPS inactivo";
    }

    public void activarGPS() {
        gpsActivo = true;
        System.out.println("✓ GPS activado para " + getId());
    }

    public void desactivarGPS() {
        gpsActivo = false;
        System.out.println("✓ GPS desactivado para " + getId());
    }

    public String getCoordenadasGPS() {
        return coordenadasGPS;
    }
}
