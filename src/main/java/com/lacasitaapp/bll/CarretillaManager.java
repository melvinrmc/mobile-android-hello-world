package com.lacasitaapp.bll;




import com.lacasitaapp.dal.DataManager;
import com.lacasitaapp.dal.User;
import com.lacasitaapp.dal.Producto;
import com.lacasitaapp.dal.Venta;
import com.lacasitaapp.dal.ItemVenta;


import java.util.Calendar;
import java.util.TimeZone;

import java.util.Date;
import java.util.List;


/**
 * Created by Melvin on 15/01/2017.
 */

public class CarretillaManager {


    private Venta venta;
    private User vendedor;

    public CarretillaManager(User u) {
        this.vendedor = u;
        venta = new Venta();
        venta.setUsuario(u);
    }

    ;

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public void iniciarVenta(User u) {
        vendedor = u;
        venta.setUsuario(u);
    }

    public User getVendedor() {
        return vendedor;
    }

    public int addProducto(Producto p, float cantidad) {
        ItemVenta iv = new ItemVenta(p, cantidad);
        venta.addItem(iv);
        return venta.getItemsVenta().size();
    }


    public int getNumItems() {
        return venta.getItemsVenta().size();
    }

    public List<ItemVenta> getItems() {
        return venta.getItemsVenta();
    }

    public float getTotal() {

        return venta.calcularTotal();
    }

    public String getResumen() {
        return "(" + venta.getItemsVenta().size() + ") Productos por (Q. " + String.valueOf(getTotal()) + ")";
    }

    public static Venta guardar(Venta v) {
        v.setEstadoVenta(Venta.EstadoVenta.PAGADO);
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        

        v.setFechaVenta(c.getTime());
        v.setTotalMonto(v.calcularTotal());

        return DataManager.saveObject(v);

    }


}
