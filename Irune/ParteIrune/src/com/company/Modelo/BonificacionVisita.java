package com.company.Modelo;

public class BonificacionVisita {

    private Bonificacion bonificacion;
    private Visita visita;

    public BonificacionVisita() {
    }

    public BonificacionVisita(Bonificacion bonificacion, Visita visita) {
        this.bonificacion = bonificacion;
        this.visita = visita;
    }

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}