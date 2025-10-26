package com.grupo2.patterns.structural.facade;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.sensores.SensorNivelAlimento;
import com.grupo2.entity.sensores.SensorTemperatura;
import com.grupo2.entity.sensores.SensorHumedad;
import com.grupo2.patterns.behavioral.command.*;
import com.grupo2.patterns.behavioral.observer.SistemaAlerta;
import com.grupo2.patterns.behavioral.strategy.EstrategiaAlimentacion;
import com.grupo2.patterns.creational.singleton.AlimentadorGlobal;
import com.grupo2.patterns.creational.factory.AnimalFactoryImpl;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <h1>GranjaFacade</h1>
 * <p>
 * Implementación del patrón estructural <b>Facade</b> que unifica y simplifica la interacción
 * con los subsistemas de la granja (alimentación, sensores, estrategias, comandos y alertas).
 * Ofrece una API de alto nivel para operaciones frecuentes sin exponer la complejidad interna.
 * </p>
 *
 * <h2>Subsistemas orquestados</h2>
 * <ul>
 *   <li>{@link AlimentadorGlobal}: singleton con estadísticas y operaciones de alimentación.</li>
 *   <li>{@link ControladorComandos}: invocador del patrón Command para acciones en la granja.</li>
 *   <li>{@link SistemaAlerta}: observador central para eventos de sensores.</li>
 *   <li>Sensores: {@link SensorNivelAlimento}, {@link SensorTemperatura}, {@link SensorHumedad}.</li>
 *   <li>Fabricación de animales: {@link AnimalFactoryImpl}.</li>
 *   <li>Estrategia de alimentación: {@link EstrategiaAlimentacion} (Strategy).</li>
 * </ul>
 *
 * <p><b>Autor:</b> Sebastian Ordoñez</p>
 * <p><b>Versión:</b> 1.0</p>
 * <p><b>Patrones:</b> Facade, Command, Observer, Strategy, Singleton, Factory</p>
 */
public class GranjaFacade {

    /** Logger de clase (reemplaza uso de System.out). */
    private static final Logger LOGGER = Logger.getLogger(GranjaFacade.class.getName());

    /** Porcentaje por defecto del peso del animal para calcular alimento (2.5%). */
    private static final double DEFAULT_RATIO_ALIMENTO = 0.025;

    // =========================
    //  Subsistemas y estado
    // =========================
    private final AlimentadorGlobal alimentador;
    private final ControladorComandos controladorComandos;
    private final SistemaAlerta sistemaAlerta;

    /** Mapa: nombre del corral -> lista de animales. */
    private final Map<String, List<Animal>> corrales;

    /** Sensores por corral. */
    private final Map<String, SensorNivelAlimento> sensoresAlimento;
    private final Map<String, SensorTemperatura> sensoresTemperatura;
    private final Map<String, SensorHumedad> sensoresHumedad;

    /** Estrategia de alimentación activa. Puede ser nula (default). */
    private EstrategiaAlimentacion estrategiaActual;

    /**
     * Crea una fachada de granja con todos los subsistemas inicializados.
     * Registra el {@link SistemaAlerta} como observador de los sensores que se creen posteriormente.
     */
    public GranjaFacade() {
        this.alimentador = AlimentadorGlobal.getInstancia();
        this.controladorComandos = new ControladorComandos();
        this.sistemaAlerta = new SistemaAlerta("Sistema Central");
        this.corrales = new HashMap<>();
        this.sensoresAlimento = new HashMap<>();
        this.sensoresTemperatura = new HashMap<>();
        this.sensoresHumedad = new HashMap<>();

        LOGGER.log(Level.INFO, "🏗️  Granja inteligente inicializada (Facade activa).");
    }

    // ------------------------------------------------------------------------------------
    // Creación/gestión de corrales y sensores
    // ------------------------------------------------------------------------------------

    /**
     * Crea un corral con su set de sensores y lo registra en la fachada.
     *
     * @param nombreCorral nombre único del corral
     * @param tipo         texto descriptivo del tipo (solo informativo)
     * @throws IllegalArgumentException si el nombre es nulo/vacío o el corral ya existe
     */
    public void crearCorral(final String nombreCorral, final String tipo) {
        validarNombreCorral(nombreCorral);

        if (corrales.containsKey(nombreCorral)) {
            throw new IllegalArgumentException("El corral '" + nombreCorral + "' ya existe.");
        }

        LOGGER.log(Level.INFO, () -> String.format("🏠 Creando corral: %s (Tipo: %s)", nombreCorral, tipo));

        // Lista de animales para el corral
        corrales.put(nombreCorral, new ArrayList<>());

        // Instalar sensores y suscribir al sistema de alertas
        final SensorNivelAlimento sensorAlimento =
                new SensorNivelAlimento("SA-" + nombreCorral, nombreCorral);
        sensorAlimento.agregarObservador(sistemaAlerta);
        sensoresAlimento.put(nombreCorral, sensorAlimento);

        final SensorTemperatura sensorTemp =
                new SensorTemperatura("ST-" + nombreCorral, nombreCorral);
        sensorTemp.agregarObservador(sistemaAlerta);
        sensoresTemperatura.put(nombreCorral, sensorTemp);

        final SensorHumedad sensorHum =
                new SensorHumedad("SH-" + nombreCorral, nombreCorral);
        sensorHum.agregarObservador(sistemaAlerta);
        sensoresHumedad.put(nombreCorral, sensorHum);

        LOGGER.log(Level.INFO, "✓ Corral creado con sensores instalados: {0}", nombreCorral);
    }

