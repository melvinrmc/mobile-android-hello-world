package com.codenvy.template.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.amazonaws.demo.userpreferencesom.AmazonClientManager; // establece la conexion
import com.amazonaws.demo.userpreferencesom.DynamoDBManager;    // rutinas de base de datos

import android.os.AsyncTask;// ejecutar rutinas de base de datos de manera asincrona en multi hilos
import android.widget.Toast;

public class HelloAndroidActivity extends Activity {
    // EXTRA_MESSAGE es la Key del par (key,value) utilizado en el Intent.
    // El Intent es la pila para el paso de parametros entre ventanas.
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    // preparamos la llamada para los servicios de Amazon AWS ddb.
    public static AmazonClientManager clientManager = null;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Agregamos la llamada a amazon aws
        clientManager = new AmazonClientManager(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.codenvy.template.android.R.menu.main, menu);
        return true;
    }

    /**
     * Called when the user clicks the Login Button
     */
    public void login(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editUser = (EditText) findViewById(R.id.edit_user);
        EditText editPassword = (EditText) findViewById(R.id.edit_password);


        String message = editUser.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void testAmazonAWS(View view) {
        Toast.makeText(
                this,
                "Prueba de Toast.",
                Toast.LENGTH_SHORT).show();

        new DynamoDBManagerTask()
                .execute(DynamoDBManagerType.CREATE_TABLE);

    }

    /**
     * Tareas que ofrece al Activity que pueden y deben ejecutarse en paralelo
     */
    private class DynamoDBManagerTask extends
            AsyncTask<DynamoDBManagerType, Void, DynamoDBManagerTaskResult> {
        @Override
        protected DynamoDBManagerTaskResult doInBackground(
                DynamoDBManagerType... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();

            DynamoDBManagerTaskResult result = new DynamoDBManagerTaskResult();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);

            if (types[0] == DynamoDBManagerType.CREATE_TABLE) {
                if (tableStatus.length() == 0) {
                    DynamoDBManager.createTable();
                }
            } else if (types[0] == DynamoDBManagerType.INSERT_USER) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            } else if (types[0] == DynamoDBManagerType.LIST_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.getUserList();
                }
            } else if (types[0] == DynamoDBManagerType.CLEAN_UP) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.cleanUp();
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(DynamoDBManagerTaskResult result) {

            if (result.getTaskType() == DynamoDBManagerType.CREATE_TABLE) {

                if (result.getTableStatus().length() != 0) {
                    Toast.makeText(
                            HelloAndroidActivity.this,
                            "The test table already exists.\nTable Status: "
                                    + result.getTableStatus(),
                            Toast.LENGTH_LONG).show();
                }

            } else if (result.getTaskType() == DynamoDBManagerType.LIST_USERS
                    && result.getTableStatus().equalsIgnoreCase("ACTIVE")) {

                startActivity(new Intent(HelloAndroidActivity.this,
                        DisplayMessageActivity.class));

            } else if (!result.getTableStatus().equalsIgnoreCase("ACTIVE")) {

                Toast.makeText(
                        HelloAndroidActivity.this,
                        "The test table is not ready yet.\nTable Status: "
                                + result.getTableStatus(), Toast.LENGTH_LONG)
                        .show();
            } else if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == DynamoDBManagerType.INSERT_USER) {
                Toast.makeText(HelloAndroidActivity.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Catalogo de operaciones que ofrece el DynamoDBManager
     */
    private enum DynamoDBManagerType {
        GET_TABLE_STATUS, CREATE_TABLE, INSERT_USER, LIST_USERS, CLEAN_UP
    }

    /**
     * Banderas de resultados de operaciones que devuelve el DynamoDBManager
     */
    private class DynamoDBManagerTaskResult {
        private DynamoDBManagerType taskType;
        private String tableStatus;

        public DynamoDBManagerType getTaskType() {
            return taskType;
        }

        public void setTaskType(DynamoDBManagerType taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }


}

