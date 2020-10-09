package com.tamargo.Modelo;
import java.sql.Date;

public class Visita {

    private int cod;
    private Empleado guia;
    private String nombre;
    private int numMaxClientes;
    private String puntoPartida;
    private Date fecha;
    private int anyo;
    private float duracionEstimada;
    private String tematica;
    private float coste;

    public Visita() {
    }

    public Visita(int cod, Empleado guia, String nombre, int numMaxClientes, String puntoPartida, Date fecha,
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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