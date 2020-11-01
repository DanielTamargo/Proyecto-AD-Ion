package com.company.modelo;

import java.util.Objects;

public class Bonificacion {

    private int cod;
    private Cliente cliente;
    private String nombre;
    private String descripcion;

    public Bonificacion() {
    }

    public Bonificacion(int cod, Cliente cliente, String nombre, String descripcion) {
        this.cod = cod;
        this.cliente = cliente;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Bonificacion{" +
                "cod=" + cod +
                ", cliente=" + cliente +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonificacion that = (Bonificacion) o;
        return cod == that.cod;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}