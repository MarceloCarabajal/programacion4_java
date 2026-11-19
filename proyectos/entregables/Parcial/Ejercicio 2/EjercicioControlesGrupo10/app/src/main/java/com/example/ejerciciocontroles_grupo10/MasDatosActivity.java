package com.example.ejerciciocontroles_grupo10;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

    private RadioGroup rgEstudios;

    private CheckBox cbDeportes, cbMusica, cbArte, cbTecnologia;

    private Switch swRecibirInfo;
    // Queda guardado para cuando el boton Guardar lo pase a la base
    private String recibirInformacion = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_datos);

        // Muestra lo que llegó del formulario de contacto, el nivel de estudios y los intereses.
        // Falta grabar el contacto en SQLite.
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

        cbDeportes = findViewById(R.id.cbDeportes);
        cbMusica = findViewById(R.id.cbMusica);
        cbTecnologia = findViewById(R.id.cbTecnologia);
        cbArte = findViewById(R.id.cbArte);

        StringBuilder intereses = new StringBuilder();
        if (cbDeportes.isChecked()) intereses.append("Deportes ");
        if (cbMusica.isChecked()) intereses.append("Música ");
        if (cbTecnologia.isChecked()) intereses.append("Tecnología ");
        if (cbArte.isChecked()) intereses.append("Arte ");

        String textoIntereses = intereses.length() > 0 ? intereses.toString().trim() : "Ninguno";

        tvDatos.append("\nIntereses: " + textoIntereses);

        // La opción elegida se lee después con rgEstudios.getCheckedRadioButtonId().
        rgEstudios = findViewById(R.id.rgEstudios);

        // El switch arranca apagado, asi que si nadie lo toca queda en No
        swRecibirInfo = findViewById(R.id.swRecibirInfo);
        swRecibirInfo.setOnCheckedChangeListener((boton, activado) -> {
            recibirInformacion = activado ? "Sí" : "No";
            Toast.makeText(this, "Recibir información: " + recibirInformacion,
                    Toast.LENGTH_SHORT).show();
        });

        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> {
            boolean hayInteres = cbDeportes.isChecked() || cbMusica.isChecked()
                    || cbArte.isChecked() || cbTecnologia.isChecked();
            if (rgEstudios.getCheckedRadioButtonId() == -1 || !hayInteres) {
                Toast.makeText(this, "Seleccione un nivel de estudios y al menos un interés.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pendiente: insertar en SQLite el contacto, el radio elegido,
            // los intereses y recibirInformacion.
        });
    }
}
