package com.grupo2;

import com.grupo2.patterns.creational.singleton.AlimentadorGlobal;

public class Main {
    public static void main(String[] args) {
        System.out.println("¡Bienvenido a la granja inteligente!");

        AlimentadorGlobal a1 = AlimentadorGlobal.getInstancia();
        AlimentadorGlobal a2 = AlimentadorGlobal.getInstancia();

        a1.dispensarAlimento("cerdo", 1200);
        a1.recargarInventario(3000);
        a1.mostrarEstadisticas();

        System.out.println(a1 == a2);
    }
}