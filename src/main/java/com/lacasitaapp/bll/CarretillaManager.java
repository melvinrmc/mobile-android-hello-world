package com.lacasitaapp.bll;

import com.lacasitaapp.dal.User;
import com.lacasitaapp.dal.Producto;
import com.lacasitaapp.dal.Venta;
import com.lacasitaapp.dal.ItemVenta;

import java.util.ArrayList;


/**
 * Created by Melvin on 15/01/2017.
 */

public class CarretillaManager {

    private Venta laVenta;
    private User vendedor;

    public CarretillaManager(User u){
        this.vendedor = u;
        laVenta = new Venta();
    };

    public void iniciarVenta(User u){
        vendedor = u;
    }
    
    public User getVendedor(){
        return vendedor;
    }

    public int addProducto(Producto p, float cantidad) {
        ItemVenta iv = new ItemVenta(p,cantidad);
        laVenta.addItem(iv);
        return laVenta.getItemsVenta().size();
    }


    public int getNumItems() {
        return  laVenta.getItemsVenta().size();
    }

    public ArrayList<ItemVenta> getItems() {
        return laVenta.getItemsVenta();
    }

    public float getTotal() {
        float acumulado = 0;
        for (ItemVenta iv: laVenta.getItemsVenta()) {
            acumulado = acumulado + iv.getMontoItem();
        }
        return acumulado;
    }
    public String getResumen() {
        return "(" + laVenta.getItemsVenta().size() + ") Productos por (Q "+ String.valueOf(getTotal()) + ")";
    }
}
