package com.example.ejerciciocontroles_grupo10;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import com.google.android.material.appbar.MaterialToolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Un solo formato de fecha para toda la pantalla (mostrar, leer y validar)
    private static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final int ANIO_MINIMO = 1900;

    // Letras (incluye acentos y ñ), separadas por un espacio, apóstrofe o guion.
    // Acepta "María José", "O'Connor", "Pérez-Gil". Rechaza números y símbolos.
    private static final String REGEX_NOMBRE = "^\\p{L}+([ '\\-]\\p{L}+)*$";

    // Grupos de números separados por un solo guion: "11-4567-8901" sí, "--11" o "11--45" no
    private static final String REGEX_TELEFONO = "^\\d+(-\\d+)*$";
    private static final int MIN_DIGITOS_TELEFONO = 8;
    private static final int MAX_DIGITOS_TELEFONO = 15;

    private EditText etNombre, etApellido, etTelefono, etEmail, etDireccion, etFechaNac;
    private Spinner spinnerTelefono, spinnerEmail;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // La Toolbar pasa a ser la barra de la Activity: ahí se dibuja el menú
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // También se suma el teclado (ime) a los márgenes: en horizontal, si no,
        // el teclado tapa los últimos campos y el botón CONTINUAR
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets barras = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom);
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
            intent.putExtra(MasDatosActivity.EXTRA_NOMBRE, texto(etNombre));
            intent.putExtra(MasDatosActivity.EXTRA_APELLIDO, texto(etApellido));
            intent.putExtra(MasDatosActivity.EXTRA_TELEFONO, texto(etTelefono));
            intent.putExtra(MasDatosActivity.EXTRA_TIPO_TELEFONO, spinnerTelefono.getSelectedItem().toString());
            intent.putExtra(MasDatosActivity.EXTRA_EMAIL, texto(etEmail));
            intent.putExtra(MasDatosActivity.EXTRA_TIPO_EMAIL, spinnerEmail.getSelectedItem().toString());
            intent.putExtra(MasDatosActivity.EXTRA_DIRECCION, texto(etDireccion));
            intent.putExtra(MasDatosActivity.EXTRA_FECHA_NAC, texto(etFechaNac));
            startActivity(intent);
        });
    }

    private void cargarSpinners() {
        // Las opciones salen de strings.xml asi se cambian en un solo lugar para los dos.
        // Se usan los atributos ya cargados en onCreate (antes se volvían a buscar
        // con findViewById en variables locales que tapaban a los atributos).
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tipos_contacto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTelefono.setAdapter(adapter);
        spinnerEmail.setAdapter(adapter);
    }

    private void mostrarCalendario() {
        Calendar calendario = Calendar.getInstance();

        // Si ya habia una fecha elegida el calendario abre ahi y no en hoy
        Date fechaCargada = leerFecha(texto(etFechaNac));
        if (fechaCargada != null) {
            calendario.setTime(fechaCargada);
        }

        DatePickerDialog dialogo = new DatePickerDialog(this,
                (vista, anio, mes, dia) -> {
                    // El mes viene de 0 a 11, por eso el mas uno
                    String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d",
                            dia, mes + 1, anio);
                    etFechaNac.setText(fecha);
                    etFechaNac.setError(null); // si tenía el error de "vacío", se limpia
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH));

        // Los dias posteriores a hoy quedan grisados y no se pueden tocar
        dialogo.getDatePicker().setMaxDate(System.currentTimeMillis());

        // Tampoco se puede elegir una fecha absurda hacia atrás
        Calendar minimo = Calendar.getInstance();
        minimo.set(ANIO_MINIMO, Calendar.JANUARY, 1);
        dialogo.getDatePicker().setMinDate(minimo.getTimeInMillis());

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
            // Ya estamos en esta pantalla, no hace falta navegar
            return true;
        } else if (id == R.id.menu_listado) {
            Intent intent = new Intent(this, ListadoActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Revisa los campos de arriba hacia abajo. Al primer error marca ESE campo
     * con setError (el ícono rojo y el mensaje aparecen pegados al campo),
     * le da el foco y corta. Así el usuario sabe exactamente qué corregir.
     */
    private boolean validarFormulario() {
        String nombre = texto(etNombre);
        String apellido = texto(etApellido);
        String telefono = texto(etTelefono);
        String email = texto(etEmail);
        String direccion = texto(etDireccion);
        String fechaNac = texto(etFechaNac);

        if (nombre.isEmpty()) return marcarError(etNombre, "Ingrese el nombre");
        if (!nombre.matches(REGEX_NOMBRE)) {
            return marcarError(etNombre, "El nombre solo puede tener letras");
        }

        if (apellido.isEmpty()) return marcarError(etApellido, "Ingrese el apellido");
        if (!apellido.matches(REGEX_NOMBRE)) {
            return marcarError(etApellido, "El apellido solo puede tener letras");
        }

        if (telefono.isEmpty()) return marcarError(etTelefono, "Ingrese el teléfono");
        if (!telefono.matches(REGEX_TELEFONO)) {
            return marcarError(etTelefono, "Solo números, separados por un guion");
        }
        // Se cuentan solo los dígitos (sin guiones) para el largo
        int digitos = telefono.replace("-", "").length();
        if (digitos < MIN_DIGITOS_TELEFONO || digitos > MAX_DIGITOS_TELEFONO) {
            return marcarError(etTelefono, "Debe tener entre " + MIN_DIGITOS_TELEFONO
                    + " y " + MAX_DIGITOS_TELEFONO + " números");
        }

        if (email.isEmpty()) return marcarError(etEmail, "Ingrese el email");
        // Patterns.EMAIL_ADDRESS es el validador que trae Android.
        // El chequeo anterior (contiene "@" y ".") aceptaba cosas como "@." o "a.b@"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return marcarError(etEmail, "Ingrese un email válido (ej: nombre@mail.com)");
        }

        if (direccion.isEmpty()) return marcarError(etDireccion, "Ingrese la dirección");

        if (!fechaNacimientoValida(fechaNac)) {
            // El campo de fecha no toma foco (abre el calendario), y setError solo
            // muestra el mensaje cuando el campo tiene foco. Por eso además va un Toast.
            etFechaNac.setError("Elija la fecha de nacimiento");
            Toast.makeText(this, "Elija la fecha de nacimiento", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean marcarError(EditText campo, String mensaje) {
        campo.setError(mensaje);
        campo.requestFocus();
        return false; // se devuelve false para poder hacer "return marcarError(...)"
    }

    private boolean fechaNacimientoValida(String fecha) {
        Date nacimiento = leerFecha(fecha);
        if (nacimiento == null || nacimiento.after(new Date())) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(nacimiento);
        return cal.get(Calendar.YEAR) >= ANIO_MINIMO;
    }

    /** Convierte "dd/MM/yyyy" a Date. Devuelve null si el texto no es una fecha real. */
    private Date leerFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat(FORMATO_FECHA, Locale.getDefault());
        formato.setLenient(false); // así "31/02/2000" falla en vez de pasar a marzo
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Texto de un EditText sin espacios al principio y al final, y con los espacios
     * repetidos convertidos en uno solo ("Juan   Pérez" pasa a "Juan Pérez").
     */
    private String texto(EditText campo) {
        return campo.getText().toString().trim().replaceAll("\\s+", " ");
    }
}
