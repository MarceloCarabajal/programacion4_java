package com.example.ejerciciocontroles_grupo10;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etTelefono, etEmail, etDireccion, etFechaNac;
    private Spinner spinnerTelefono, spinnerEmail;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNac = findViewById(R.id.etFechaNac);
        spinnerTelefono = findViewById(R.id.spinnerTelefono);
        spinnerEmail = findViewById(R.id.spinnerEmail);
        btnContinuar = findViewById(R.id.btnContinuar);

        cargarSpinners();

        etFechaNac.setOnClickListener(v -> mostrarCalendario());

        // si el formulario es válido, pasa los datos a la pantalla siguiente
        btnContinuar.setOnClickListener(v -> {
            if (!validarFormulario()) {
                return;
            }

            Intent intent = new Intent(this, MasDatosActivity.class);
            intent.putExtra(MasDatosActivity.EXTRA_NOMBRE, etNombre.getText().toString().trim());
            intent.putExtra(MasDatosActivity.EXTRA_APELLIDO, etApellido.getText().toString().trim());
            intent.putExtra(MasDatosActivity.EXTRA_TELEFONO, etTelefono.getText().toString().trim());
            intent.putExtra(MasDatosActivity.EXTRA_TIPO_TELEFONO, spinnerTelefono.getSelectedItem().toString());
            intent.putExtra(MasDatosActivity.EXTRA_EMAIL, etEmail.getText().toString().trim());
            intent.putExtra(MasDatosActivity.EXTRA_TIPO_EMAIL, spinnerEmail.getSelectedItem().toString());
            intent.putExtra(MasDatosActivity.EXTRA_DIRECCION, etDireccion.getText().toString().trim());
            intent.putExtra(MasDatosActivity.EXTRA_FECHA_NAC, etFechaNac.getText().toString().trim());
            startActivity(intent);
        });
    }

    private void cargarSpinners() {
        Spinner spinnerTelefono = findViewById(R.id.spinnerTelefono);
        Spinner spinnerEmail = findViewById(R.id.spinnerEmail);

        // Las opciones salen de strings.xml asi se cambian en un solo lugar para los dos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tipos_contacto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTelefono.setAdapter(adapter);
        spinnerEmail.setAdapter(adapter);
    }

    private void mostrarCalendario() {
        Calendar calendario = Calendar.getInstance();

        // Si ya habia una fecha elegida el calendario abre ahi y no en hoy
        String fechaCargada = etFechaNac.getText().toString().trim();
        if (fechaNacimientoValida(fechaCargada)) {
            try {
                calendario.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fechaCargada));
            } catch (ParseException e) {
                calendario = Calendar.getInstance();
            }
        }

        DatePickerDialog dialogo = new DatePickerDialog(this,
                (vista, anio, mes, dia) -> {
                    // El mes viene de 0 a 11, por eso el mas uno
                    String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d",
                            dia, mes + 1, anio);
                    etFechaNac.setText(fecha);
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH));

        // Los dias posteriores a hoy quedan grisados y no se pueden tocar
        dialogo.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialogo.show();
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
            return true;
        } else if (id == R.id.menu_listado) {
           // Intent intent = new Intent(this, ListadoActivity.class);
           // startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean validarFormulario() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String fechaNac = etFechaNac.getText().toString().trim();

        // Campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || direccion.isEmpty() || fechaNac.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Que no haya números en Nombre o Apellido
        if (nombre.matches(".*\\d.*") || apellido.matches(".*\\d.*")) {
            Toast.makeText(this, "El nombre y apellido no deben contener números", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Teléfono (solo números y guiones)
        if (!telefono.matches("[0-9\\-]+")) {
            Toast.makeText(this, "Ingrese un número de teléfono válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Formato de Email
        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this,"Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Fecha de nacimiento real, en formato dd/MM/yyyy, y no posterior a hoy
        if (!fechaNacimientoValida(fechaNac)) {
            Toast.makeText(this, "Ingrese una fecha de nacimiento válida (dd/MM/aaaa)", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean fechaNacimientoValida(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        try {
            Date nacimiento = formato.parse(fecha);
            return nacimiento != null && !nacimiento.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }
}