package com.lacasitaapp.bll;

import com.lacasitaapp.admin.User;
import com.lacasitaapp.dal.DataManager;

/**
 * Created by Melvin on 30/12/2016.
 */

public class Usuario {
    public static User doLogin(String pUsuario, String pPassword) {
        User miUsuario = DataManager.buscarUsuario(pUsuario);
        if (miUsuario != null) {
            if (miUsuario.getUsuario().equalsIgnoreCase(pUsuario)
                    && miUsuario.getPassword().equalsIgnoreCase(pPassword)
                    ) {
                return miUsuario;
            }
        }
        return null;
    }
}