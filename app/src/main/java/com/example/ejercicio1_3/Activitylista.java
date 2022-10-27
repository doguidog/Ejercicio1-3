package com.example.ejercicio1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio1_3.configuracion.SQLiteConexion;
import com.example.ejercicio1_3.tablas.Personas;
import com.example.ejercicio1_3.tablas.Transacciones;

import java.util.ArrayList;

public class Activitylista extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView lista;
    Button btnmenu;
    ArrayList<Personas> listaPersona;
    ArrayList <String> ArregloPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activylista);

        //Casteo
        lista = (ListView) findViewById(R.id.listapersonas);
        btnmenu = (Button) findViewById(R.id.btnmenu);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        obtenerLista();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersona);
        lista.setAdapter(adp);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                obtenerPersona(i);
            }
        });

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //Metodo para obtener datos de la base de datos de cierta tabla
    private void obtenerLista(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_person = null;
        listaPersona = new ArrayList<Personas>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.TbPersonas, null);

        while (cursor.moveToNext())
        {
            list_person = new Personas();
            list_person.setId(cursor.getInt(0));
            list_person.setNombre(cursor.getString(1));
            list_person.setApellidos(cursor.getString(2));
            list_person.setEdad(cursor.getInt(3));
            list_person.setCorreo(cursor.getString(4));
            list_person.setDireccion(cursor.getString(5));
            listaPersona.add(list_person);
        }

        cursor.close();
        llenarLista();
    }

    private void obtenerPersona(int id) {
        Personas persona = listaPersona.get(id);

        Intent intent = new Intent(getApplicationContext(),ActivityEliminarActualizar.class);
        intent.putExtra("codigo", persona.getId()+"");
        intent.putExtra("nombre", persona.getNombre());
        intent.putExtra("apellidos", persona.getApellidos());
        intent.putExtra("edad", persona.getEdad()+"");
        intent.putExtra("correo", persona.getCorreo());
        intent.putExtra("direccion", persona.getDireccion());

        startActivity(intent);
    }


    //Metodo para llevar la lista
    private void llenarLista(){
        ArregloPersona = new ArrayList<String>();

        for (int i=0; i < listaPersona.size();i++){
            ArregloPersona.add(listaPersona.get(i).getId()+" - "+
                    listaPersona.get(i).getNombre()+" - "+
                    listaPersona.get(i).getApellidos()+" - "+
                    listaPersona.get(i).getEdad()+" - "+
                    listaPersona.get(i).getCorreo()+" - "+
                    listaPersona.get(i).getDireccion());
        }
    }
}