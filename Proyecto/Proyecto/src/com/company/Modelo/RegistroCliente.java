package com.company.Modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroCliente {

    private int cod;
    private Cliente cliente;
    private LocalDateTime fecha;
    private String registro;

    public RegistroCliente() {
    }

    public RegistroCliente(int cod) {
        this.cod = cod;
    }

    public RegistroCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public RegistroCliente(int cod, Cliente cliente, LocalDateTime fecha, String registro) {
        this.cod = cod;
        this.cliente = cliente;
        this.fecha = fecha;
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "RegistroCliente{" +
                "cod=" + cod +
                ", cliente=" + cliente +
                ", fecha=" + fecha +
                ", registro='" + registro + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroCliente that = (RegistroCliente) o;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}