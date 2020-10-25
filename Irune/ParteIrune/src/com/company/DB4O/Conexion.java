package com.company.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Conexion {

    public Conexion(){}

    public ObjectContainer conectarBD(){
        String BDAgencia = "Agencia.yap";
        return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDAgencia);
    }
}
