package com.company.Modelo;

import java.util.Objects;

public class VisitaCliente {

    private Cliente cliente;
    private Visita visita;

    public VisitaCliente() {
    }

    public VisitaCliente(Visita visita) {
        this.visita = visita;
    }

    public VisitaCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VisitaCliente(Cliente cliente, Visita visita) {
        this.cliente = cliente;
        this.visita = visita;
    }

    public void cambiarDatosDB4O (VisitaCliente visCli) {
        this.visita = visCli.getVisita();
        this.cliente = visCli.getCliente();
    }

    @Override
    public String toString() {
        return "VisitaCliente{" +
                "cliente=" + cliente +
                ", visita=" + visita +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitaCliente that = (VisitaCliente) o;
        return cliente.equals(that.cliente) &&
                visita.equals(that.visita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, visita);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}