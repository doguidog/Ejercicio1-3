package com.example.ejercicio1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio1_3.configuracion.SQLiteConexion;
import com.example.ejercicio1_3.tablas.Transacciones;

public class ActivityAgregar extends AppCompatActivity {

    EditText txtnombre,txtapellidos,txtedad,txtcorreo,txtdireccion;
    Button btninsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        casteo();

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Agregar();
            }
        });

    }
    //Metodo general
    private void casteo(){
        txtnombre = (EditText) findViewById(R.id.txtnombre);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        txtedad = (EditText) findViewById(R.id.txtedad);
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtdireccion = (EditText) findViewById(R.id.txtdireccion);
        btninsertar = (Button) findViewById(R.id.btninsertar);
    }

    //Metodo agregar

    private void Agregar(){

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombre, txtnombre.getText().toString());
        valores.put(Transacciones.apellidos, txtapellidos.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.correo, txtcorreo.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        Long resultado = db.insert(Transacciones.TbPersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro #"+ resultado.toString() + " Ingresado."
                , Toast.LENGTH_SHORT).show();

        db.close();

        limpiarCampos();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    private void limpiarCampos() {
        txtnombre.setText("");
        txtapellidos.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");
    }










}