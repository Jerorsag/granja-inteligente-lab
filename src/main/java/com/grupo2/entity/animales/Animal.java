package com.grupo2.entity.animales;

import com.grupo2.patterns.behavioral.state.Estado;
import com.grupo2.patterns.behavioral.state.EstadoSano;

public class Animal {
    private String id;
    private String tipo;
    private String raza;
    private double peso;
    private int edad;
    private String historialMedico;
    private int nivelActividad;
    private String proposito; // leche, carne, huevos
    private Estado estadoSalud;

    public Animal() {
        this.estadoSalud = new EstadoSano();
        this.historialMedico = "";
    }

    public Animal(String id, String tipo, String raza, double peso, int edad, String proposito) {
        this.id = id;
        this.tipo = tipo;
        this.raza = raza;
        this.peso = peso;
        this.edad = edad;
        this.proposito = proposito;
        this.estadoSalud = new EstadoSano();
        this.historialMedico = "";
        this.nivelActividad = 100;
    }

    // Método para alimentar
    public void alimentar(double cantidad) {
        estadoSalud.alimentar(this, cantidad);
    }

    // Método para cambiar estado de salud
    public void cambiarEstado(Estado nuevoEstado) {
        this.estadoSalud = nuevoEstado;
        System.out.println("Estado del animal " + id + " cambiado a: " + nuevoEstado.getClass().getSimpleName());
    }

    public void mostrarInfo() {
        System.out.println("=== Información del Animal ===");
        System.out.println("ID: " + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Raza: " + raza);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Edad: " + edad + " meses");
        System.out.println("Propósito: " + proposito);
        System.out.println("Nivel de Actividad: " + nivelActividad + "%");
        System.out.println("Estado de Salud: " + estadoSalud.getClass().getSimpleName());
        System.out.println("Historial Médico: " + (historialMedico.isEmpty() ? "Sin registros" : historialMedico));
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public int getNivelActividad() {
        return nivelActividad;
    }

    public void setNivelActividad(int nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public Estado getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(Estado estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public void agregarHistorialMedico(String registro) {
        this.historialMedico += "\n" + registro;
    }
}
