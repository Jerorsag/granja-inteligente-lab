package com.grupo2.patterns.behavioral.command;

import com.grupo2.patterns.creational.singleton.AlimentadorGlobal;

/**
 * Comando concreto: Dispensar alimento
 */
public class DispensarAlimentoCommand implements Command {
    private AlimentadorGlobal alimentador;
    private String corral;
    private double cantidad;
    private boolean ejecutado;

    public DispensarAlimentoCommand(String corral, double cantidad) {
        this.alimentador = AlimentadorGlobal.getInstancia();
        this.corral = corral;
        this.cantidad = cantidad;
        this.ejecutado = false;
    }

    @Override
    public void ejecutar() {
        if (!ejecutado) {
            boolean exito = alimentador.dispensarAlimento(corral, cantidad);
            if (exito) {
                ejecutado = true;
                System.out.println("✓ Comando ejecutado: Alimento dispensado al corral " + corral);
            } else {
                System.out.println("✗ Error: No se pudo dispensar alimento");
            }
        } else {
            System.out.println("⚠ Comando ya ejecutado");
        }
    }

    @Override
    public void deshacer() {
        if (ejecutado) {
            alimentador.recargarInventario(cantidad);
            ejecutado = false;
            System.out.println("↶ Comando deshecho: Alimento devuelto al inventario");
        } else {
            System.out.println("⚠ No hay nada que deshacer");
        }
    }

    @Override
    public String getDescripcion() {
        return "Dispensar " + cantidad + " kg de alimento al corral " + corral;
    }
}