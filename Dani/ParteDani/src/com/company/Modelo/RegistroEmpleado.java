package com.company.Modelo;

import java.time.LocalDateTime;

public class RegistroEmpleado {

    private int cod;
    private Empleado empleado;
    private LocalDateTime fecha;
    private String registro;

    public RegistroEmpleado() {
    }

    public RegistroEmpleado(int cod, Empleado empleado, LocalDateTime fecha, String registro) {
        this.cod = cod;
        this.empleado = empleado;
        this.fecha = fecha;
        this.registro = registro;
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