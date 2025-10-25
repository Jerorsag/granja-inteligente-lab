package com.grupo2.entity.sensores;

import java.util.Random;

/**
 * Sensor para monitorear temperatura
 */
public class SensorTemperatura extends Sensor {
    private Random random;
    
    public SensorTemperatura(String id, String ubicacion) {
        super(id, "Temperatura", ubicacion);
        this.random = new Random();
        this.valorActual = 22.0; // Temperatura ambiente inicial
        this.umbralMinimo = 15.0; // °C
        this.umbralMaximo = 28.0; // °C
    }
    
    @Override
    public void tomarLectura() {
        // Simula variación de temperatura
        valorActual += (random.nextDouble() - 0.5) * 3; // ±1.5°C
        
        System.out.println("📊 Lectura Sensor " + id + " [" + ubicacion + "]: " + 
                         String.format("%.1f", valorActual) + "°C");
        
        verificarUmbrales();
    }
}
