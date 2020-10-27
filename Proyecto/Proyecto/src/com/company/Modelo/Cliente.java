package com.company.Modelo;

import java.util.Objects;

public class Cliente {

    private String dni;
    private String nombre;
    private String apellidos;
    private int edad;
    private String profesion;
    private String contrasenya;

    public Cliente() {
    }

    public Cliente(String dni) {
        this.dni = dni;
    }

    public Cliente(String dni, String nombre, String apellidos, int edad, String profesion, String contrasenya) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.profesion = profesion;
        this.contrasenya = contrasenya;
    }

    public void cambiarDatosDB4O(Cliente cli) {
        this.nombre = cli.getNombre();
        this.apellidos = cli.getApellidos();
        this.edad = cli.getEdad();
        this.profesion = cli.getProfesion();
        this.contrasenya = cli.getContrasenya();
    }

    @Override //TODO habrá que dejar bien lindo el toString para que se muestre elegante en los JList
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", profesion='" + profesion + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return dni.equals(cliente.dni);
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}