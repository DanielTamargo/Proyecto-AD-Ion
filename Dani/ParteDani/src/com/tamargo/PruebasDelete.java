package com.tamargo;

import com.tamargo.sql.Conexiones;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebasDelete {

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






        try {
            bd.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
