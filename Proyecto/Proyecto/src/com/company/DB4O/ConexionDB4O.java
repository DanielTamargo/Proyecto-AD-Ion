package com.company.DB4O;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;

import javax.swing.*;

public class ConexionDB4O {

    public ConexionDB4O() { }

    /**
     * Método que devuelve la conexión a la bbdd DB4O
     *
     * @return db <- devuelve la conexión
     */
    public ObjectContainer conectarBD(){
        ObjectContainer db = null;
        String BDAgencia = "db4o.yap";
        try {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDAgencia);
        } catch (Db4oException ex) {
            JButton okButton = new JButton("Entendido");
            okButton.setFocusPainted(false);
            Object[] options = {okButton};
            final JOptionPane pane = new JOptionPane("Error al conectar con la BBDD 'DB4O'", JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);
            JDialog dialog = pane.createDialog("Error al conectar");
            okButton.addActionListener(e -> dialog.dispose());
            dialog.setVisible(true);
        }
        return db;
    }
}
