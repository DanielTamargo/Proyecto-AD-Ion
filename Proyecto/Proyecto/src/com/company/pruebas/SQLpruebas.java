package com.company.pruebas;

import com.company.modelo.Cliente;
import com.company.modelo.Empleado;
import com.company.modelo.Visita;
import com.company.sql.BorrarDatos;
import com.company.sql.CargarDatos;
import com.company.sql.EditarDatos;
import com.company.sql.InsertarDatos;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SQLpruebas {

    public static void main(String[] args) {

        // TODO importante cambiar el valor para que se conecte a tu BBDD
        int bbdd = 1; //1 = SQLite, 2 = MySQL, 3 = Oracle

        //Datos que insertará, modificará, y luego eliminará
        Empleado emp = new Empleado("99999999X", "Prueba", "Test",
                Date.valueOf("2000-05-30"), Date.valueOf("2020-10-20"), "Española", "Prueba", "test");

        Cliente cli = new Cliente("11111111X", "Prueba", "Test", 25, "Prueba", "test");

        Visita vis = new Visita(emp, "Prueba", 5, "Test",
                LocalDateTime.parse("2020-10-23 11:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                2020, 2.5f, "Testmática", 12.5f);

        System.out.println("- CARGAR DATOS");

        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(bbdd);
        ArrayList<Cliente> clientes = new CargarDatos().cargarClientes(bbdd);
        ArrayList<Visita> visitas = new CargarDatos().cargarVisitas(bbdd);

        if (empleados.size() > 0)
            System.out.println("\n\tEMPLEADOS:");
        for (Empleado e: empleados)
            System.out.println(e);

        if (clientes.size() > 0)
            System.out.println("\n\tCLIENTES:");
        for (Cliente c: clientes)
            System.out.println(c);

        if (visitas.size() > 0)
            System.out.println("\n\tVISITAS:");
        for (Visita v: visitas)
            System.out.println(v);

        System.out.println();

        new InsertarDatos().insertarEmpleado(bbdd, emp);
        new InsertarDatos().insertarCliente(bbdd, cli);

        new InsertarDatos().insertarVisita(bbdd, vis);
        vis.setCod(new CargarDatos().cargarCodigoVisita(bbdd, vis));
        new InsertarDatos().insertarVisitaCliente(bbdd, vis.getCod(), cli.getDni());

        new InsertarDatos().insertarRegistroEmpleado(bbdd, emp.getDni(), "Prueba Empleado");
        new InsertarDatos().insertarRegistroCliente(bbdd, cli.getDni(), "Prueba Cliente");

        // Modificar Datos
        emp.setNombre("Prueba Modificada");
        cli.setNombre("Prueba Modificada");
        vis.setNombre("Prueba Modificada");

        new EditarDatos().editarEmpleado(bbdd, emp);
        new EditarDatos().editarCliente(bbdd, cli);
        new EditarDatos().editarVisitas(bbdd, vis);

        // Borrar Datos
        new BorrarDatos().borrarEmpleado(bbdd, emp.getDni());
        new BorrarDatos().borrarCliente(bbdd, cli.getDni());
        if (bbdd == 1)
            new BorrarDatos().borrarVisita(bbdd, vis.getCod());

    }

}
