package com.example.imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boton1(View view){
        Toast.makeText(this, "La geekipedia de Ernesto", Toast.LENGTH_SHORT).show();
    }

    public void boton2(View view){
        Toast.makeText(this, "Esta es una mano", Toast.LENGTH_SHORT).show();
    }
}