package com.grupo2.patterns.structural.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p><b>Autor:</b> Sebastian Ordoñez</p>
 * <p><b>Versión:</b> 1.0</p>
 */
public class SensorLegacy {

    /** Logger para trazabilidad de lecturas y diagnósticos. */
    private static final Logger LOGGER = Logger.getLogger(SensorLegacy.class.getName());

    /** Código único del sensor heredado. */
    private String codigo;

    /** Ubicación física del sensor (por ejemplo: laboratorio, establo, etc.). */
    private String lugar;

    /** Última medición registrada (valor simulado). */
    private double medicion;

    /**
     * Crea una instancia del sensor heredado con su código y ubicación.
     *
     * @param codigo identificador único del sensor
     * @param lugar ubicación o contexto donde está instalado el sensor
     */
    public SensorLegacy(final String codigo, final String lugar) {
        this.codigo = codigo;
        this.lugar = lugar;
        this.medicion = 0.0;
    }

    /**
     * Realiza una medición simulada.
     * <p>
     * En la implementación original (legacy), este método actualiza la variable
     * {@link #medicion} con un valor aleatorio entre 0 y 100. En un sistema real,
     * podría representar una lectura de hardware o una llamada a una API antigua.
     * </p>
     * <p><b>Nota:</b> En sistemas modernos, se recomienda reemplazar el uso directo
     * de {@code System.out.println} por un logger configurable.</p>
     */
    public void realizarMedicion() {
        medicion = Math.random() * 100;
        LOGGER.log(Level.INFO, "[LEGACY] Sensor {0} midió: {1}", new Object[]{codigo, medicion});
    }

    /**
     * Devuelve la última medición registrada por el sensor.
     *
     * @return valor numérico de la última medición (en rango 0–100)
     */
    public double obtenerDato() {
        return medicion;
    }

    /**
     * Devuelve el código único del sensor.
     *
     * @return código identificador del sensor
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve la ubicación asociada al sensor.
     *
     * @return descripción o nombre del lugar donde se encuentra el sensor
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Permite modificar el código identificador del sensor.
     *
     * <p><b>Advertencia:</b> este método no debería usarse en sistemas modernos,
     * ya que puede romper la trazabilidad de los sensores en producción.</p>
     *
     * @param codigo nuevo código del sensor
     */
    public void ajustarCodigo(final String codigo) {
        this.codigo = codigo;
    }
}
