package com.codenvy.template.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;

import android.view.View;
import android.content.Intent;
import android.widget.EditText;


import com.amazonaws.demo.userpreferencesom.DynamoDBManager;    // rutinas de base de datos
import com.lacasitaapp.admin.User;
import com.lacasitaapp.bll.Usuario;
import com.lacasitaapp.dal.DataManager;

import android.os.AsyncTask;// ejecutar rutinas de manera asincrona en multi hilos
import android.widget.Toast;

public class HelloAndroidActivity extends Activity {
    // EXTRA_MESSAGE es la Key del par (key,value) utilizado en el Intent.
    // El Intent es la pila para el paso de parametros entre ventanas.
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private ProgressDialog pDialog;
    public static User usuario;

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

        // Enviamos el Contexto a manejador de datos.
        // Es la única vez en toda la App que realizamos esto.
        new DataManager(this);
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

        if (editUser.getText().toString().trim().equals("")) {
            editUser.setError("Usuario es requerido");
        } else {

            usuario = new User();
            usuario.setUsuario(editUser.getText().toString().trim());
            usuario.setPassword(editPassword.getText().toString().trim());

            pDialog = new ProgressDialog(this);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage("Procesando...");
            pDialog.setCancelable(true);
            pDialog.setMax(100);
            new LoginTask().execute(usuario);
        }
    }

    //TODO: Eliminar testAmazonAWS
    public void testAmazonAWS(View view) {
        Toast.makeText(
                this,
                "Prueba de Toast.",
                Toast.LENGTH_SHORT).show();

        new DynamoDBManagerTask()
                .execute(DynamoDBManagerType.CREATE_TABLE);

    }

    private enum UsuarioActividadTipo {LOGIN}

    private class LoginTask extends
            AsyncTask<User, Void, User> {

        @Override
        protected User doInBackground(User... usuarios) {
            User usuario = usuarios[0];
            User usuarioEnBDD = Usuario.doLogin(usuario.getUsuario(), usuario.getPassword());
            return usuarioEnBDD;
        }


        @Override
        protected void onPreExecute() {

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    LoginTask.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }

        protected void onPostExecute(User usuarioEnBDD) {

            pDialog.dismiss();
            if (usuarioEnBDD == null) {

                Toast.makeText(
                        HelloAndroidActivity.this,
                        "Lo sentimos, tus credenciales no son válidas.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(
                        HelloAndroidActivity.this,
                        "Bienvenido " + usuarioEnBDD.getNombre(),
                        Toast.LENGTH_SHORT).show();
                HelloAndroidActivity.usuario = usuarioEnBDD;
                startActivity(new Intent(HelloAndroidActivity.this,
                        DisplayMessageActivity.class));
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(HelloAndroidActivity.this, "Login cancelado!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //TODO: eliminar clase UsuarioActividadResultado
    private class UsuarioActividadResultado {

        private UsuarioActividadTipo usuarioActividadTipo;
        private String resultado;

        public UsuarioActividadTipo getUsuarioActividadTipo() {
            return usuarioActividadTipo;
        }

        public void setUsuarioActividadTipo(UsuarioActividadTipo usuarioActividadTipo) {
            this.usuarioActividadTipo = usuarioActividadTipo;
        }

        public String getResultado() {
            return resultado;
        }

        public void setResultado(String resultado) {
            this.resultado = resultado;
        }

    }

    /**
     * Tareas que ofrece al Activity que pueden y deben ejecutarse en paralelo
     * TODO: limpiar Actividad Principal de la implementacion explicita de helloAndroidActivity.
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
            } else if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == DynamoDBManagerType.DO_USER_LOGIN) {
                Toast.makeText(HelloAndroidActivity.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        }
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

    /**
     * Catalogo de operaciones que ofrece el DynamoDBManager
     */

    private enum DynamoDBManagerType {
        GET_TABLE_STATUS, CREATE_TABLE, INSERT_USER, LIST_USERS, CLEAN_UP, DO_USER_LOGIN
    }
}

