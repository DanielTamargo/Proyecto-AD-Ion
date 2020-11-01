package com.company.Modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroEmpleado {

    private int cod;
    private Empleado empleado;
    private LocalDateTime fecha;
    private String registro;

    public RegistroEmpleado() {
    }

    public RegistroEmpleado(int cod) {
        this.cod = cod;
    }

    public RegistroEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public RegistroEmpleado(int cod, Empleado empleado, String registro) {
        this.cod = cod;
        this.empleado = empleado;
        this.registro = registro;
        this.fecha = LocalDateTime.now();
    }

    public RegistroEmpleado(int cod, Empleado empleado, LocalDateTime fecha, String registro) {
        this.cod = cod;
        this.empleado = empleado;
        this.fecha = fecha;
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "RegistroEmpleado{" +
                "cod=" + cod +
                ", empleado=" + empleado +
                ", fecha=" + fecha +
                ", registro='" + registro + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroEmpleado that = (RegistroEmpleado) o;
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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