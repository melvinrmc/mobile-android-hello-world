package com.lacasitaapp.bll;

import com.lacasitaapp.dal.Categoria;
import com.lacasitaapp.dal.DataManager;
import com.lacasitaapp.dal.Producto;

import java.util.ArrayList;

/**
 * Created by Melvin on 4/01/2017.
 */

public class ProductoManager {

    public static ArrayList<Producto> productos = null;
    public static ArrayList<Categoria> categorias = null;

    public static Integer loadProductos() {

        if (productos == null) {
            productos = DataManager.getProductos();
        }
        return productos.size();

    }

    public static Integer loadCategorias() {
        if (categorias == null) {
            categorias = DataManager.getCategorias();
        }
        return categorias.size();
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

    public static Categoria getCategoria(String categoria) {

        Categoria resultCategoria = null;

        for (Categoria c : categorias) {
            if (c.getIdCategoria().equalsIgnoreCase(categoria)) {
                resultCategoria = c;
                return resultCategoria;
            }
        }
        return resultCategoria;
    }
}

