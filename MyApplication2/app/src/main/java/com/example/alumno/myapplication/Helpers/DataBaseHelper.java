package com.example.alumno.myapplication.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.alumno.myapplication.Conf.Settings;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static class Columns implements BaseColumns {
        public static final String COLUMN_NAME_CLIENTE = "cliente";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_TIPO = "tipo";

    }

    public static final String OBJETO_TABLE_NAME = "Reciclaje_Objeto";
    private static final String OBJETO_SQL_CREATE_TABLE =
            "CREATE TABLE " + OBJETO_TABLE_NAME + " (" +
                    Columns._ID + " INTEGER PRIMARY KEY," +
                    Columns.COLUMN_NAME_CLIENTE + " TEXT, " +
                    Columns.COLUMN_NAME_NOMBRE + " TEXT, " +
                    Columns.COLUMN_NAME_TIPO + " TEXT)";
    private static final String OBJETO_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + OBJETO_TABLE_NAME;



    public DataBaseHelper(Context context) {
        super(context, Settings.DATABASE_NAME, null, Settings.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(OBJETO_SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OBJETO_SQL_DELETE_ENTRIES);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}