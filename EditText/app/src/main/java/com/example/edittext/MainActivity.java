package com.example.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.txt_nombre);
        et2 = (EditText) findViewById(R.id.txt_password);
    }

    public void registrar(View view){
        String nombre = et1.getText().toString();
        String password = et2.getText().toString();

        if(nombre.length() == 0){
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
        }

        if(password.length() == 0){
            Toast.makeText(this, "Debes ingresar un password", Toast.LENGTH_SHORT).show();
        }

        if(password.length() != 0 && nombre.length() != 0){
            Toast.makeText(this, "Registro en proceso", Toast.LENGTH_SHORT).show();
        }
    }
}