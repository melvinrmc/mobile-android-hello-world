package com.lacasitaapp.bll;

import android.provider.ContactsContract;

import com.lacasitaapp.admin.User;
import com.lacasitaapp.dal.DataManager;
import com.lacasitaapp.dal.Producto;

import java.util.ArrayList;

/**
 * Created by Melvin on 4/01/2017.
 */

public class ProductoManager {

    public static ArrayList<Producto> productos = null;

    public static Integer loadProductos() {

        if (productos == null) {
            productos = DataManager.getProductos();
        }
        return productos.size();

    }

    public static ArrayList<Producto> getProductos(String categoria) {

        ArrayList<Producto> resultList = new ArrayList<Producto>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultList.add(p);
            }
        }
        return resultList;
    }
}

