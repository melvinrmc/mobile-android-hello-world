package com.codenvy.template.android;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lacasitaapp.bll.ProductoManager;
import com.lacasitaapp.dal.Categoria;
import com.lacasitaapp.dal.Producto;

import java.util.ArrayList;

/**
 * Created by Melvin on 3/01/2017.
 */

public class ProductosCategoriaActivity extends Activity {

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
        setContentView(R.layout.activity_productos_categoria);

        Intent intent = getIntent();
        String categoria = intent.getStringExtra(CategoriaActivity.CATEGORIA_SELECCIONADA);

        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = ProductoManager.getProductos(categoria);
        Categoria miCategoria = ProductoManager.getCategoria(categoria);

        ViewGroup layout = (ViewGroup) findViewById(R.id.productos_categoria_layout);

        TableLayout.LayoutParams paramsForRow = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(paramsForRow);
        // tableRow.setWeightSum(1);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);

        int totProductos = productos.size();
        int i = 0;

        for (Producto p : productos) {
            i++;
            TextView textView = new TextView(this);
            textView.setTextSize(24);
            textView.setText(p.getSummary());
            // textView.setLayoutParams(params);
            textView.setPadding(5, 5, 5, 5);
            textView.setBackgroundColor(Color.parseColor(miCategoria.getColor()));
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click

                    ingresarCantidad(v);
                }
            });
            tableRow.addView(textView);


            if (tableRow.getChildCount() == 2) {
                tableRow.setPadding(5, 5, 5, 5);
                layout.addView(tableRow);
                tableRow = new TableRow(this);
                tableRow.setLayoutParams(paramsForRow);
            } else if (i == totProductos) {
                // relleno
                TextView textViewRelleno = new TextView(this);
                textViewRelleno.setTextSize(16);
                textViewRelleno.setText("");
                //    textViewRelleno.setLayoutParams(params);
                tableRow.addView(textViewRelleno);
                layout.addView(tableRow);
            }

        }
    }

    private void ingresarCantidad(View view){
        EditText cantidad = (EditText) findViewById(R.id.edit_cantidad);
        cantidad.setVisibility(View.VISIBLE);
        showSoftKeyboard(cantidad);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
