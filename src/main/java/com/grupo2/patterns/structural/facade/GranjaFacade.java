package com.grupo2.patterns.structural.facade;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.sensores.SensorHumedad;
import com.grupo2.entity.sensores.SensorNivelAlimento;
import com.grupo2.entity.sensores.SensorTemperatura;
import com.grupo2.patterns.behavioral.command.Command;
import com.grupo2.patterns.behavioral.command.ControladorComandos;
import com.grupo2.patterns.behavioral.command.DispensarAlimentoCommand;
import com.grupo2.patterns.behavioral.command.EncenderRiegoCommand;
import com.grupo2.patterns.behavioral.observer.SistemaAlerta;
import com.grupo2.patterns.behavioral.strategy.EstrategiaAlimentacion;
import com.grupo2.patterns.creational.factory.AnimalFactoryImpl;
import com.grupo2.patterns.creational.singleton.AlimentadorGlobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Patrón Facade: Unifica la comunicación con los subsistemas de la granja
 */
public class GranjaFacade {
    // Subsistemas
    private AlimentadorGlobal alimentador;
    private ControladorComandos controladorComandos;
    private SistemaAlerta sistemaAlerta;
    private Map<String, List<Animal>> corrales;
    private Map<String, SensorNivelAlimento> sensoresAlimento;
    private Map<String, SensorTemperatura> sensoresTemperatura;
    private Map<String, SensorHumedad> sensoresHumedad;
    private EstrategiaAlimentacion estrategiaActual;

    public GranjaFacade() {
        this.alimentador = AlimentadorGlobal.getInstancia();
        this.controladorComandos = new ControladorComandos();
        this.sistemaAlerta = new SistemaAlerta("Sistema Central");
        this.corrales = new HashMap<>();
        this.sensoresAlimento = new HashMap<>();
        this.sensoresTemperatura = new HashMap<>();
        this.sensoresHumedad = new HashMap<>();

        System.out.println("==============================================");
        System.out.println("  🏗️  GRANJA INTELIGENTE INICIALIZADA  🏗️");
        System.out.println("==============================================\n");
    }

    /**
     * Crear un corral completo con sensores
     */
    public void crearCorral(String nombreCorral, String tipo) {
        System.out.println("\n🏠 Creando corral: " + nombreCorral + " (Tipo: " + tipo + ")");

        // Crear lista de animales para el corral
        corrales.put(nombreCorral, new ArrayList<>());

        // Instalar sensores
        SensorNivelAlimento sensorAlimento = new SensorNivelAlimento("SA-" + nombreCorral, nombreCorral);
        sensorAlimento.agregarObservador(sistemaAlerta);
        sensoresAlimento.put(nombreCorral, sensorAlimento);

        SensorTemperatura sensorTemp = new SensorTemperatura("ST-" + nombreCorral, nombreCorral);
        sensorTemp.agregarObservador(sistemaAlerta);
        sensoresTemperatura.put(nombreCorral, sensorTemp);

        SensorHumedad sensorHum = new SensorHumedad("SH-" + nombreCorral, nombreCorral);
        sensorHum.agregarObservador(sistemaAlerta);
        sensoresHumedad.put(nombreCorral, sensorHum);

        System.out.println("✓ Corral creado con todos los sensores instalados");
    }

    /**
     * Agregar animal a un corral
     */
    public Animal agregarAnimal(String corral, String tipoAnimal, String id, String raza, double peso, int edad) {
        if (!corrales.containsKey(corral)) {
            System.out.println("⚠ Error: El corral " + corral + " no existe");
            return null;
        }

        Animal animal = AnimalFactoryImpl.crearAnimal(tipoAnimal, id, raza, peso, edad);
        corrales.get(corral).add(animal);

        System.out.println("✓ Animal " + id + " agregado al corral " + corral);
        return animal;
    }

