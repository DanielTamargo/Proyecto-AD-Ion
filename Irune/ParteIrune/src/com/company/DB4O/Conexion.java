package com.company.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Conexion {

    public String BDAgencia = "Agencia.yap";
    public ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDAgencia);

    public Conexion(){}

    public ObjectContainer conectarBD(){

        /*ObjectContainer rootContainer = db;
        ObjectContainer session = rootContainer.ext().openSession();


        return session;*/

        String BDAgencia = "Agencia.yap";
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDAgencia);

        return db;
    }
}
