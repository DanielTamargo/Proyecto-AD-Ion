package com.company.modelo;

import java.util.Objects;

public class BonificacionVisita {

    private Bonificacion bonificacion;
    private Visita visita;

    public BonificacionVisita() {
    }

    public BonificacionVisita(Bonificacion bonificacion, Visita visita) {
        this.bonificacion = bonificacion;
        this.visita = visita;
    }

    @Override
    public String toString() {
        return "BonificacionVisita{" +
                "bonificacion=" + bonificacion +
                ", visita=" + visita +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonificacionVisita that = (BonificacionVisita) o;
        return bonificacion.equals(that.bonificacion) &&
                visita.equals(that.visita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonificacion, visita);
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