package com.codenvy.template.android;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lacasitaapp.admin.User;
import com.lacasitaapp.bll.ProductoManager;
import com.lacasitaapp.bll.Usuario;
import com.lacasitaapp.dal.Producto;


public class DisplayMessageActivity extends Activity {

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HelloAndroidActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(24);
        textView.setText(HelloAndroidActivity.usuario.getNombre());

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView, 0);


    }

    @Override
    public void onBackPressed() {

        return;
    }

    public void realizarVenta(View view) {

        pDialog = new ProgressDialog(this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);
        new realizarVentaTask().execute();


    }

    private class realizarVentaTask extends
            AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            int numCategoriasCargadas = ProductoManager.loadCategorias();
            int numProductosCargados = ProductoManager.loadProductos();
            return ProductoManager.loadProductos();
        }


        @Override
        protected void onPreExecute() {

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    DisplayMessageActivity.realizarVentaTask.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }

        protected void onPostExecute(Integer size) {

            pDialog.dismiss();
            if (size == 0) {

                Toast.makeText(
                        DisplayMessageActivity.this,
                        "Lo sentimos, no se cargaron los productos.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(
                        DisplayMessageActivity.this,
                        "Se cargaron " + size.toString() + " productos.",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(DisplayMessageActivity.this,
                        CategoriaActivity.class));

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(DisplayMessageActivity.this, "Apertura de productos cancelada.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
