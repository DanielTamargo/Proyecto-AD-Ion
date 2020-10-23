package com.company.pruebassql;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.sql.BorrarDatos;
import com.company.sql.CargarDatos;
import com.company.sql.EditarDatos;
import com.company.sql.InsertarDatos;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PruebasFuncionamiento {

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

        System.out.println(vis.getFecha());

        System.out.println("- CARGAR DATOS");

        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(bbdd);
        ArrayList<Cliente> clientes = new CargarDatos().cargarClientes(bbdd);
        ArrayList<Visita> visitas = new CargarDatos().cargarVisitas(bbdd);

        System.out.println("\n\tEMPLEADOS:");
        for (Empleado e: empleados)
            System.out.println(e);

        System.out.println("\n\tCLIENTES:");
        for (Cliente c: clientes)
            System.out.println(c);

        System.out.println("\n\tVISITAS:");
        for (Visita v: visitas)
            System.out.println(v);

        System.out.println("\n------------------------------------------------------------------------------");
        System.out.println("- INSERTAR DATOS\n");

        new InsertarDatos().insertarEmpleado(bbdd, emp);
        new InsertarDatos().insertarCliente(bbdd, cli);

        new InsertarDatos().insertarVisita(bbdd, vis);
        vis.setCod(new CargarDatos().cargarCodigoVisita(bbdd, vis));
        new InsertarDatos().insertarVisitaCliente(bbdd, vis.getCod(), cli.getDni());

        new InsertarDatos().insertarRegistroEmpleado(1, emp.getDni(), "Prueba Empleado");
        new InsertarDatos().insertarRegistroCliente(1, cli.getDni(), "Prueba Cliente");

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
        new BorrarDatos().borrarVisita(bbdd, vis.getCod());

    }

}
