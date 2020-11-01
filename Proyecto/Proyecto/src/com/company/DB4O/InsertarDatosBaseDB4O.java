package com.company.DB4O;

import com.company.modelo.Cliente;
import com.company.modelo.Empleado;
import com.company.modelo.Visita;
import com.company.modelo.VisitaCliente;
import com.db4o.ObjectContainer;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InsertarDatosBaseDB4O {

    //TODO -> devolver los arraylist para luego ir metiendo nuevos datos que se añadan a los base
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Visita> visitas = new ArrayList<>();
    public ArrayList<VisitaCliente> visitasClientes = new ArrayList<>();

    public InsertarDatosBaseDB4O(){}

    public void insertarEmpleadosBase(){

        Empleado emp1 = new Empleado("52354260G", "Laura", "Velarde",
                reconstruirFecha("1995-05-11"), reconstruirFecha("2017-04-24"),
                "Española", "Administrador", "1234");

        Empleado emp2 = new Empleado("23020399K", "Marta", "Palma",
                reconstruirFecha("1992-09-05"), reconstruirFecha("2019-01-13"),
                "Española", "Administrador", "1234");

        Empleado emp3 = new Empleado("68601853Y", "Sofia", "Arrazol",
                reconstruirFecha("1986-02-09"), reconstruirFecha("2018-12-04"),
                "Española", "RRHH", "1234");

        Empleado emp4 = new Empleado("53955150W", "Juan", "Alberdi",
                reconstruirFecha("1985-05-12"), reconstruirFecha("2019-02-10"),
                "Española", "RRHH", "1234");

        Empleado emp5 = new Empleado("12607802F", "Fernando", "Leyva",
                reconstruirFecha("1980-11-01"), reconstruirFecha("2020-01-22"),
                "Argentina", "Guía", "1234");

        Empleado emp6 = new Empleado("39333645B", "Adrian", "Jimenez",
                reconstruirFecha("1980-12-08"), reconstruirFecha("2020-02-01"),
                "Española", "Guía", "1234");

        Empleado emp7 = new Empleado("84442298Y", "Daniel", "Gutierrez",
                reconstruirFecha("1990-11-12"), reconstruirFecha("2019-01-13"),
                "Española", "Guía", "1234");

        Empleado emp8 = new Empleado("55178890G", "Lucia", "Morato",
                reconstruirFecha("1983-01-24"), reconstruirFecha("2020-04-02"),
                "Española", "Guía", "1234");

        Empleado emp9 = new Empleado("82907601F", "Eva", "Macias",
                reconstruirFecha("1990-09-09"), reconstruirFecha("2020-03-20"),
                "Colombiana", "Guía", "1234");

        empleados.add(emp1);
        empleados.add(emp2);
        empleados.add(emp3);
        empleados.add(emp4);
        empleados.add(emp5);
        empleados.add(emp6);
        empleados.add(emp7);
        empleados.add(emp8);
        empleados.add(emp9);

        if (empleados.size() > 0){
            System.out.println("Empleados insertados con éxito.");
        } else {
            System.out.println("Los empleados no se pueden cargar en la lista 'empleados'");
        }
    }

    public void insertarClientesBase(){

        Cliente cli1 = new Cliente("34678765G", "Violeta", "Bicardi", 34, "Arquitecta", "1234");
        Cliente cli2 = new Cliente("56789432W", "Diego", "Jimenez", 22, "Estudiante", "1234");
        Cliente cli3 = new Cliente("22347812Q", "Julen", "Olarizu", 28, "Administrativo", "1234");
        Cliente cli4 = new Cliente("56766654U", "Sandra", "Villastres", 34, "Arquitecta", "1234");
        Cliente cli5 = new Cliente("45667545T", "Violeta", "Bicardi", 34, "Abogada", "1234");
        Cliente cli6 = new Cliente("52076665L", "Samuel", "Navalon", 50, "Profesor", "1234");
        Cliente cli7 = new Cliente("62927665X", "Jose Manuel", "Verdu", 22, "Estudiante", "1234");
        Cliente cli8 = new Cliente("08958157W", "Angela", "Robles", 34, "Enfermera", "1234");
        Cliente cli9 = new Cliente("28045926W", "Miriam", "Pereiro", 42, "Enfermera", "1234");

        clientes.add(cli1);
        clientes.add(cli2);
        clientes.add(cli3);
        clientes.add(cli4);
        clientes.add(cli5);
        clientes.add(cli6);
        clientes.add(cli7);
        clientes.add(cli8);
        clientes.add(cli9);

        if (clientes.size() > 0){
            System.out.println("Clientes insertados con éxito.");
        } else {
            System.out.println("Los clientes no se pueden cargar en la lista 'clientes'");
        }
    }

    public void insertarVisitasBase(){

        Empleado emp5 = empleados.get(4);
        Empleado emp7 = empleados.get(6);
        Empleado emp6 = empleados.get(5);
        Empleado emp9 = empleados.get(8);
        Empleado emp8 = empleados.get(7);

        Visita visita1 = new Visita(1, emp5, "Visita nocturna", 50, "Plaza España",
                stringToLocalDateTime("2020-10-16 21:30:00"), 2020, 1.5f, "Misterio", 45.50f);
        Visita visita2 = new Visita(2, emp7, "Visita histórica muralla", 33, "Catedral Santa Maria",
                stringToLocalDateTime("2020-10-11 18:00:00"), 2020, 2.0f, "Historia", 40.0f);
        Visita visita3 = new Visita(3, emp6, "Visita histórica", 20, "Plaza Abastos",
                stringToLocalDateTime("2020-10-23 12:00:00"), 2020, 2.0f, "Historia", 30.0f);

        Visita visita4 = new Visita(4, emp8, "Visita histórica", 45, "Plaza Abastos",
                stringToLocalDateTime("2020-11-01 17:00:00"), 2020, 2.5f, "Historia", 25.0f);

        Visita visita5 = new Visita(5, emp5, "Visita por las afueras", 20, "Estatua del Santo",
                stringToLocalDateTime("2020-10-10 11:00:00"), 2020, 2.5f, "Historia", 20.0f);

        Visita visita6 = new Visita(6, emp9, "Visita leyendas", 20, "Virgen Blanca",
                stringToLocalDateTime("2020-11-01 20:00:00"), 2020, 2.5f, "Misterio", 30.0f);

        Visita visita7 = new Visita(7, emp6, "Visita histórica con leyendas", 30, "Plaza España",
                stringToLocalDateTime("2020-10-22 18:30:00"), 2020, 2.0f, "Misterio", 25.0f);

        visitas.add(visita1);
        visitas.add(visita2);
        visitas.add(visita3);
        visitas.add(visita4);
        visitas.add(visita5);
        visitas.add(visita6);
        visitas.add(visita7);

        if (visitas.size() > 0){
            System.out.println("Visitas insertadas con éxito.");
        } else {
            System.out.println("Las visitas no se pueden cargar en la lista 'visitas'");
        }
    }

    public void insertarVisitasClientesBase(){

        Cliente cliente2 = clientes.get(1);
        Cliente cliente5 = clientes.get(4);
        Cliente cliente1 = clientes.get(0);
        Cliente cliente9 = clientes.get(8);
        Cliente cliente6 = clientes.get(5);
        Cliente cliente8 = clientes.get(7);
        Cliente cliente3 = clientes.get(2);

        VisitaCliente visitaCliente1 = new VisitaCliente(cliente2, visitas.get(0));
        VisitaCliente visitaCliente2 = new VisitaCliente(cliente5, visitas.get(1));
        VisitaCliente visitaCliente3 = new VisitaCliente(cliente1, visitas.get(4));
        VisitaCliente visitaCliente4 = new VisitaCliente(cliente1, visitas.get(0));
        VisitaCliente visitaCliente5 = new VisitaCliente(cliente9, visitas.get(2));
        VisitaCliente visitaCliente6 = new VisitaCliente(cliente6, visitas.get(3));
        VisitaCliente visitaCliente7 = new VisitaCliente(cliente2, visitas.get(3));
        VisitaCliente visitaCliente8 = new VisitaCliente(cliente8, visitas.get(1));
        VisitaCliente visitaCliente9 = new VisitaCliente(cliente9, visitas.get(6));
        VisitaCliente visitaCliente10 = new VisitaCliente(cliente3, visitas.get(5));

        visitasClientes.add(visitaCliente1);
        visitasClientes.add(visitaCliente2);
        visitasClientes.add(visitaCliente3);
        visitasClientes.add(visitaCliente4);
        visitasClientes.add(visitaCliente5);
        visitasClientes.add(visitaCliente6);
        visitasClientes.add(visitaCliente7);
        visitasClientes.add(visitaCliente8);
        visitasClientes.add(visitaCliente9);
        visitasClientes.add(visitaCliente10);

        if (visitasClientes.size() > 0){
            System.out.println("Las visitas de los clientes insertadas con éxito.");
        } else {
            System.out.println("Las visitas de los clientes no se pueden cargar en la lista 'visitasClientes'");
        }
    }

    public void insertarDatosBase(){

        ObjectContainer bd = new ConexionDB4O().conectarBD();

        insertarEmpleadosBase();
        for (Empleado empleado : empleados) {
            bd.store(empleado);
        }

        insertarClientesBase();
        for (Cliente cliente : clientes) {
            bd.store(cliente);
        }

        insertarVisitasBase();
        for (Visita visita : visitas) {
            bd.store(visita);
        }

        insertarVisitasClientesBase();
        for (VisitaCliente visitaCliente : visitasClientes) {
            bd.store(visitaCliente);
        }
        bd.close();
    }

    /**
     * Método reutilizado cada vez que queramos convertir un string a localdatetime
     *
     * @param fechaStr <- fecha en una variable tipo string
     * @return <- devolvemos la variable tipo localdatetime con la fecha recibida cargada
     */
    public static LocalDateTime stringToLocalDateTime(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fecha = null;
        try {
            fecha = LocalDateTime.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error al convertir la fecha.");
        }
        return fecha;
    }

    /**
     * Método reutilizado por los distintos métodos que necesiten cargar o insertar datos tipo Date en la DB4O
     *
     * @param fechaStr <- la fecha versión String
     * @return <- la fecha bien construida
     */
    public static Date reconstruirFecha(String fechaStr) {
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
