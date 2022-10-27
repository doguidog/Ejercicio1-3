package com.example.ejercicio1_3.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ejercicio1_3.tablas.Transacciones;

public class SQLiteConexion extends SQLiteOpenHelper {
    //Constructor de clase para la base de datos

    public SQLiteConexion(Context context,
                          String dbname,
                          SQLiteDatabase.CursorFactory factory,
                          int version){

        super(context,dbname,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de las tablas de la db desde el DDL de la clase Transacciones
        db.execSQL(Transacciones.CTPersonas); //CREANDO LA TABLA DE PERSONAS
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Eliminamos la data y las tablas de la aplicacion desde el DDL de la clase Transacciones
        db.execSQL(Transacciones.DropTPersonas);
        onCreate(db);
    }
}
