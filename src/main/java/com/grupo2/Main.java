package com.grupo2;

import com.grupo2.entity.animales.Animal;
import com.grupo2.patterns.creational.builder.AnimalBuilder;
import com.grupo2.patterns.creational.builder.AnimalDirector;
import com.grupo2.patterns.creational.factory.AbstractAnimalFactory;
import com.grupo2.patterns.creational.factory.VacaLecheraFactory;
import com.grupo2.patterns.creational.singleton.AlimentadorGlobal;
import com.grupo2.patterns.structural.facade.GranjaFacade;

/**
 * SISTEMA DE GRANJA INTELIGENTE
 * Implementación de todos los patrones de diseño solicitados
 *
 * PATRONES IMPLEMENTADOS:
 *
 * CREACIONALES:
 * - Factory Method: Creación dinámica de animales
 * - Abstract Factory: Familias de productos (animales + alimento + entorno)
 * - Builder: Construcción paso a paso de animales
 * - Singleton: Sistema de alimentación global único
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                                                       ║");
        System.out.println("║   🌾 SISTEMA DE GRANJA INTELIGENTE 🌾                ║");
        System.out.println("║   Demostración de Patrones de Diseño                 ║");
        System.out.println("║                                                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // FASE 1: INICIALIZACIÓN CON FACADE
        System.out.println("\n█████ FASE 1: INICIALIZACIÓN DEL SISTEMA █████\n");
        GranjaFacade granja = new GranjaFacade();

        // Crear corrales usando la facade
        granja.crearCorral("Corral-Cerdos", "carne");
        granja.crearCorral("Corral-Vacas", "lechero");
        granja.crearCorral("Corral-Gallinas", "avicola");

        // FASE 2: PATRONES CREACIONALES
        System.out.println("\n\n█████ FASE 2: PATRONES CREACIONALES █████\n");

        // 2.1 FACTORY METHOD
        System.out.println("--- 2.1 Factory Method: Creación dinámica de animales ---");
        Animal cerdo1 = granja.agregarAnimal("Corral-Cerdos", "cerdo", "C001", "Duroc", 120, 6);
        Animal cerdo2 = granja.agregarAnimal("Corral-Cerdos", "cerdo", "C002", "Landrace", 115, 5);

        Animal vaca1 = granja.agregarAnimal("Corral-Vacas", "vaca", "V001", "Holstein", 600, 24);
        Animal vaca2 = granja.agregarAnimal("Corral-Vacas", "vaca", "V002", "Jersey", 450, 18);

        Animal gallina1 = granja.agregarAnimal("Corral-Gallinas", "gallina", "G001", "Leghorn", 2.0, 5);
        Animal gallina2 = granja.agregarAnimal("Corral-Gallinas", "gallina", "G002", "Rhode Island", 2.5, 6);

        // 2.2 ABSTRACT FACTORY
        System.out.println("\n--- 2.2 Abstract Factory: Familias de productos ---");
        AbstractAnimalFactory vacaLecheraFactory = new VacaLecheraFactory();
        Animal vacaLechera = vacaLecheraFactory.crearAnimal("V003", 580, 20);
        System.out.println("✓ Familia Vaca Lechera creada:");
        System.out.println("  - Animal: " + vacaLechera.getTipo() + " " + vacaLechera.getRaza());
        System.out.println("  - Alimento: " + vacaLecheraFactory.crearAlimento());
        System.out.println("  - Entorno: " + vacaLecheraFactory.crearEntorno());

        // 2.3 BUILDER
        System.out.println("\n--- 2.3 Builder: Construcción paso a paso ---");
        AnimalBuilder builder = new AnimalBuilder();
        AnimalDirector director = new AnimalDirector(builder);

        Animal cerdoBuilder = director.construirCerdoEngorde("C003");
        System.out.println("✓ Cerdo construido con Builder:");
        cerdoBuilder.mostrarInfo();

        // 2.4 SINGLETON
        System.out.println("\n--- 2.4 Singleton: Sistema de alimentación único ---");
        System.out.println("✓ AlimentadorGlobal es único en toda la aplicación");
    }
}