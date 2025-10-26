package com.grupo2.entity.sensores;

import java.util.Random;

/**
 * Sensor para monitorear humedad
 */
public class SensorHumedad extends Sensor {
    private Random random;
    
    public SensorHumedad(String id, String ubicacion) {
        super(id, "Humedad", ubicacion);
        this.random = new Random();
        this.valorActual = 60.0; // Humedad inicial 60%
        this.umbralMinimo = 40.0; // %
        this.umbralMaximo = 80.0; // %
    }
    
    @Override
    public void tomarLectura() {
        // Simula variación de humedad
        valorActual += (random.nextDouble() - 0.5) * 10; // ±5%
        
        if (valorActual < 0) valorActual = 0;
        if (valorActual > 100) valorActual = 100;
        
        System.out.println("📊 Lectura Sensor " + id + " [" + ubicacion + "]: " + 
                         String.format("%.1f", valorActual) + "%");
        
        verificarUmbrales();
    }
}
