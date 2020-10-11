package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.edmail);

        //Trabajar con sharedPreferences
        SharedPreferences sp = getSharedPreferences("datos", Context.MODE_PRIVATE);
        //Colocar lo que encontró en el idText
        ed1.setText(sp.getString("mail",""));
    }

    public void guardar(View view){
        SharedPreferences sp2 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor objEditor = sp2.edit(); //vamos a editar el archivo SharedPreferencias
        objEditor.putString("mail",ed1.getText().toString()); //escribiendo el valor en el editor
        objEditor.commit(); // guardar en el editor

        //CERRAR EL ACTIVITY ACTUAL
        finish();
    }
}