    /**
     * Agrega un animal a un corral existente.
     *
     * @param corral     nombre del corral de destino
     * @param tipoAnimal tipo para la {@link AnimalFactoryImpl} (p.ej., "vaca", "cerdo")
     * @param id         identificador del animal
     * @param raza       raza del animal
     * @param peso       peso del animal (kg)
     * @param edad       edad (meses/años según modelo)
     * @return el animal creado y agregado
     * @throws IllegalArgumentException si el corral no existe o los datos son inválidos
     */
    public Animal agregarAnimal(final String corral,
                                final String tipoAnimal,
                                final String id,
                                final String raza,
                                final double peso,
                                final int edad) {
        validarNombreCorral(corral);
        if (!corrales.containsKey(corral)) {
            throw new IllegalArgumentException("El corral '" + corral + "' no existe.");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id del animal no puede ser nulo o vacío.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso del animal debe ser positivo.");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("La edad del animal no puede ser negativa.");
        }

        final Animal animal = AnimalFactoryImpl.crearAnimal(tipoAnimal, id, raza, peso, edad);
        corrales.get(corral).add(animal);

        LOGGER.log(Level.INFO, "✓ Animal {0} agregado al corral {1}", new Object[]{id, corral});
        return animal;
    }

    // ------------------------------------------------------------------------------------
    // Operaciones de alto nivel (alimentación, riego, monitoreo)
    // ------------------------------------------------------------------------------------

    /**
     * Alimenta todos los animales de un corral aplicando la estrategia actual (si existe),
     * o el ratio por defecto si no se ha configurado una estrategia.
     *
     * @param corral nombre del corral a alimentar
     * @throws IllegalArgumentException si el corral no existe o está vacío
     */
    public void alimentarCorral(final String corral) {
        validarNombreCorral(corral);

        if (!corrales.containsKey(corral)) {
            throw new IllegalArgumentException("Corral no encontrado: " + corral);
        }

        final List<Animal> animales = corrales.get(corral);
        if (animales.isEmpty()) {
            throw new IllegalArgumentException("El corral '" + corral + "' no tiene animales.");
        }

        LOGGER.log(Level.INFO, "🍽️  Alimentando corral: {0}", corral);

        // Calcular cantidad total necesaria
        double cantidadTotal = 0.0;
        for (Animal animal : animales) {
            cantidadTotal += (estrategiaActual != null)
                    ? estrategiaActual.calcularCantidadAlimento(animal)
                    : animal.getPeso() * DEFAULT_RATIO_ALIMENTO;
        }

        // Ejecutar comando de alimentación
        final Command comandoAlimentar = new DispensarAlimentoCommand(corral, cantidadTotal);
        controladorComandos.ejecutarComando(comandoAlimentar);

        // Alimentar cada animal (lógica por individuo)
        for (Animal animal : animales) {
            final double cantidad = (estrategiaActual != null)
                    ? estrategiaActual.calcularCantidadAlimento(animal)
                    : animal.getPeso() * DEFAULT_RATIO_ALIMENTO;
            animal.alimentar(cantidad);
        }

        // Actualizar sensor de nivel de alimento del corral
        final SensorNivelAlimento sensor = sensoresAlimento.get(corral);
        if (sensor != null) {
            sensor.rellenar();
        }
    }

    /**
     * Activa el sistema de riego para una zona y duración concretas.
     *
     * @param zona     identificador de la zona a regar
     * @param duracion duración en minutos (o la unidad que el comando defina)
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public void activarRiego(final String zona, final int duracion) {
        if (zona == null || zona.isBlank()) {
            throw new IllegalArgumentException("La zona de riego no puede ser nula o vacía.");
        }
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración del riego debe ser positiva.");
        }

        LOGGER.log(Level.INFO, "💧 Activando sistema de riego. Zona: {0}, Duración: {1}", new Object[]{zona, duracion});
        final Command comandoRiego = new EncenderRiegoCommand(zona, duracion);
        controladorComandos.ejecutarComando(comandoRiego);
    }

    /**
     * Solicita una lectura de todos los sensores instalados en el corral.
     *
     * @param corral nombre del corral a monitorear
     * @throws IllegalArgumentException si el corral no existe
     */
    public void monitorearCorral(final String corral) {
        validarNombreCorral(corral);
        if (!corrales.containsKey(corral)) {
            throw new IllegalArgumentException("Corral no encontrado: " + corral);
        }

        LOGGER.log(Level.INFO, "📊 Monitoreando corral: {0}", corral);

        final SensorNivelAlimento sAlimento = sensoresAlimento.get(corral);
        if (sAlimento != null) sAlimento.tomarLectura();

        final SensorTemperatura sTemp = sensoresTemperatura.get(corral);
        if (sTemp != null) sTemp.tomarLectura();

        final SensorHumedad sHum = sensoresHumedad.get(corral);
        if (sHum != null) sHum.tomarLectura();
    }

