package com.example.alumno.myapplication.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alumno.myapplication.Helpers.DataBaseHelper;

import java.security.PublicKey;
import java.util.ArrayList;

public class Objeto {
    private String Nombre;
    private String Tipo;

    private int id;
    private String cliente;
    public  static DataBaseHelper dbInstance;


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public DataBaseHelper getDbInstance(Context _context){

        if (Objeto.dbInstance ==null){
            Objeto.dbInstance=new DataBaseHelper(_context);
            return Objeto.dbInstance;
        }
        return Objeto.dbInstance;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void getOne(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {DataBaseHelper.Columns._ID,
                DataBaseHelper.Columns.COLUMN_NAME_CLIENTE,
                DataBaseHelper.Columns.COLUMN_NAME_NOMBRE,
                DataBaseHelper.Columns.COLUMN_NAME_TIPO};

        String[] args = new String[] {this.getId() + ""};

        Cursor c = db.query(DataBaseHelper.OBJETO_TABLE_NAME, fields,
                null, null,  null, null, null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                this.setId(c.getInt(0));
                this.setCliente(c.getString(1));
                this.setNombre(c.getString(2));
                this.setTipo(c.getString(3));
            } while(c.moveToNext());
        }
    }

    public ArrayList<Objeto> getAll(Context _context) {
        ArrayList<Objeto> rows = new ArrayList<>();
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {DataBaseHelper.Columns._ID,
                DataBaseHelper.Columns.COLUMN_NAME_CLIENTE,
                DataBaseHelper.Columns.COLUMN_NAME_NOMBRE,
                DataBaseHelper.Columns.COLUMN_NAME_TIPO};

        String[] args = new String[] {this.getId() + ""};

        Cursor c = db.query(DataBaseHelper.OBJETO_TABLE_NAME, fields,
                null, null,  null, null,
                DataBaseHelper.Columns._ID+" DESC");

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                Objeto n = new Objeto();
                n.setId(c.getInt(0));
                n.setCliente(c.getString(1));
                n.setNombre(c.getString(2));
                n.setTipo(c.getString(3));
                rows.add(n);
            } while(c.moveToNext());
        }
        return rows;
    }

    public void setLocal(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Columns.COLUMN_NAME_CLIENTE, this.getCliente());
        values.put(DataBaseHelper.Columns.COLUMN_NAME_NOMBRE,this.getNombre());
        values.put(DataBaseHelper.Columns.COLUMN_NAME_TIPO,this.getTipo());

        String[] args = new String[] {this.getId() + ""};

        if (this.id == 0) {
            this.id = this.getAll(_context).size() + 1;
        }
        long newRowId = db.insert(DataBaseHelper.OBJETO_TABLE_NAME, null, values);
    }

}
