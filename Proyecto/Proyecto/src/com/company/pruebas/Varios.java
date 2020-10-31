package com.company.pruebas;

import com.company.Modelo.Visita;
import com.company.sql.CargarDatos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Varios {

    public static void main(String[] args) {

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("Hola"); test1.add("Bien"); test1.add("Mal"); test1.add("Genial"); test1.add("Adiós");
        ArrayList<String> test2 = new ArrayList<>();
        test2.add("AAAA"); test2.add("BBBB"); test2.add("Mal"); test2.add("CCCC"); test2.add("DDDD");

        for (String s1: test1) {
            System.out.println(s1);
            for (String s2: test2) {
                if (s1.equalsIgnoreCase(s2)) {
                    System.out.println("Coincide -> " + s2);
                    break;
                }
                System.out.println(s2);
            }
            System.out.println();
        }


        /*
        ArrayList<Visita> visitas = new CargarDatos().cargarVisitas(1);
        for (Visita v: visitas)
            System.out.println(v);

        System.out.println();

        visitas.sort(Comparator.comparing(Visita::getFecha));

        for (Visita v: visitas)
            System.out.println(v);
        */

        /*
        LocalDateTime antes = LocalDateTime.now();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (antes.isBefore(LocalDateTime.now()))
            System.out.println("Si");

         */

        //String xd = "UPDATE empleados SET dni='99999999X', nombre='Prueba Modificada', primerApellido='Test', fechaNac='2000/05/30', fechaContratacion='2020/10";
        //System.out.println(xd.substring(98));
/*
        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(1);
        Empleado emple = empleados.get(0);
        String fechaNac = emple.getFechaNac().toString();
        String fechaContr = emple.getFechaContratacion().toString();
        System.out.println(fechaNac);
        System.out.println(fechaContr);

 */

    }

}
