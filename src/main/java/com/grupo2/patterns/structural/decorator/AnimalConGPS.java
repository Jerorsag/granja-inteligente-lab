package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;

/**
 * <b>Clase:</b> AnimalConGPS <br>
 * <b>Patrón:</b> Decorator (Decorador concreto) <br><br>
 *
 * Esta clase extiende la funcionalidad del objeto {@link Animal} agregando
 * capacidades de rastreo GPS. Permite activar o desactivar el sistema,
 * actualizar las coordenadas del animal y mostrar información relacionada
 * con el dispositivo instalado. <br><br>
 *
 * El decorador mantiene la composición con un objeto {@link Animal} existente,
 * añadiendo comportamiento sin modificar la clase base.
 *
 * @author Sebastian Ordoñez
 * @version 1.0
 * @since 2025-10
 */
public class AnimalConGPS extends AnimalDecorator {

    /** Coordenadas actuales del animal en formato "latitud, longitud". */
    private String coordenadasGPS;

    /** Fecha en la que se instaló el dispositivo GPS. */
    private LocalDate fechaInstalacion;

    /** Estado del GPS: activo o inactivo. */
    private boolean gpsActivo;

    /**
     * Constructor del decorador GPS.
     *
     * @param animal Objeto base {@link Animal} al que se agregará la funcionalidad GPS.
     */
    public AnimalConGPS(Animal animal) {
        super(animal);
        this.coordenadasGPS = "0.0, 0.0";
        this.fechaInstalacion = LocalDate.now();
        this.gpsActivo = true;
    }

    /**
     * Muestra la información general del animal y las características del GPS.
     *
     * Sobrescribe el método {@link Animal#mostrarInfo()} para incluir datos del dispositivo GPS.
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("--- Características GPS ---");
        System.out.println("GPS Activo: " + (gpsActivo ? "Sí" : "No"));
        System.out.println("Coordenadas: " + coordenadasGPS);
        System.out.println("Instalado: " + fechaInstalacion);
    }

    /**
     * Actualiza la posición GPS del animal.
     *
     * @param latitud Nueva latitud del animal.
     * @param longitud Nueva longitud del animal.
     */
    public void actualizarPosicion(double latitud, double longitud) {
        this.coordenadasGPS = latitud + ", " + longitud;
        System.out.println("📍 GPS actualizado para " + getId() + ": " + coordenadasGPS);
    }

    /**
     * Rastrear la ubicación actual del animal si el GPS está activo.
     *
     * @return Coordenadas actuales si el GPS está activo, de lo contrario devuelve "GPS inactivo".
     */
    public String rastrear() {
        if (gpsActivo) {
            System.out.println("🛰 Rastreando animal " + getId() + "...");
            return coordenadasGPS;
        }
        return "GPS inactivo";
    }

    /**
     * Activa el sistema GPS del animal.
     */
    public void activarGPS() {
        gpsActivo = true;
        System.out.println("✓ GPS activado para " + getId());
    }

    /**
     * Desactiva el sistema GPS del animal.
     */
    public void desactivarGPS() {
        gpsActivo = false;
        System.out.println("✓ GPS desactivado para " + getId());
    }

    /**
     * Obtiene las coordenadas GPS actuales del animal.
     *
     * @return Coordenadas en formato "latitud, longitud".
     */
    public String getCoordenadasGPS() {
        return coordenadasGPS;
    }
}
