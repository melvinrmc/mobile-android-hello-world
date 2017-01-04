package com.lacasitaapp.dal;

import com.amazonaws.demo.userpreferencesom.Constants;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Melvin on 3/01/2017.
 */
@DynamoDBTable(tableName = Constants.LC_POS_CATEGORIA)
public class Categoria {
    private String idCategoria;
    private String color;
    private String urlImagen;
    private String nombre;

    @DynamoDBHashKey(attributeName = "id_categoria")
    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    @DynamoDBAttribute(attributeName = "color_categoria")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @DynamoDBAttribute(attributeName = "imagen_categoria")
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
}
