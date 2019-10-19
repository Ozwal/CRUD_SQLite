package tinoco.castro.aplicacioncrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOContacto {
    SQLiteDatabase sqLiteDatabase;
    Context contexto;


    public DAOContacto(Context contexto) {
        this.contexto = contexto;
        sqLiteDatabase = new BaseDeDatos(contexto).getWritableDatabase();
    }

    public long insert(Contacto contacto) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BaseDeDatos.COLUMNS_NAME_CONTACTO[1], contacto.getUsuario());
        contentValues.put(BaseDeDatos.COLUMNS_NAME_CONTACTO[2], contacto.getEmail());
        contentValues.put(BaseDeDatos.COLUMNS_NAME_CONTACTO[3], contacto.getTelefono());
        contentValues.put(BaseDeDatos.COLUMNS_NAME_CONTACTO[4], contacto.getFechaNacimiento());

        return sqLiteDatabase.insert(BaseDeDatos.TABLE_NAME_CONTACTOS, null, contentValues);
    }

    public boolean delete (int id){
        return sqLiteDatabase.delete(BaseDeDatos.TABLE_NAME_CONTACTOS,
                "_id=?",new String[]{Integer.toString(id)}) > 0;
    }

    public boolean update(int id, ContentValues contentValues){
        return sqLiteDatabase.update(BaseDeDatos.TABLE_NAME_CONTACTOS, contentValues,
                "_id="+id,null) > 0;
    }

    public Contacto contactoPorID(int id){
        Cursor cursor = sqLiteDatabase.query(BaseDeDatos.TABLE_NAME_CONTACTOS,
                null,"_id="+id,null,null,null, null);

        Contacto contacto = null;

        if (cursor.moveToFirst()) {
            do {
                contacto =
                        new Contacto(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getString(3), cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return contacto;
    }

    public Cursor getAllCursor() {
        Cursor cursor = sqLiteDatabase.query(BaseDeDatos.TABLE_NAME_CONTACTOS,
                BaseDeDatos.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Contacto contactoPorUsuario(String usuario){
        Cursor cursor = sqLiteDatabase.query(BaseDeDatos.TABLE_NAME_CONTACTOS,
                null,"_usuario=?",new String[]{usuario},null,null, null);
        Contacto contacto = null;

        if (cursor.moveToFirst()) {
            do {
                contacto =
                        new Contacto(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getString(3), cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return contacto;
    }
}
