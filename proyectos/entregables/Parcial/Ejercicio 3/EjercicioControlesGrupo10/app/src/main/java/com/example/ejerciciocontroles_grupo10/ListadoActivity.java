package com.example.ejerciciocontroles_grupo10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import OpenHelper.OpenHelper;

public class ListadoActivity extends AppCompatActivity {

    private ListView lvContactos;
    private ArrayList<String> listaContactos;
    private OpenHelper baseContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lvContactos = findViewById(R.id.lvContactos);

        // Los contactos salen de la misma base donde los guarda la pantalla 2
        baseContactos = new OpenHelper(this);
        listaContactos = baseContactos.devuelveContactosListado();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaContactos
        );
        lvContactos.setAdapter(adapter);

        // Muestra la línea del listado. Falta traer el resto de las columnas
        // (teléfono, dirección, estudios, intereses y recibir información)
        // con otra consulta en OpenHelper.
        lvContactos.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Contacto")
                    .setMessage(listaContactos.get(position))
                    .setPositiveButton("Aceptar", null)
                    .show();
        });
    }

    @Override
    protected void onDestroy() {
        baseContactos.cerrar();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navegacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_agregar) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_listado) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
