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

public class ActivityEliminarActualizar extends AppCompatActivity {

    EditText txtcodigo,txtnombre2,txtapellidos2,txtedad2,txtcorreo2,txtdireccion2;
    Button btnactualizar,btneliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_actualizar);

        casteo();

        Bundle resultados = getIntent().getExtras();
        txtcodigo.setText(resultados.getString("codigo"));
        txtnombre2.setText(resultados.getString("nombre"));
        txtapellidos2.setText(resultados.getString("apellidos"));
        txtedad2.setText(resultados.getString("edad"));
        txtcorreo2.setText(resultados.getString("correo"));
        txtdireccion2.setText(resultados.getString("direccion"));

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPersona();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPersona();
            }
        });

    }

    //Metodo de casteo()
    private void casteo(){
        txtcodigo = (EditText) findViewById(R.id.txtcodigo);
        txtnombre2 = (EditText) findViewById(R.id.txtnombre2);
        txtapellidos2 = (EditText) findViewById(R.id.txtapellidos2);
        txtedad2 = (EditText) findViewById(R.id.txtedad2);
        txtcorreo2 = (EditText) findViewById(R.id.txtcorreo2);
        txtdireccion2 = (EditText) findViewById(R.id.txtdireccion2);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
    }

    //Metodos de actualizar y eliminar
    //Actualizar
    private void actualizarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String codigo = txtcodigo.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, txtnombre2.getText().toString());
        valores.put(Transacciones.apellidos, txtapellidos2.getText().toString());
        valores.put(Transacciones.edad, txtedad2.getText().toString());
        valores.put(Transacciones.correo, txtcorreo2.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion2.getText().toString());

        db.update(Transacciones.TbPersonas, valores , Transacciones.id +" = "+codigo, null);

        Toast.makeText(getApplicationContext(), "Registro #" + codigo + " actualizado"
                ,Toast.LENGTH_LONG).show();

        db.close();

        Intent intent = new Intent(getApplicationContext(),Activitylista.class);
        startActivity(intent);
    }

    //Eliminar
    private void eliminarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String codigo = txtcodigo.getText().toString();

        db.delete(Transacciones.TbPersonas,Transacciones.id +" = "+ codigo, null);

        Toast.makeText(getApplicationContext(), "Registro #" + codigo + " eliminado"
                ,Toast.LENGTH_LONG).show();
        db.close();

        Intent intent = new Intent(getApplicationContext(),Activitylista.class);
        startActivity(intent);
    }
}