package com.company.pruebas;

import java.time.LocalDateTime;

public class Varios {

    public static void main(String[] args) {

        LocalDateTime antes = LocalDateTime.now();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (antes.isBefore(LocalDateTime.now()))
            System.out.println("Si");

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
