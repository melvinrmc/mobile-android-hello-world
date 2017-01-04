package com.codenvy.template.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lacasitaapp.dal.DataManager;
import com.lacasitaapp.dal.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Melvin on 3/01/2017.
 */

public class CategoriaActivity extends Activity {

    public static String CATEGORIA_SELECCIONADA = "CATEGORIA_SELECCIONADA";


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
        setContentView(R.layout.activity_categorias);
    }

    public void openCategoria(View view) {
        String idCategoria;
        String nombreCategoria;
        Button button = (Button) view;
        nombreCategoria = button.getText().toString();
        if (nombreCategoria.equalsIgnoreCase(getResources().getString(R.string.button_categoria_a))) {
            idCategoria = "A";
        } else if (nombreCategoria.equalsIgnoreCase(getResources().getString(R.string.button_categoria_b))) {
            idCategoria = "B";
        } else {
            idCategoria = "";
        }

        if (!idCategoria.equalsIgnoreCase("")) {

            Intent intent = new Intent(this, ProductosCategoriaActivity.class);

            intent.putExtra(CATEGORIA_SELECCIONADA, idCategoria);
            startActivity(intent);
        }
    }
}
