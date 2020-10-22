package com.company;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.sql.CargarDatos;
import com.company.sql.Conexiones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PruebasSelect {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Conexiones conexiones = new Conexiones();
        Connection bd;
        System.out.print("Base de datos a utilizar: ");
        int bbdd;
        try {
            bbdd = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            bbdd = 1;
        }

        switch (bbdd) {
            case 1 -> bd = conexiones.conexionSQlite();
            case 2 -> bd = conexiones.conexionMySQL();
            case 3 -> bd = conexiones.conexionOracle();
            default -> bd = null;
        }

        new CargarDatos().mostrarRegistrosEmpleados(bbdd);

        //System.out.println("Forzar un error:");
        //conexiones.mostrarErrorAlConectar(new Random().nextInt(3) + 1);
        //System.out.println();
/*
        System.out.println("Empleados:");
        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(bbdd);
        for (Empleado e : empleados) {
            System.out.println(e);
        }
        System.out.println();

        if (bd != null) {
            System.out.println("Empleado concreto:");
            Empleado emp = new CargarDatos().cargarEmpleado(bbdd, bd, "72831820C");
            System.out.println(emp);
            System.out.println();
        }

        System.out.println("Clientes:");
        ArrayList<Cliente> clientes = new CargarDatos().cargarClientes(bbdd);
        for (Cliente c: clientes) {
            System.out.println(c);
        }
        System.out.println();

        if (bd != null) {
            System.out.println("Cliente concreto:");
            Cliente cli = new CargarDatos().cargarCliente(bd, "20993742D");
            System.out.println(cli);
            System.out.println();
        }

        System.out.println("Visitas:");
        ArrayList<Visita> visitas = new CargarDatos().cargarVisitas(bbdd);
        for (Visita v: visitas) {
            System.out.println(v);
        }
        System.out.println();

        System.out.println("Visitas organizadas por un empleado:");
        ArrayList<Visita> visitasEmpleado = new CargarDatos().cargarVisitasEmpleado(bbdd, "77484057R");
        for (Visita v: visitasEmpleado){
            System.out.println(v);
        }
        System.out.println();

        System.out.println("Visitas de un cliente:");
        ArrayList<Visita> visitasCliente = new CargarDatos().cargarVisitasCliente(bbdd, "dnifalso");
        for (Visita v: visitasCliente){
            System.out.println(v);
        }
        System.out.println();
*/
        try {
            if (bd != null)
                bd.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
