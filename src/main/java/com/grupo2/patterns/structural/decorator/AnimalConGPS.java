package com.grupo2.patterns.structural.decorator;

import com.grupo2.entity.animales.Animal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>AnimalConGPS</h1>
 * <p>
 * Decorador concreto que implementa el patrón <b>Decorator</b> para añadir
 * funcionalidad de rastreo GPS a un objeto {@link Animal} existente sin modificar
 * su estructura original.
 * </p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Extender dinámicamente las capacidades de {@link Animal} con rastreo satelital.</li>
 *   <li>Permitir activación, desactivación y actualización de coordenadas GPS.</li>
 *   <li>Proveer trazabilidad de la fecha de instalación del dispositivo GPS.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Sebastian Ordoñez</p>
 * <p><b>Versión:</b> 1.0</p>
 * <p><b>Patrón:</b> Structural → Decorator</p>
 */
public class AnimalConGPS extends AnimalDecorator {

    /** Logger para registro de eventos y seguimiento. */
    private static final Logger LOGGER = Logger.getLogger(AnimalConGPS.class.getName());

    /** Coordenadas GPS actuales del animal en formato "latitud, longitud". */
    private String coordenadasGPS;

    /** Fecha en que se instaló el dispositivo GPS en el animal. */
    private final LocalDate fechaInstalacion;

    /** Estado actual del dispositivo GPS (activo/inactivo). */
    private boolean gpsActivo;

    /**
     * Crea una instancia del decorador GPS asociada a un animal existente.
     * <p>Inicializa las coordenadas con valores por defecto y activa el GPS.</p>
     *
     * @param animal instancia de {@link Animal} que será decorada con funcionalidad GPS
     */
    public AnimalConGPS(final Animal animal) {
        super(animal);
        this.coordenadasGPS = "0.0, 0.0";
        this.fechaInstalacion = LocalDate.now();
        this.gpsActivo = true;
    }

    /**
     * Muestra información general del animal y los detalles del módulo GPS.
     * <p>Extiende el comportamiento de {@link Animal#mostrarInfo()} para incluir
     * la información de rastreo.</p>
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        LOGGER.log(Level.INFO, "--- Características GPS ---");
        LOGGER.log(Level.INFO, "GPS Activo: {0}", (gpsActivo ? "Sí" : "No"));
        LOGGER.log(Level.INFO, "Coordenadas: {0}", coordenadasGPS);
        LOGGER.log(Level.INFO, "Instalado: {0}", fechaInstalacion);
    }

    /**
     * Actualiza la posición actual del animal con nuevas coordenadas GPS.
     *
     * @param latitud  valor de la latitud actual
     * @param longitud valor de la longitud actual
     */
    public void actualizarPosicion(final double latitud, final double longitud) {
        this.coordenadasGPS = latitud + ", " + longitud;
        LOGGER.log(Level.INFO, "📍 GPS actualizado para {0}: {1}", new Object[]{getId(), coordenadasGPS});
    }

    /**
     * Inicia el proceso de rastreo si el GPS está activo.
     *
     * @return coordenadas actuales si el GPS está activo; mensaje indicativo en caso contrario
     */
    public String rastrear() {
        if (gpsActivo) {
            LOGGER.log(Level.INFO, "🛰 Rastreando animal {0}...", getId());
            return coordenadasGPS;
        }
        return "GPS inactivo";
    }

    /**
     * Activa el dispositivo GPS del animal.
     */
    public void activarGPS() {
        gpsActivo = true;
        LOGGER.log(Level.INFO, "✓ GPS activado para {0}", getId());
    }

    /**
     * Desactiva el dispositivo GPS del animal.
     */
    public void desactivarGPS() {
        gpsActivo = false;
        LOGGER.log(Level.INFO, "✓ GPS desactivado para {0}", getId());
    }

    /**
     * Devuelve las coordenadas GPS actuales del animal.
     *
     * @return coordenadas en formato "latitud, longitud"
     */
    public String getCoordenadasGPS() {
        return coordenadasGPS;
    }
}
