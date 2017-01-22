package com.codenvy.template.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lacasitaapp.bll.CarretillaManager;
import com.lacasitaapp.dal.ItemVenta;

import java.util.ArrayList;

/**
 * Created by Melvin on 21/01/2017.
 */

public class CarretillaActivity extends Activity implements AdapterView.OnItemClickListener{
    public static CarretillaManager carretillaManager = null;
    public ItemVenta itemSeleccionado;

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
        setContentView(R.layout.activity_carretilla);

        ArrayList<ItemVenta> itemsVenta = new ArrayList<ItemVenta>();
        itemsVenta = carretillaManager.getItems();

        TextView tvResume = (TextView) findViewById(R.id.tv_total_articulos);
        tvResume.setText(carretillaManager.getResumen());

        ViewGroup activityLayout = (ViewGroup) findViewById(R.id.activity_items_carretilla_layout);
        ListView layout = (ListView) findViewById(R.id.items_carretilla_layout);


        ItemCarretillaAdapter adapter;
        // Inicializamos el adapter.
        adapter = new ItemCarretillaAdapter(this, itemsVenta);
        // Asignamos el Adapter al ListView, en este punto hacemos que el
        // ListView muestre los datos que queremos.
        layout.setAdapter(adapter);
        layout.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long ID) {
        // Al hacer click sobre uno de los items del ListView mostramos los
        // datos en los TextView.
        Toast.makeText(
                CarretillaActivity.this,
                "Great! "
                        + carretillaManager.getItems().get(position).getProducto().getNombre()
                        + " por "
                        + String.valueOf( carretillaManager.getItems().get(position).getMontoItem() ),
                Toast.LENGTH_SHORT  ).show();
        itemSeleccionado = carretillaManager.getItems().get(position);


    }


 private class ItemCarretillaAdapter extends ArrayAdapter<ItemVenta> {
        private Context context;
        private ArrayList<ItemVenta> itemsCarretilla;

        public ItemCarretillaAdapter(Context context, ArrayList<ItemVenta> ics) {
            super(context, R.layout.list_item_carretilla, ics);
            // Guardamos los parámetros en variables de clase.
            this.context = context;
            this.itemsCarretilla = ics;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // En primer lugar "inflamos" una nueva vista, que será la que se
            // mostrará en la celda del ListView. Para ello primero creamos el
            // inflater, y después inflamos la vista.
            LayoutInflater inflater = LayoutInflater.from(context);
            View item = inflater.inflate(R.layout.list_item_carretilla, null);

            // A partir de la vista, recogeremos los controles que contiene para
            // poder manipularlos.
            // Recogemos el ImageView y le asignamos una foto.
            ImageView imagen = (ImageView) item.findViewById(R.id.producto_imagen);
            imagen.setImageResource(R.drawable.ic_mojarras);

            // Recogemos el TextView para mostrar el nombre y establecemos el
            // nombre.
            TextView nombre = (TextView) item.findViewById(R.id.producto_nombre);
            nombre.setText(itemsCarretilla.get(position).getProducto().getNombre());
            nombre.setTypeface(null, Typeface.BOLD);

            TextView tvCantidad = (TextView) item.findViewById(R.id.producto_cantidad);
            tvCantidad.setText( String.valueOf( itemsCarretilla.get(position).getCantidad() ) );

            TextView tvPrecio = (TextView) item.findViewById(R.id.producto_precio);
            tvPrecio.setText(String.valueOf(itemsCarretilla.get(position).getProducto().getPrecio()));

            TextView tvItemTotal = (TextView) item.findViewById(R.id.item_total);
            tvItemTotal.setText(String.valueOf(itemsCarretilla.get(position).getMontoItem()));



            // Devolvemos la vista para que se muestre en el ListView.
            return item;
        }

    }

}
