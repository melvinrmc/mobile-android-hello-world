package com.lacasitaapp.admin;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.demo.userpreferencesom.Constants;

/**
 * Created by Melvin on 28/12/2016.
 */
@DynamoDBTable(tableName = Constants.LC_POS_USUARIO)
public class User {
    private String usuario;
    private String nombre;
    private String password;
    private String estado;

    @DynamoDBHashKey(attributeName = "usuario")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @DynamoDBAttribute(attributeName = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute(attributeName = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
