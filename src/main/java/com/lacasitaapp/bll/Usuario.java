package com.lacasitaapp.bll;

import com.lacasitaapp.admin.User;
import com.lacasitaapp.dal.DataManager;

/**
 * Created by Melvin on 30/12/2016.
 */

public class Usuario {
    public static User doLogin(String usuario, String password) {
        User miUsuario = DataManager.buscarUsuario(usuario);
        if (miUsuario != null) {
            if (miUsuario.getUsuario().equalsIgnoreCase(usuario)
                    && miUsuario.getPassword().equalsIgnoreCase(password)
                    ) {
                return miUsuario;
            }
        }
        return null;
    }
}