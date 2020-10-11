package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_precio = (EditText) findViewById(R.id.et_precio);

    }

    public void registrar(View view){
        //EL SEGUNDO PARAMETRO ES EL NOMBRE DE LA BASE DE DATOS
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        //abrir base de datos en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {

            //creando el contenedor con los datos a insertar
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            //insertando en la base de datos, en la tabla articulos
            BaseDeDatos.insert("articulos",null,registro);

            //cerrando conexion a la base de datos
            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe llenar todos los campos.", Toast.LENGTH_SHORT).show();
        }

        et_codigo.setText("");
        et_descripcion.setText("");
        et_precio.setText("");

    }

    public void buscar(View view){
        //EL SEGUNDO PARAMETRO ES EL NOMBRE DE LA BASE DE DATOS
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        //abrir base de datos en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){

            //objeto cursor, ayudará al seleccionar registros
            Cursor fila = BaseDeDatos.rawQuery("select descripcion, precio from articulos where codigo = "+codigo,null);
            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0)); //porque el indice del select es acorde a la columna descripción
                et_precio.setText(fila.getString(1)); //porque el indice del select es acorde a la columna precio
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el producto.", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }

        }else{
            Toast.makeText(this, "Debe introducir el código del articulo", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(View view){
        //EL SEGUNDO PARAMETRO ES EL NOMBRE DE LA BASE DE DATOS
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        //abrir base de datos en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){

            //eliminar un valor en sql, se utiliza un metodo que devuelve un entero con los registros eliminados
            //se le pasa el nombre de la tabla y la clausula where con los parámetros
            int cantidad = BaseDeDatos.delete("articulos","codigo="+codigo,null);
            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidad==1){
                Toast.makeText(this, "Articulo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debe introducir el código del articulo", Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }

    public void modificar(View view){

        //EL SEGUNDO PARAMETRO ES EL NOMBRE DE LA BASE DE DATOS
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        //abrir base de datos en modo lectura-escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //guardando todos los valores antes de modificar
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {

            //creando el contenedor con los datos a insertar
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            //actualizando en la base de datos, en la tabla articulos
            int cantidad = BaseDeDatos.update("articulos",registro,"codigo="+codigo,null);

            //cerrando conexion a la base de datos
            BaseDeDatos.close();

            if(cantidad==1){
                Toast.makeText(this, "Articulo actualizado exitosamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

        }else{
            Toast.makeText(this, "Debe llenar todos los campos.", Toast.LENGTH_SHORT).show();
        }

    }
}