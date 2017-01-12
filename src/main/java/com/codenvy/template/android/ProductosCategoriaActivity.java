package com.codenvy.template.android;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.lacasitaapp.bll.ProductoManager;
import com.lacasitaapp.dal.Categoria;
import com.lacasitaapp.dal.Producto;

import java.util.ArrayList;

/**
 * Created by Melvin on 3/01/2017.
 */

public class ProductosCategoriaActivity extends Activity implements AdapterView.OnItemClickListener {

    public static ArrayList<Producto> productos;
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

        productos = new ArrayList<Producto>();
        productos = ProductoManager.getProductos(categoria);
        Categoria miCategoria = ProductoManager.getCategoria(categoria);
        ViewGroup  activityLayout = (ViewGroup) findViewById(R.id.activity_productos_categoria_layout);
        activityLayout.setBackgroundColor(Color.parseColor(miCategoria.getColor()));
        ListView layout = (ListView) findViewById(R.id.productos_categoria_layout);


        ProductosAdapter adapter;
// Inicializamos el adapter.
        adapter = new ProductosAdapter(this, productos);
// Asignamos el Adapter al ListView, en este punto hacemos que el
// ListView muestre los datos que queremos.
        layout.setAdapter(adapter);
        layout.setOnItemClickListener(this);


        /*

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

        } */
    }
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long ID) {
        // Al hacer click sobre uno de los items del ListView mostramos los
        // datos en los TextView.
        Toast.makeText(
                ProductosCategoriaActivity.this,
                "Seleccione cuantas " + productos.get(position).getUnidadMedida() + " de " + productos.get(position).getNombre(),
                Toast.LENGTH_SHORT).show();

        ingresarCantidad(view);

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

    private class ProductosAdapter extends ArrayAdapter<Producto> {
        private Context context;
        private ArrayList<Producto> productos;

        public ProductosAdapter(Context context, ArrayList<Producto> productos) {
            super(context, R.layout.list_item_producto, productos);
            // Guardamos los parámetros en variables de clase.
            this.context = context;
            this.productos = productos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // En primer lugar "inflamos" una nueva vista, que será la que se
            // mostrará en la celda del ListView. Para ello primero creamos el
            // inflater, y después inflamos la vista.
            LayoutInflater inflater = LayoutInflater.from(context);
            View item = inflater.inflate(R.layout.list_item_producto, null);

            // A partir de la vista, recogeremos los controles que contiene para
            // poder manipularlos.
            // Recogemos el ImageView y le asignamos una foto.
            ImageView imagen = (ImageView) item.findViewById(R.id.producto_imagen);
            imagen.setImageResource(R.drawable.ic_mojarras);

            // Recogemos el TextView para mostrar el nombre y establecemos el
            // nombre.
            TextView nombre = (TextView) item.findViewById(R.id.producto_nombre);
            nombre.setText(productos.get(position).getNombre());
            nombre.setTypeface(null, Typeface.BOLD);

            TextView unidadMedida = (TextView) item.findViewById(R.id.producto_unidad_medida);
            unidadMedida.setText(productos.get(position).getUnidadMedida());

            TextView precio = (TextView) item.findViewById(R.id.producto_precio);
            precio.setText( String.valueOf( productos.get(position).getPrecio() ));

            // Recogemos el TextView para mostrar la unidad de medida de celda y lo
            // establecemos.
            // TextView numCelda = (TextView) item.findViewById(R.id.producto_unidad_medida);
            // numCelda.setText(String.valueOf(position));

            // Devolvemos la vista para que se muestre en el ListView.
            return item;
        }

    }

}
