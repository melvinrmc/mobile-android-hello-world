package com.lacasitaapp.dal;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBDocument;

@DynamoDBDocument
public class ItemVenta {
    private Producto producto;
    private float cantidad;


    public ItemVenta(Producto p, float c) {
        this.producto = p;
        this.cantidad = c;
    }

    @DynamoDBAttribute(attributeName = "producto")
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @DynamoDBAttribute(attributeName = "cantidad")
    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getMontoItem() {
        return producto.getPrecio() * cantidad;
    }
}