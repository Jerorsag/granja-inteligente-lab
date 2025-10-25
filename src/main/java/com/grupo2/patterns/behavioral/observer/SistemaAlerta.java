package com.grupo2.patterns.behavioral.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Observer: Observador concreto - Sistema de Alertas
 * Recibe notificaciones de los sensores
 */
public class SistemaAlerta implements Observer {
    private String nombre;
    private List<String> alertas;
    private DateTimeFormatter formatter;
    
    public SistemaAlerta(String nombre) {
        this.nombre = nombre;
        this.alertas = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    @Override
    public void actualizar(Subject sujeto, String mensaje) {
        String timestamp = LocalDateTime.now().format(formatter);
        String alerta = "[" + timestamp + "] " + nombre + " - " + mensaje;
        alertas.add(alerta);
        
        System.out.println("🚨 ALERTA: " + alerta);
        
        // Si es crítico, tomar acción
        if (mensaje.toLowerCase().contains("crítico") || 
            mensaje.toLowerCase().contains("bajo") ||
            mensaje.toLowerCase().contains("alto")) {
            System.out.println("   ⚡ Acción requerida inmediatamente");
        }
    }
    
    public void mostrarHistorialAlertas() {
        System.out.println("\n=== Historial de Alertas: " + nombre + " ===");
        if (alertas.isEmpty()) {
            System.out.println("No hay alertas registradas");
        } else {
            for (int i = 0; i < alertas.size(); i++) {
                System.out.println((i + 1) + ". " + alertas.get(i));
            }
        }
    }
    
    public List<String> getAlertas() {
        return new ArrayList<>(alertas);
    }
    
    public String getNombre() {
        return nombre;
    }
}
