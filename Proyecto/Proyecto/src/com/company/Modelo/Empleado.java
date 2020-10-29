package com.company.Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Empleado {

    private String dni;
    private String nombre;
    private String primerapellido;
    private Date fechaNac;
    private Date fechaContratacion;
    private java.util.Date fechaNacDB4O;
    private java.util.Date fechaContratacionDB4O;
    private String nacionalidad;
    private String cargo;
    private String contrasenya;

    public Empleado() {
    }

    public Empleado(String dni) {
        this.dni = dni;
    }

    public Empleado(String dni, String contrasenya) {
        this.dni = dni;
        this.contrasenya = contrasenya;
    }

    public Empleado(String dni, String nombre, String primerapellido, java.util.Date fechaNac, java.util.Date fechaContratacion,
                    String nacionalidad, String cargo, String contrasenya) {
        this.dni = dni;
        this.nombre = nombre;
        this.primerapellido = primerapellido;
        this.fechaNacDB4O = fechaNac;
        this.fechaContratacionDB4O = fechaContratacion;
        this.nacionalidad = nacionalidad;
        this.cargo = cargo;
        this.contrasenya = contrasenya;
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

    public void cambiarDatosDB4O(Empleado emp) {
        this.nombre = emp.getNombre();
        this.primerapellido = emp.getPrimerapellido();
        this.fechaNacDB4O = emp.getFechaNacDB4O();
        this.fechaContratacionDB4O = emp.getFechaContratacionDB4O();
        this.nacionalidad = emp.getNacionalidad();
        this.cargo = emp.getCargo();
        this.contrasenya = emp.getContrasenya();
    }

    public String fechaFormateada(java.util.Date fecha) {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(fecha);
    }

    @Override
    public String toString() {
        String apellidoNombre = primerapellido + ", " + nombre;
        String cargo = this.cargo;
        if (cargo.equalsIgnoreCase("Administrador"))
            cargo = "Admin";
        return String.format("%-9s | %-20s | %s", dni, apellidoNombre, cargo);
        /*
        if (fechaNacDB4O == null) {
            return "Empleado{" +
                    "dni='" + dni + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", primerapellido='" + primerapellido + '\'' +
                    ", fechaNac=" + fechaNac +
                    ", fechaContratacion=" + fechaContratacion +
                    ", nacionalidad='" + nacionalidad + '\'' +
                    ", cargo='" + cargo + '\'' +
                    ", contrasenya='" + contrasenya + '\'' +
                    '}';
        } else {
            return "Empleado{" +
                    "dni='" + dni + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", primerapellido='" + primerapellido + '\'' +
                    ", fechaNac=" + fechaFormateada(fechaNacDB4O) +
                    ", fechaContratacion=" + fechaFormateada(fechaContratacionDB4O) +
                    ", nacionalidad='" + nacionalidad + '\'' +
                    ", cargo='" + cargo + '\'' +
                    ", contrasenya='" + contrasenya + '\'' +
                    '}';
        }*/
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

    public java.util.Date getFechaNacDB4O() {
        return fechaNacDB4O;
    }

    public void setFechaNacDB4O(java.util.Date fechaNacDB4O) {
        this.fechaNacDB4O = fechaNacDB4O;
    }

    public java.util.Date getFechaContratacionDB4O() {
        return fechaContratacionDB4O;
    }

    public void setFechaContratacionDB4O(java.util.Date fechaContratacionDB4O) {
        this.fechaContratacionDB4O = fechaContratacionDB4O;
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