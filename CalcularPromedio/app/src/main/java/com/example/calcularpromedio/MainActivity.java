package com.example.calcularpromedio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int num1 = 5;
        int num2 = 20;
        int num3 = 15;
        int prom = 0;

        prom = (num2+num1+num3)/3;

        if(prom <=10){
            //mensaje emergente
            Toast.makeText(this, "El numero es menor o igual que 10", Toast.LENGTH_SHORT).show();
        } else if(prom >= 11){
            Toast.makeText(this, "El numero es mayor a 10", Toast.LENGTH_SHORT).show();
        }
    }
}