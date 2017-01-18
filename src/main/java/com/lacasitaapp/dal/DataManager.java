package com.lacasitaapp.dal;

import android.content.Context;

import com.amazonaws.AmazonServiceException;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.demo.userpreferencesom.AmazonClientManager;

import java.util.ArrayList;

/**
 * Created by Melvin on 28/12/2016.
 */

public class DataManager {

    public static AmazonClientManager clientManager = null;


    public DataManager(Context context) {
        this.clientManager = new AmazonClientManager(context);
    }

    public static User buscarUsuario(String usuario) {

        AmazonDynamoDBClient ddb = clientManager.ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            User miUsuario = mapper.load(User.class,
                    usuario);

            return miUsuario;

        } catch (AmazonServiceException ex) {
            clientManager.wipeCredentialsOnAuthError(ex);
        }
        return null;
    }

    public static ArrayList<Producto> getProductos() {

        AmazonDynamoDBClient ddb = clientManager.ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        try {
            PaginatedScanList<Producto> result = mapper.scan(
                    Producto.class, scanExpression);

            ArrayList<Producto> resultList = new ArrayList<Producto>();
            for (Producto p : result) {
                resultList.add(p);
            }
            return resultList;

        } catch (AmazonServiceException ex) {
            clientManager.wipeCredentialsOnAuthError(ex);
        }

        return null;
    }

    public static ArrayList<Categoria> getCategorias() {

        AmazonDynamoDBClient ddb = clientManager.ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        try {
            PaginatedScanList<Categoria> result = mapper.scan(
                    Categoria.class, scanExpression);

            ArrayList<Categoria> resultList = new ArrayList<Categoria>();
            for (Categoria c : result) {
                resultList.add(c);
            }
            return resultList;

        } catch (AmazonServiceException ex) {
            clientManager.wipeCredentialsOnAuthError(ex);
        }

        return null;
    }
}