    /**
     * Cambia la estrategia de alimentación activa.
     *
     * @param nuevaEstrategia implementación de {@link EstrategiaAlimentacion}; puede ser {@code null}
     *                        para volver al comportamiento por defecto.
     */
    public void cambiarEstrategiaAlimentacion(final EstrategiaAlimentacion nuevaEstrategia) {
        this.estrategiaActual = nuevaEstrategia;

        if (nuevaEstrategia == null) {
            LOGGER.log(Level.INFO, "🔄 Estrategia de alimentación desactivada. Se usará el ratio por defecto ({0}%).",
                    DEFAULT_RATIO_ALIMENTO * 100);
            return;
        }

        LOGGER.log(Level.INFO, "🔄 Estrategia de alimentación cambiada:");
        LOGGER.log(Level.INFO, "   {0}", nuevaEstrategia.obtenerDescripcion());
        LOGGER.log(Level.INFO, "   Frecuencia: {0} veces/día", nuevaEstrategia.calcularFrecuencia());
    }

    /**
     * Muestra un resumen del estado general de la granja.
     * <p>Genera una salida legible con corrales, conteos, estrategia activa y
     * el historial del invocador de comandos y del sistema de alertas.</p>
     */
    public void mostrarEstadoGranja() {
        final String titulo = "ESTADO GENERAL DE LA GRANJA";
        final String marco = "═".repeat(titulo.length() + 10);

        LOGGER.log(Level.INFO, "\n╔{0}╗", marco);
        LOGGER.log(Level.INFO, "║     {0}     ║", titulo);
        LOGGER.log(Level.INFO, "╚{0}╝", marco);

        // Corrales
        LOGGER.log(Level.INFO, "\n📍 CORRALES:");
        final String listadoCorrales = corrales.entrySet().stream()
                .map(e -> String.format("  • %s: %d animales", e.getKey(), e.getValue().size()))
                .collect(Collectors.joining("\n"));
        LOGGER.log(Level.INFO, listadoCorrales.isBlank() ? "  (sin corrales)" : listadoCorrales);

        // Alimentador
        alimentador.mostrarEstadisticas();

        // Estrategia actual
        if (estrategiaActual != null) {
            LOGGER.log(Level.INFO, "\n🔄 Estrategia Actual:");
            LOGGER.log(Level.INFO, "   {0}", estrategiaActual.obtenerDescripcion());
        } else {
            LOGGER.log(Level.INFO, "\n🔄 Estrategia Actual: (por defecto {0}%)",
                    DEFAULT_RATIO_ALIMENTO * 100);
        }

        // Historial de comandos y alertas
        controladorComandos.mostrarHistorial();
        sistemaAlerta.mostrarHistorialAlertas();
    }

    // ------------------------------------------------------------------------------------
    // Consultas (exponen vistas de solo lectura)
    // ------------------------------------------------------------------------------------

    /**
     * Devuelve una vista inmodificable de los animales en el corral indicado.
     *
     * @param corral nombre del corral
     * @return lista inmodificable de animales; lista vacía si el corral no existe
     */
    public List<Animal> getAnimalesCorral(final String corral) {
        final List<Animal> lista = corrales.get(corral);
        return (lista == null) ? List.of() : Collections.unmodifiableList(lista);
    }

    /**
     * Devuelve el sensor de nivel de alimento asociado a un corral (si existe).
     *
     * @param corral nombre del corral
     * @return {@link Optional} con el sensor si está instalado
     */
    public Optional<SensorNivelAlimento> getSensorAlimento(final String corral) {
        return Optional.ofNullable(sensoresAlimento.get(corral));
    }

    /** @return el invocador de comandos para inspección/diagnóstico. */
    public ControladorComandos getControladorComandos() {
        return controladorComandos;
    }

    /** @return el sistema central de alertas. */
    public SistemaAlerta getSistemaAlerta() {
        return sistemaAlerta;
    }

    // ------------------------------------------------------------------------------------
    // Utilidades privadas
    // ------------------------------------------------------------------------------------

    private void validarNombreCorral(final String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del corral no puede ser nulo o vacío.");
        }
    }
}
