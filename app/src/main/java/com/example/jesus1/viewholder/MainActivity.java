package com.example.jesus1.viewholder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    AdaptadorObjeto AdapObject;
    ArrayList<String> lista = new ArrayList<String>();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CargarLista();
        AdapObject = new AdaptadorObjeto(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(AdapObject);


    }

    private void CargarLista() {

        lista.add("Elbuskador1");
        lista.add("Master");
        lista.add("Chesa");
        lista.add("Shin");
        lista.add("Zaiko");
        lista.add("Erosekai");
        lista.add("Susej");
    }

    private class AdaptadorObjeto extends ArrayAdapter{

        Activity context;
        int layout;
        ArrayList<String> Lista;
        // Propiedades

        public AdaptadorObjeto(Activity context) {
            // parametros que le pasamos en el constructor
            super(context,R.layout.main,lista);
            // los que son añadidos despues
            this.context = context;
            this.layout = R.layout.main;
            this.Lista = lista;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent); --> No es necesario
            View item = convertView;
            // En este paso hay que construir una static class llamada VIEWHOLDER
            // que contendrá los elementos que conforman el LAYOUT que vamos a INFLAR,
            // o en otras palabras, el que se va a mostrar una vez que sea adaptado
            // al ListView
            ViewHolder Holder;

            if (item == null)
            // Si el ITEM está VACIO...
            // Significa que si aún NO HA SIDO RELLENADO con los PARÁMETROS del VIEWHOLDER
            {
                // Si es nulo procedemos a inflarlo a través del LAYOUTINFLATER.
                LayoutInflater inflate;
                // CREAMOS un layoutinflater, cuyo valor a inflar será...
                inflate = context.getLayoutInflater();
                // CON EL CONTEXTO (activity) de nuestra actividad, a través del método
                // GETLAYOUTINFLATER.
                item = inflate.inflate(layout,null);
                // Tras eso, INFLAMOS finalmente el item, para ASIGNAR los CAMPOS del VIEWHOLDER
                // a los del LAYOUT INFLADO.
                Holder = new ViewHolder();
                Holder.txt_view = (TextView) item.findViewById(R.id.txt_view);
                // Le añadimos el valor de la lista de la POSICION ESCOGIDA
                item.setTag(Holder);
            }
            else {
                // En el caso que el item ya LLENO
                Holder = (ViewHolder) convertView.getTag();
                //  El HOLDER se LLENARÁ con el ITEM SELECCIONADO
            }
            // Una vez resuelto lo del item, su inflacion y conexion con el HOLDER, nos disponemos
            // a DAR VALORES a este con los campos del LAYOUT.
            Holder.txt_view.setText(lista.get(position).toString());

            // DEVOLVEMOS EL ITEM
           return item;
        }
    }

    private static class ViewHolder {
        // Debes añadirle el static
        TextView txt_view;
        // No tienen que ser necesariamente el mismo nombre que los que tienen en el layout
        // pero es preferible para evitar errores.
    }
}
