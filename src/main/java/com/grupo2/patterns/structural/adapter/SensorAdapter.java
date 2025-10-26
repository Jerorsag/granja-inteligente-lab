package com.grupo2.patterns.structural.adapter;

import com.grupo2.entity.sensores.Sensor;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 *
 * @author Sebastian Ordoñez
 * @since 1.0
 */
public class SensorAdapter extends Sensor {

    /** Logger de clase para trazar eventos y diagnósticos (reemplaza System.out). */
    private static final Logger LOGGER = Logger.getLogger(SensorAdapter.class.getName());

    /** Umbral mínimo por defecto para disparar alertas/acciones. */
    private static final double DEFAULT_UMBRAL_MINIMO = 10.0;

    /** Umbral máximo por defecto para disparar alertas/acciones. */
    private static final double DEFAULT_UMBRAL_MAXIMO = 90.0;

    /** Dependencia heredada a la que se delegan las mediciones. Inmutable tras construcción. */
    private final SensorLegacy sensorLegacy;

    /**
     * Crea un nuevo adaptador a partir de un sensor legacy.
     *
     * <p>Inicializa los metadatos obligatorios del {@link Sensor} base (id, nombre, ubicación)
     * tomando los datos del sensor heredado. Configura umbrales por defecto auto-documentados.</p>
     *
     * @param sensorLegacy instancia existente del sensor heredado; no debe ser {@code null}
     * @throws NullPointerException si {@code sensorLegacy} es {@code null}
     */
    public SensorAdapter(final SensorLegacy sensorLegacy) {
        // Validación defensiva para evitar NPE en tiempo de ejecución.
        super(
                Objects.requireNonNull(sensorLegacy, "sensorLegacy no puede ser null").getCodigo(),
                "Sensor Legacy Adaptado",
                sensorLegacy.getLugar()
        );
        this.sensorLegacy = sensorLegacy;

        // Configuración de umbrales por defecto (pueden ajustarse externamente si la superclase lo permite).
        this.umbralMinimo = DEFAULT_UMBRAL_MINIMO;
        this.umbralMaximo = DEFAULT_UMBRAL_MAXIMO;
    }

    /**
     * Toma una lectura desde el sensor heredado y la adapta al modelo del {@link Sensor} moderno.
     *
     * <p>Secuencia:</p>
     * <ol>
     *   <li>Solicita al sensor legacy que realice la medición.</li>
     *   <li>Obtiene el dato y lo asigna a {@code valorActual} (campo de la superclase).</li>
     *   <li>Registra la lectura para trazabilidad.</li>
     *   <li>Ejecuta la verificación de umbrales definida en la superclase.</li>
     * </ol>
     *
     * <p><b>Nota de rendimiento:</b> este método asume operación rápida; si el sensor
     * legacy realiza I/O bloqueante, considerar ejecutarlo en un hilo separado o con
     * timeouts en la implementación de {@link SensorLegacy}.</p>
     */
    @Override
    public void tomarLectura() {
        // 1) Delegar medición al sistema heredado.
        sensorLegacy.realizarMedicion();

        // 2) Adaptar el resultado a la API moderna.
        valorActual = sensorLegacy.obtenerDato();

        // 3) Registrar lectura (evita imprimir por consola en producción).
        // Formato: "[ADAPTADO] Sensor <id> [<ubicacion>]: <valor>"
        LOGGER.log(
                Level.INFO,
                () -> String.format("📊 [ADAPTADO] Sensor %s [%s]: %.1f", id, ubicacion, valorActual)
        );

        // 4) Aplicar lógica de negocio común (alertas por umbral, etc.).
        verificarUmbrales();
    }

    /**
     * Devuelve el sensor heredado subyacente.
     *
     * <p><b>Advertencia de encapsulamiento:</b> exponer la dependencia permite accesos
     * directos a la API legacy. Úsese con moderación para evitar dependencias fuertes
     * fuera del adaptador.</p>
     *
     * @return instancia inmutable de {@link SensorLegacy} usada por el adaptador
     */
    public SensorLegacy getSensorLegacy() {
        return sensorLegacy;
    }
}
