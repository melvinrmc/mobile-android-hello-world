package com.lacasitaapp.dal;

import com.amazonaws.demo.userpreferencesom.Constants;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Melvin on 15/01/2017.
 */
@DynamoDBTable(tableName = Constants.LC_POS_VENTA)
public class Venta {

    public enum EstadoVenta {EN_ESPERA, EN_PROGRESO, FINALIZADO, PAGADO, ENTREGADO}
    

    private String idVenta;
    private Date fechaVenta;
    private User usuario;
    private ArrayList<ItemVenta> itemsVenta;
    private float totalMonto;
    private float totalDenominacionPago;
    private float cambio;
    private EstadoVenta estadoVenta;

    @DynamoDBHashKey(attributeName = "id_venta")
    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    @DynamoDBAttribute(attributeName = "fecha_venta")
    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @DynamoDBAttribute(attributeName = "usuario")
    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    @DynamoDBAttribute(attributeName = "items_venta")
    public ArrayList<ItemVenta> getItemsVenta() {
        return itemsVenta;
    }

    public void setItemsVenta(ArrayList<ItemVenta> itemsVenta) {
        this.itemsVenta = itemsVenta;
    }

    @DynamoDBAttribute(attributeName = "total_monto")
    public float getTotalMonto() {
        return totalMonto;
    }

    public void setTotalMonto(float totalMonto) {
        this.totalMonto = totalMonto;
    }

    @DynamoDBAttribute(attributeName = "denominacion_pago")
    public float getTotalDenominacionPago() {
        return totalDenominacionPago;
    }

    public void setTotalDenominacionPago(float totalDenominacionPago) {
        this.totalDenominacionPago = totalDenominacionPago;
    }

    @DynamoDBAttribute(attributeName = "cambio")
    public float getCambio() {
        return cambio;
    }


    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

    @DynamoDBAttribute(attributeName = "estado_venta")
    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
    
    public void addItem(ItemVenta iv ){
        this.itemsVenta.add(iv);
        return;
    }
}