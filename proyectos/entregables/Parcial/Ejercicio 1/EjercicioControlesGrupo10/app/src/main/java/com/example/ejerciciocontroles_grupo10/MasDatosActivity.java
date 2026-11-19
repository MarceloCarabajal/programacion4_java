package com.example.ejerciciocontroles_grupo10;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MasDatosActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_APELLIDO = "apellido";
    public static final String EXTRA_TELEFONO = "telefono";
    public static final String EXTRA_TIPO_TELEFONO = "tipoTelefono";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_TIPO_EMAIL = "tipoEmail";
    public static final String EXTRA_DIRECCION = "direccion";
    public static final String EXTRA_FECHA_NAC = "fechaNac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_datos);

        // Muestra lo que llegó del formulario de contacto.
        // Los controles de esta pantalla son del ejercicio siguiente.
        String nombre = getIntent().getStringExtra(EXTRA_NOMBRE);
        String apellido = getIntent().getStringExtra(EXTRA_APELLIDO);
        String telefono = getIntent().getStringExtra(EXTRA_TELEFONO);
        String tipoTelefono = getIntent().getStringExtra(EXTRA_TIPO_TELEFONO);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        String tipoEmail = getIntent().getStringExtra(EXTRA_TIPO_EMAIL);
        String direccion = getIntent().getStringExtra(EXTRA_DIRECCION);
        String fechaNac = getIntent().getStringExtra(EXTRA_FECHA_NAC);

        TextView tvDatos = findViewById(R.id.tvDatosRecibidos);
        tvDatos.setText("Nombre: " + nombre + "\n"
                + "Apellido: " + apellido + "\n"
                + "Teléfono: " + telefono + " (" + tipoTelefono + ")\n"
                + "Email: " + email + " (" + tipoEmail + ")\n"
                + "Dirección: " + direccion + "\n"
                + "Fecha de nacimiento: " + fechaNac);
    }
}
