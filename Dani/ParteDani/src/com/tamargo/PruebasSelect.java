package com.tamargo;

import com.tamargo.Modelo.Cliente;
import com.tamargo.Modelo.Empleado;
import com.tamargo.Modelo.Visita;
import com.tamargo.sql.CargarDatos;
import com.tamargo.sql.Conexiones;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PruebasSelect {

    public static void main(String[] args) {

        Conexiones conexiones = new Conexiones();
        Connection bd;
        int bbdd = 1;

        switch (bbdd) {
            case 1 -> bd = conexiones.conexionSQlite();
            case 2 -> bd = conexiones.conexionMySQL();
            case 3 -> bd = conexiones.conexionOracle();
            default -> bd = conexiones.conexionSQlite();
        }


        //System.out.println("Forzar un error:");
        //conexiones.mostrarErrorAlConectar(new Random().nextInt(3) + 1);
        //System.out.println();

        System.out.println("Empleados:");
        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(bbdd);
        for (Empleado e : empleados) {
            System.out.println(e);
        }
        System.out.println();

        System.out.println("Empleado concreto:");
        Empleado emp = new CargarDatos().cargarEmpleado(bbdd, bd, "72831820C");
        System.out.println(emp);
        System.out.println();

        System.out.println("Clientes:");
        ArrayList<Cliente> clientes = new CargarDatos().cargarClientes(bbdd);
        for (Cliente c: clientes) {
            System.out.println(c);
        }
        System.out.println();

        System.out.println("Cliente concreto:");
        Cliente cli = new CargarDatos().cargarCliente(bd, "20993742D");
        System.out.println(cli);
        System.out.println();

        System.out.println("Visitas:");
        ArrayList<Visita> visitas = new CargarDatos().cargarVisitas(bbdd);
        for (Visita v: visitas) {
            System.out.println(v);
        }
        System.out.println();

        System.out.println("Visitas organizadas por un empleado:");
        ArrayList<Visita> visitasEmpleado = new CargarDatos().cargarVisitasOrganizadasPorEmpleado(bbdd, "77484057R");
        for (Visita v: visitasEmpleado){
            System.out.println(v);
        }
        System.out.println();

        System.out.println("Visitas de un cliente:");
        ArrayList<Visita> visitasCliente = new CargarDatos().cargarVisitasDeUnCliente(bbdd, "dnifalso");
        for (Visita v: visitasCliente){
            System.out.println(v);
        }
        System.out.println();

        try {
            bd.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