    /**
     * Alimentar un corral específico (método simplificado de la facade)
     */
    public void alimentarCorral(String corral) {
        if (!corrales.containsKey(corral)) {
            System.out.println("⚠ Error: Corral no encontrado");
            return;
        }

        List<Animal> animales = corrales.get(corral);
        if (animales.isEmpty()) {
            System.out.println("⚠ El corral está vacío");
            return;
        }

        System.out.println("\n🍽️ ALIMENTANDO CORRAL: " + corral);

        // Calcular cantidad total necesaria
        double cantidadTotal = 0;
        for (Animal animal : animales) {
            if (estrategiaActual != null) {
                cantidadTotal += estrategiaActual.calcularCantidadAlimento(animal);
            } else {
                cantidadTotal += animal.getPeso() * 0.025; // Default 2.5%
            }
        }

        // Ejecutar comando de alimentación
        Command comandoAlimentar = new DispensarAlimentoCommand(corral, cantidadTotal);
        controladorComandos.ejecutarComando(comandoAlimentar);

        // Alimentar cada animal
        for (Animal animal : animales) {
            double cantidad = estrategiaActual != null ?
                    estrategiaActual.calcularCantidadAlimento(animal) :
                    animal.getPeso() * 0.025;
            animal.alimentar(cantidad);
        }

        // Actualizar sensor
        if (sensoresAlimento.containsKey(corral)) {
            sensoresAlimento.get(corral).rellenar();
        }
    }

    /**
     * Activar riego en una zona
     */
    public void activarRiego(String zona, int duracion) {
        System.out.println("\n💧 ACTIVANDO SISTEMA DE RIEGO");
        Command comandoRiego = new EncenderRiegoCommand(zona, duracion);
        controladorComandos.ejecutarComando(comandoRiego);
    }

    /**
     * Monitorear todos los sensores de un corral
     */
    public void monitorearCorral(String corral) {
        System.out.println("\n📊 MONITOREANDO CORRAL: " + corral);

        if (sensoresAlimento.containsKey(corral)) {
            sensoresAlimento.get(corral).tomarLectura();
        }

        if (sensoresTemperatura.containsKey(corral)) {
            sensoresTemperatura.get(corral).tomarLectura();
        }

        if (sensoresHumedad.containsKey(corral)) {
            sensoresHumedad.get(corral).tomarLectura();
        }
    }

    /**
     * Cambiar estrategia de alimentación
     */
    public void cambiarEstrategiaAlimentacion(EstrategiaAlimentacion nuevaEstrategia) {
        this.estrategiaActual = nuevaEstrategia;
        System.out.println("\n🔄 Estrategia de alimentación cambiada:");
        System.out.println("   " + nuevaEstrategia.obtenerDescripcion());
        System.out.println("   Frecuencia: " + nuevaEstrategia.calcularFrecuencia() + " veces/día");
    }

    /**
     * Mostrar estado completo de la granja
     */
    public void mostrarEstadoGranja() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     ESTADO GENERAL DE LA GRANJA          ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n📍 CORRALES:");
        for (String corral : corrales.keySet()) {
            List<Animal> animales = corrales.get(corral);
            System.out.println("  • " + corral + ": " + animales.size() + " animales");
        }

        alimentador.mostrarEstadisticas();

        if (estrategiaActual != null) {
            System.out.println("\n🔄 Estrategia Actual:");
            System.out.println("   " + estrategiaActual.obtenerDescripcion());
        }

        controladorComandos.mostrarHistorial();
        sistemaAlerta.mostrarHistorialAlertas();
    }

    /**
     * Obtener animales de un corral
     */
    public List<Animal> getAnimalesCorral(String corral) {
        return corrales.getOrDefault(corral, new ArrayList<>());
    }

    /**
     * Obtener sensor de alimento de un corral
     */
    public SensorNivelAlimento getSensorAlimento(String corral) {
        return sensoresAlimento.get(corral);
    }

    public ControladorComandos getControladorComandos() {
        return controladorComandos;
    }

    public SistemaAlerta getSistemaAlerta() {
        return sistemaAlerta;
    }
}