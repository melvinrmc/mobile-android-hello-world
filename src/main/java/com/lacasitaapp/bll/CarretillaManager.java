package com.lacasitaapp.bll;

import com.lacasitaapp.dal.User;
import com.lacasitaapp.dal.Producto;
import com.lacasitaapp.dal.Venta;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Melvin on 15/01/2017.
 */

public class CarretillaManager {

    private Venta laVenta;
    private User vendedor;

    public CarretillaManager(User u){
        this.vendedor = u;
    };

    public void iniciarVenta(User u){
        vendedor = u;
    }

    public int addProducto(Producto p, float cantidad) {
        return 0;
    }

}
