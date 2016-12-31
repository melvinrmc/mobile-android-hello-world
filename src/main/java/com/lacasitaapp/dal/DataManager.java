package com.lacasitaapp.dal;

import android.content.Context;

import com.amazonaws.AmazonServiceException;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.codenvy.template.android.HelloAndroidActivity;
import com.lacasitaapp.admin.User;
import com.amazonaws.demo.userpreferencesom.AmazonClientManager;
import com.amazonaws.demo.userpreferencesom.DynamoDBManager;

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
}
