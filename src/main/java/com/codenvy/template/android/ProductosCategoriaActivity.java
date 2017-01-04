package com.codenvy.template.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
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

        for (Producto p : productos) {
            TextView textView = new TextView(this);
            textView.setTextSize(40);
            textView.setText(p.getNombre());

            ViewGroup layout = (ViewGroup) findViewById(R.id.productos_categoria_layout);
            layout.addView(textView);

        }
    }
}
