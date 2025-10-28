package com.grupo2.patterns.creational.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Patrón Singleton: Sistema de alimentación global único
 * Gestiona todos los dispensadores de la granja
 */
public class AlimentadorGlobal {
    private static AlimentadorGlobal instancia;
    private double inventarioTotal; // kg de alimento
    private Map<String, Double> dispensadores; // corral -> cantidad dispensada
    private int dispensacionesTotales;

    /**
     * Constructor privado para evitar instanciación externa
     */
    private AlimentadorGlobal() {
        this.inventarioTotal = 10000.0; // 10 toneladas iniciales
        this.dispensadores = new HashMap<>();
        this.dispensacionesTotales = 0;
        System.out.println("✓ Sistema de Alimentación Global inicializado");
    }

    /**
     * Método para obtener la única instancia (Thread-safe)
     */
    public static synchronized AlimentadorGlobal getInstancia() {
        if (instancia == null) {
            instancia = new AlimentadorGlobal();
        }
        return instancia;
    }

    /**
     * Dispensa alimento a un corral específico
     */
    public synchronized boolean dispensarAlimento(String corral, double cantidad) {
        if (cantidad > inventarioTotal) {
            System.out.println("⚠ Inventario insuficiente. Disponible: " + inventarioTotal + " kg");
            return false;
        }

        inventarioTotal -= cantidad;
        dispensadores.put(corral, dispensadores.getOrDefault(corral, 0.0) + cantidad);
        dispensacionesTotales++;

        System.out.println("✓ Dispensados " + cantidad + " kg al corral " + corral);
        System.out.println("  Inventario restante: " + inventarioTotal + " kg");

        return true;
    }

    /**
     * Recarga el inventario de alimento
     */
    public synchronized void recargarInventario(double cantidad) {
        inventarioTotal += cantidad;
        System.out.println("✓ Inventario recargado con " + cantidad + " kg. Total: " + inventarioTotal + " kg");
    }

    /**
     * Obtiene estadísticas del sistema
     */
    public void mostrarEstadisticas() {
        System.out.println("\n=== Estadísticas del Alimentador Global ===");
        System.out.println("Inventario Total: " + inventarioTotal + " kg");
        System.out.println("Dispensaciones Totales: " + dispensacionesTotales);
        System.out.println("Corrales Alimentados: " + dispensadores.size());

        System.out.println("\nDistribución por Corral:");
        dispensadores.forEach((corral, cantidad) ->
                System.out.println("  " + corral + ": " + cantidad + " kg")
        );
    }

    public double getInventarioTotal() {
        return inventarioTotal;
    }

    public int getDispensacionesTotales() {
        return dispensacionesTotales;
    }

    public Map<String, Double> getDispensadores() {
        return new HashMap<>(dispensadores);
    }
}