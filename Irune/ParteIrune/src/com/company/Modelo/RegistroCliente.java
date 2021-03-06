package com.company.Modelo;

import java.sql.Date;
import java.util.Objects;

public class RegistroCliente {

    private int cod;
    private Cliente cliente;
    private Date fecha;
    private String registro;

    public RegistroCliente() {
    }

    public RegistroCliente(int cod, Cliente cliente, Date fecha, String registro) {
        this.cod = cod;
        this.cliente = cliente;
        this.fecha = fecha;
        this.registro = registro;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}