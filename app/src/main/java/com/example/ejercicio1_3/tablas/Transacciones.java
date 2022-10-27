package com.example.ejercicio1_3.tablas;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "ejercicio3";

    /* Creacion de las tablas de la DB*/
    public static final String TbPersonas = "personas";

    /* Creacion de los campos de la tabla*/
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    //DDL: Crea,elimina Tablas
    public static final String CTPersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombre TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String GetPersonas = "SELECT * FROM " +Transacciones.TbPersonas;

    public static final String DropTPersonas = "DROP TABLE IF EXISTS personas";
}
