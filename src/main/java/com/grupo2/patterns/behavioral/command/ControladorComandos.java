package com.grupo2.patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Invoker del patrón Command: Ejecuta y gestiona comandos
 */
public class ControladorComandos {
    private List<Command> historialComandos;
    private List<Command> comandosPendientes;

    public ControladorComandos() {
        this.historialComandos = new ArrayList<>();
        this.comandosPendientes = new ArrayList<>();
    }

    /**
     * Ejecuta un comando inmediatamente
     */
    public void ejecutarComando(Command comando) {
        System.out.println("\n▶ Ejecutando: " + comando.getDescripcion());
        comando.ejecutar();
        historialComandos.add(comando);
    }

    /**
     * Programa un comando para ejecución posterior
     */
    public void programarComando(Command comando) {
        comandosPendientes.add(comando);
        System.out.println("⏰ Comando programado: " + comando.getDescripcion());
    }

    /**
     * Ejecuta todos los comandos pendientes
     */
    public void ejecutarComandosPendientes() {
        System.out.println("\n▶▶ Ejecutando " + comandosPendientes.size() + " comandos pendientes...");
        for (Command comando : comandosPendientes) {
            ejecutarComando(comando);
        }
        comandosPendientes.clear();
    }

    /**
     * Deshace el último comando ejecutado
     */
    public void deshacerUltimo() {
        if (!historialComandos.isEmpty()) {
            Command ultimo = historialComandos.remove(historialComandos.size() - 1);
            System.out.println("\n↶ Deshaciendo: " + ultimo.getDescripcion());
            ultimo.deshacer();
        } else {
            System.out.println("⚠ No hay comandos para deshacer");
        }
    }

    /**
     * Muestra el historial de comandos
     */
    public void mostrarHistorial() {
        System.out.println("\n=== Historial de Comandos ===");
        if (historialComandos.isEmpty()) {
            System.out.println("No hay comandos ejecutados");
        } else {
            for (int i = 0; i < historialComandos.size(); i++) {
                System.out.println((i + 1) + ". " + historialComandos.get(i).getDescripcion());
            }
        }
    }
}