package com.grupo2.patterns.structural.adapter;

import com.grupo2.entity.sensores.Sensor;

/**
 * Patrón Adapter: Adapta la interfaz del sensor legacy a la nueva interfaz
 */
public class SensorAdapter extends Sensor {
    private SensorLegacy sensorLegacy;

    public SensorAdapter(SensorLegacy sensorLegacy) {
        super(sensorLegacy.getCodigo(), "Sensor Legacy Adaptado", sensorLegacy.getLugar());
        this.sensorLegacy = sensorLegacy;
        this.umbralMinimo = 10.0;
        this.umbralMaximo = 90.0;
    }

    @Override
    public void tomarLectura() {
        // Adapta el método antiguo al nuevo
        sensorLegacy.realizarMedicion();
        valorActual = sensorLegacy.obtenerDato();

        System.out.println("📊 [ADAPTADO] Sensor " + id + " [" + ubicacion + "]: " +
                String.format("%.1f", valorActual));

        verificarUmbrales();
    }

    /**
     * Permite acceso al sensor legacy subyacente si es necesario
     */
    public SensorLegacy getSensorLegacy() {
        return sensorLegacy;
    }
}
