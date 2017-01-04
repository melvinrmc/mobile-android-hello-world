package com.codenvy.template.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lacasitaapp.bll.ProductoManager;
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
        ViewGroup layout = (ViewGroup) findViewById(R.id.productos_categoria_layout);

        TableLayout.LayoutParams paramsForRow = new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 0.33f);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(paramsForRow);
        tableRow.setWeightSum(1);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);



        for (Producto p : productos) {

            TextView textView = new TextView(this);
            textView.setTextSize(32);
            textView.setText(p.getNombre());
            textView.setLayoutParams(params);
            tableRow.addView(textView);

            if (tableRow.getChildCount() == 2) {
                layout.addView(tableRow);
                tableRow = new TableRow(this);
                tableRow.setLayoutParams(paramsForRow);
            }

        }
    }
}
