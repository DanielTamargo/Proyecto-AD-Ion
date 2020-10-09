package com.tamargo.Modelo;

public class VisitaCliente {

    private Cliente cliente;
    private Visita visita;

    public VisitaCliente() {
    }

    public VisitaCliente(Cliente cliente, Visita visita) {
        this.cliente = cliente;
        this.visita = visita;
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