package com.grupo2.patterns.structural.facade;

import com.grupo2.entity.animales.Animal;
import com.grupo2.entity.sensores.SensorHumedad;
import com.grupo2.entity.sensores.SensorNivelAlimento;
import com.grupo2.entity.sensores.SensorTemperatura;
import com.grupo2.patterns.behavioral.command.ControladorComandos;
import com.grupo2.patterns.behavioral.observer.SistemaAlerta;
import com.grupo2.patterns.behavioral.strategy.EstrategiaAlimentacion;
import com.grupo2.patterns.creational.singleton.AlimentadorGlobal;

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
}