package com.company.pruebas;

import com.company.DB4O.BorrarDatosDB4O;
import com.company.DB4O.CargarDatosDB4O;
import com.company.DB4O.InsertarDatosBaseDB4O;
import com.company.DB4O.InsertarEditarDatosDB4O;
import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.Modelo.VisitaCliente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DB4Opruebas {

    public static void main(String[] args) {

        ArrayList<VisitaCliente> visitasClientes = new CargarDatosDB4O().cargarVisitasClientes();
        for (VisitaCliente visitaCliente : visitasClientes) {
            System.out.println(visitaCliente);
        }

        //new InsertarDatosBaseDB4O().insertarDatosBase();

        /*
        ArrayList<Cliente> clientes = new CargarDatosDB4O().cargarClientes();
        if (clientes.size() <= 0) {
            System.out.println("Insertando datos base");
            new InsertarDatosBaseDB4O().insertarDatosBase();
            clientes = new CargarDatosDB4O().cargarClientes();
        }

        for (Cliente c: clientes)
            System.out.println(c);

         */

    }

    public static void pruebasVisitasClientes() {
        ArrayList<VisitaCliente> visitasClientes = new CargarDatosDB4O().cargarVisitasClientes();
        if (visitasClientes.size() <= 0) {
            System.out.println("Insertando datos base");
            new InsertarDatosBaseDB4O().insertarDatosBase();
            visitasClientes = new CargarDatosDB4O().cargarVisitasClientes();
        }

        for (VisitaCliente vc: visitasClientes)
            System.out.println(vc);

        Cliente cliente = visitasClientes.get(0).getCliente();
        cliente.setNombre("FASDFASDFADSFFASD");
        System.out.println();

        new InsertarEditarDatosDB4O().insertarEditarCliente(cliente);

        visitasClientes = new CargarDatosDB4O().cargarVisitasClientes();
        for (VisitaCliente vc: visitasClientes)
            System.out.println(vc);
    }

    public static void pruebasVisitas() {
        ArrayList<Visita> visitas = new CargarDatosDB4O().cargarVisitas();
        if (visitas.size() <= 0) {
            System.out.println("Insertando datos base");
            new InsertarDatosBaseDB4O().insertarDatosBase();
            visitas = new CargarDatosDB4O().cargarVisitas();
        }

        for (Visita v: visitas)
            System.out.println(v);

        Empleado emp = visitas.get(0).getGuia();
        new BorrarDatosDB4O().borrarEmpleado(emp);

        System.out.println();
        visitas = new CargarDatosDB4O().cargarVisitas();
        for (Visita v: visitas)
            System.out.println(v);
    }

    public static void pruebasEmpleados() {
        ArrayList<Empleado> empleados = new CargarDatosDB4O().cargarEmpleados();
        if (empleados.size() <= 0) {
            System.out.println("Insertando datos base");
            new InsertarDatosBaseDB4O().insertarDatosBase();
            empleados = new CargarDatosDB4O().cargarEmpleados();
        }

        for (Empleado e: empleados)
            System.out.println(e);

        Empleado emp9 = new Empleado("82907601F", "Evita", "Macias",
                reconstruirFecha("1990-09-09"), reconstruirFecha("2020-03-20"),
                "Colombiana", "Guía", "1234");

        new InsertarEditarDatosDB4O().insertarEditarEmpleado(emp9);

        System.out.println();
        empleados = new CargarDatosDB4O().cargarEmpleados();
        for (Empleado e: empleados)
            System.out.println(e);
    }

    public static java.util.Date reconstruirFecha(String fechaStr) {
        Date fecha = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            fecha = format.parse(fechaStr);
        } catch (Exception e) {
            System.out.println("Error al convertir la fecha.");
        }
        return fecha;
    }

}
