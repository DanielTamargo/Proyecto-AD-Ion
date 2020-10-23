package com.company.Modelo;

import java.time.LocalDateTime;

public class Visita {

    private int cod;
    private Empleado guia;
    private String nombre;
    private int numMaxClientes;
    private String puntoPartida;
    private LocalDateTime fecha;
    private int anyo;
    private float duracionEstimada;
    private String tematica;
    private float coste;

    public Visita() {
    }

    public Visita(Empleado guia, String nombre, int numMaxClientes, String puntoPartida, LocalDateTime fecha,
                  int anyo, float duracionEstimada, String tematica, float coste) {
        this.guia = guia;
        this.nombre = nombre;
        this.numMaxClientes = numMaxClientes;
        this.puntoPartida = puntoPartida;
        this.fecha = fecha;
        this.anyo = anyo;
        this.duracionEstimada = duracionEstimada;
        this.tematica = tematica;
        this.coste = coste;
    }

    public Visita(int cod, Empleado guia, String nombre, int numMaxClientes, String puntoPartida, LocalDateTime fecha,
                  int anyo, float duracionEstimada, String tematica, float coste) {
        this.cod = cod;
        this.guia = guia;
        this.nombre = nombre;
        this.numMaxClientes = numMaxClientes;
        this.puntoPartida = puntoPartida;
        this.fecha = fecha;
        this.anyo = anyo;
        this.duracionEstimada = duracionEstimada;
        this.tematica = tematica;
        this.coste = coste;
    }

    @Override //TODO habrá que dejar bien lindo el toString para que se muestre elegante en los JList
    public String toString() {
        if (guia != null) {
            return "Visita{" +
                    "cod=" + cod +
                    ", guia=" + guia.getDni() + "(" + guia.getNombre() + ")" +
                    ", nombre='" + nombre + '\'' +
                    ", numMaxClientes=" + numMaxClientes +
                    ", puntoPartida='" + puntoPartida + '\'' +
                    ", fecha=" + fecha +
                    ", anyo=" + anyo +
                    ", duracionEstimada=" + duracionEstimada +
                    ", tematica='" + tematica + '\'' +
                    ", coste=" + coste +
                    '}';
        } else {
            return "Visita{" +
                    "cod=" + cod +
                    ", guia=null" +
                    ", nombre='" + nombre + '\'' +
                    ", numMaxClientes=" + numMaxClientes +
                    ", puntoPartida='" + puntoPartida + '\'' +
                    ", fecha=" + fecha +
                    ", anyo=" + anyo +
                    ", duracionEstimada=" + duracionEstimada +
                    ", tematica='" + tematica + '\'' +
                    ", coste=" + coste +
                    '}';
        }
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Empleado getGuia() { return guia; }

    public void setGuia(Empleado guia) { this.guia = guia; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumMaxClientes() {
        return numMaxClientes;
    }

    public void setNumMaxClientes(int numMaxClientes) {
        this.numMaxClientes = numMaxClientes;
    }

    public String getPuntoPartida() {
        return puntoPartida;
    }

    public void setPuntoPartida(String puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public float getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(float duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }
}