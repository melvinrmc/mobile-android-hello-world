package com.lacasitaapp.dal;

import com.amazonaws.demo.userpreferencesom.Constants;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Melvin on 3/01/2017.
 */
@DynamoDBTable(tableName = Constants.LC_POS_PRODUCTO)
public class Producto {
    private String idProducto;
    private String categoria;
    private float descuentoMayorista;
    private String urlImagen;
    private String nombre;
    private float precio;
    private String unidadMedida;

    @DynamoDBHashKey(attributeName = "id_producto")
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    @DynamoDBAttribute(attributeName = "categoria")
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @DynamoDBAttribute(attributeName = "descuento_mayorista")
    public float getDescuentoMayorista() {
        return descuentoMayorista;
    }

    public void setDescuentoMayorista(float descuentoMayorista) {
        this.descuentoMayorista = descuentoMayorista;
    }

    @DynamoDBAttribute(attributeName = "imagen_producto")
    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @DynamoDBAttribute(attributeName = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @DynamoDBAttribute(attributeName = "precio")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @DynamoDBAttribute(attributeName = "unidad_medida")
    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }


}
