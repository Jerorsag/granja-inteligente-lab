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