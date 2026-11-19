package OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OpenHelper extends SQLiteOpenHelper {

    public static final String NombreBase = "BDContactos";
    public static final int VersionBase = 1;

    public static final String ContactosTabla = "contactos";
    public static final String ContactosColumnaID = "_ID";
    public static final String ContactosColumnaNombre = "nombre";
    public static final String ContactosColumnaApellido = "apellido";
    public static final String ContactosColumnaTelefono = "telefono";
    public static final String ContactosColumnaTipoTelefono = "tipoTelefono";
    public static final String ContactosColumnaEmail = "email";
    public static final String ContactosColumnaTipoEmail = "tipoEmail";
    public static final String ContactosColumnaDireccion = "direccion";
    public static final String ContactosColumnaFechaNac = "fechaNac";
    public static final String ContactosColumnaNivelEstudio = "nivelEstudio";
    public static final String ContactosColumnaIntereses = "intereses";
    public static final String ContactosColumnaRecibirInfo = "recibirInfo";

    // Una columna por cada dato que junta GUARDAR, asi el insert y el listado usan los mismos nombres
    public static final String ContactosCreacionTabla =
            "CREATE TABLE IF NOT EXISTS " + ContactosTabla + "("
            + ContactosColumnaID + " integer primary key autoincrement, "
            + ContactosColumnaNombre + " text, "
            + ContactosColumnaApellido + " text, "
            + ContactosColumnaTelefono + " text, "
            + ContactosColumnaTipoTelefono + " text, "
            + ContactosColumnaEmail + " text, "
            + ContactosColumnaTipoEmail + " text, "
            + ContactosColumnaDireccion + " text, "
            + ContactosColumnaFechaNac + " text, "
            + ContactosColumnaNivelEstudio + " text, "
            + ContactosColumnaIntereses + " text, "
            + ContactosColumnaRecibirInfo + " text);";

    // El nombre y la version quedan fijos aca para que cada pantalla lo abra solo con el contexto
    public OpenHelper(@Nullable Context context) {
        super(context, NombreBase, null, VersionBase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactosCreacionTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionVieja, int versionNueva) {
        // Mientras probamos se borra y se vuelve a crear, todavia no hay contactos que cuidar
        db.execSQL("DROP TABLE IF EXISTS " + ContactosTabla);
        onCreate(db);
    }

    public void abrir() {
        this.getWritableDatabase();
    }

    public void cerrar() {
        this.close();
    }

    // El listado solo muestra nombre, apellido y mail,
    // por eso se piden esas tres columnas y no la fila entera
    public ArrayList<String> devuelveContactosListado() {
        ArrayList<String> listaContactos = new ArrayList<String>();
        Cursor mcursor = this.getReadableDatabase().query(ContactosTabla,
                new String[]{ContactosColumnaNombre, ContactosColumnaApellido,
                        ContactosColumnaEmail},
                null, null, null, null, ContactosColumnaApellido);

        if (mcursor.moveToFirst()) {
            do {
                listaContactos.add(mcursor.getString(0) + " "
                        + mcursor.getString(1) + " - " + mcursor.getString(2));
            } while (mcursor.moveToNext());
        }
        mcursor.close();
        return listaContactos;
    }
}
