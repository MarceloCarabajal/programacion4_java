package com.example.ejerciciocontroles_grupo10;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import OpenHelper.OpenHelper;

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

    private OpenHelper baseContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_datos);

        // Se abre al entrar para que la tabla ya exista cuando GUARDAR inserte el contacto
        baseContactos = new OpenHelper(this);
        baseContactos.abrir();

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
            int idRadioSeleccionado = rgEstudios.getCheckedRadioButtonId();

            if (idRadioSeleccionado == -1) {
                Toast.makeText(this, "Seleccione un nivel de estudios", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!hayInteres) {
                Toast.makeText(this, "Seleccione al menos un interés", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton rbSeleccionado = findViewById(idRadioSeleccionado);
            String nivelEstudio = rbSeleccionado.getText().toString();


            StringBuilder interesesActualizados = new StringBuilder();
            if (cbDeportes.isChecked()) interesesActualizados.append("Deportes, ");
            if (cbMusica.isChecked()) interesesActualizados.append("Música, ");
            if (cbTecnologia.isChecked()) interesesActualizados.append("Tecnología, ");
            if (cbArte.isChecked()) interesesActualizados.append("Arte, ");
            String interesesFinal = interesesActualizados.substring(0, interesesActualizados.length() - 2);


            tvDatos.setText("Nombre: " + nombre + "\n"
                    + "Apellido: " + apellido + "\n"
                    + "Teléfono: " + telefono + " (" + tipoTelefono + ")\n"
                    + "Email: " + email + " (" + tipoEmail + ")\n"
                    + "Dirección: " + direccion + "\n"
                    + "Fecha de nacimiento: " + fechaNac + "\n"
                    + "Nivel de estudios: " + nivelEstudio + "\n"
                    + "Intereses: " + interesesFinal + "\n"
                    + "Recibir información: " + recibirInformacion);

            Toast.makeText(this, "Datos validados y listos para guardar", Toast.LENGTH_SHORT).show();

            // Pendiente para SQLite: insertar (nombre, apellido, telefono, tipoTelefono,
            // email, tipoEmail, direccion, fechaNac, nivelEstudio, interesesFinal, recibirInformacion)
        });
    }

    @Override
    protected void onDestroy() {
        baseContactos.cerrar();
        super.onDestroy();
    }
}
