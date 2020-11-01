package com.company.Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    public Visita(int cod) {
        this.cod = cod;
    }

    public Visita(Empleado guia) {
        this.guia = guia;
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

    public void cambiarDatosDB4O(Visita vis) {
        this.guia = vis.getGuia();
        this.nombre = vis.getNombre();
        this.numMaxClientes = vis.getNumMaxClientes();
        this.puntoPartida = vis.getPuntoPartida();
        this.fecha = vis.getFecha();
        this.anyo = vis.getAnyo();
        this.duracionEstimada = vis.getDuracionEstimada();
        this.tematica = vis.getTematica();
        this.coste = vis.getCoste();
    }

    @Override //TODO habrá que dejar bien lindo el toString para que se muestre elegante en los JList
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaFormateada = fecha.format(formatter);
        return String.format("%3d | %s | %s", cod, fechaFormateada, tematica);
        /*
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
        }*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return cod == visita.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
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