package com.company.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Conexion {

    public Conexion() { }

    /**
     * Método que devuelve la conexión a la bbdd DB4O
     *
     * @return db <- devuelve la conexión
     */
    public ObjectContainer conectarBD(){
        String BDAgencia = "DB4O.yap";
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDAgencia);
        return db;
    }
}
