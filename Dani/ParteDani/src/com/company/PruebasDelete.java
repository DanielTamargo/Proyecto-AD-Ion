package com.company;

import com.company.sql.BorrarDatos;
import com.company.sql.Conexiones;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebasDelete {

    public static void main(String[] args) {

        int bbdd = 1;

        new BorrarDatos().borrarEmpleado(1, "88139440J");

    }

}
