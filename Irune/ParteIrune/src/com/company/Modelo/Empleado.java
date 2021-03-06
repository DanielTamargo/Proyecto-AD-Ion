package com.company.Modelo;

import java.sql.Date;
import java.util.Objects;

public class Empleado {

    private String dni;
    private String nombre;
    private String primerapellido;
    private Date fechaNac;
    private Date fechaContratacion;
    private String nacionalidad;
    private String cargo;
    private String contrasenya;

    public Empleado() {
    }

    public Empleado(String dni, String nombre, String primerapellido, Date fechaNac, Date fechaContratacion,
                    String nacionalidad, String cargo, String contrasenya) {
        this.dni = dni;
        this.nombre = nombre;
        this.primerapellido = primerapellido;
        this.fechaNac = fechaNac;
        this.fechaContratacion = fechaContratacion;
        this.nacionalidad = nacionalidad;
        this.cargo = cargo;
        this.contrasenya = contrasenya;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return dni.equals(empleado.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}