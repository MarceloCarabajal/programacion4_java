package utn.frgp.edu.appmobile_controles;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Switch switchSiNo;
    private EditText etNombre;
    private EditText etCorreo;
    private EditText etFechaNac;
    private CheckBox chkDeporte;
    private CheckBox chkMusica;
    private CheckBox chkVideojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switchSiNo = findViewById(R.id.switchSiNo);
        switchSiNo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                switchSiNo.setText("Si");
            } else {
                switchSiNo.setText("No");
            }
        });

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etFechaNac = findViewById(R.id.etFechaNacimiento);

        chkDeporte = 
    }

    public void btnEvent(View view) {
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String fechaNac = etFechaNac.getText().toString();
    }
}