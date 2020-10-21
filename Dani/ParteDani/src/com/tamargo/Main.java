package com.tamargo;

import com.tamargo.Modelo.Cliente;
import com.tamargo.Modelo.Empleado;
import com.tamargo.Modelo.Visita;
import com.tamargo.sql.CargarDatos;
import com.tamargo.sql.Conexiones;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        System.out.println(    "------ Pruebas Select:");
        PruebasSelect.main(args);
        System.out.println("\n\n------ Pruebas Delete:");
        PruebasDelete.main(args);
        System.out.println("\n\n------ Pruebas Update:");
        PruebasUpdate.main(args);

    }
}
