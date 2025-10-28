package com.grupo2.patterns.behavioral.command;

/**
 * Comando concreto: Encender sistema de riego
 */
public class EncenderRiegoCommand implements Command {
    private String zona;
    private int duracionMinutos;
    private boolean encendido;

    public EncenderRiegoCommand(String zona, int duracionMinutos) {
        this.zona = zona;
        this.duracionMinutos = duracionMinutos;
        this.encendido = false;
    }

    @Override
    public void ejecutar() {
        if (!encendido) {
            System.out.println("💧 Encendiendo riego en zona: " + zona);
            System.out.println("   Duración programada: " + duracionMinutos + " minutos");
            encendido = true;
            System.out.println("✓ Sistema de riego activado");
        } else {
            System.out.println("⚠ El riego ya está encendido en " + zona);
        }
    }

    @Override
    public void deshacer() {
        if (encendido) {
            System.out.println("💧 Apagando riego en zona: " + zona);
            encendido = false;
            System.out.println("✓ Sistema de riego desactivado");
        } else {
            System.out.println("⚠ El riego ya está apagado");
        }
    }

    @Override
    public String getDescripcion() {
        return "Encender riego en " + zona + " por " + duracionMinutos + " minutos";
    }
}