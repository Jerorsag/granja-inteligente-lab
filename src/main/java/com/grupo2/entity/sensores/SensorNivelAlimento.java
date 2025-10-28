package com.grupo2.entity.sensores;

import java.util.Random;

/**
 * Sensor específico para monitorear nivel de alimento
 */
public class SensorNivelAlimento extends Sensor {
    private Random random;
    
    public SensorNivelAlimento(String id, String ubicacion) {
        super(id, "Nivel de Alimento", ubicacion);
        this.random = new Random();
        this.valorActual = 100.0; // Comienza lleno
        this.umbralMinimo = 20.0; // Alerta si baja del 20%
        this.umbralMaximo = 100.0;
    }
    
    @Override
    public void tomarLectura() {
        // Simula consumo gradual con variación
        valorActual -= random.nextDouble() * 15; // Reduce entre 0-15%
        
        if (valorActual < 0) {
            valorActual = 0;
        }
        
        System.out.println("📊 Lectura Sensor " + id + " [" + ubicacion + "]: " + 
                         String.format("%.1f", valorActual) + "%");
        
        verificarUmbrales();
    }
    
    public void rellenar() {
        valorActual = 100.0;
        System.out.println("✓ Contenedor de alimento rellenado en " + ubicacion);
    }
    
    public void setNivel(double nivel) {
        this.valorActual = nivel;
        verificarUmbrales();
    }
}

