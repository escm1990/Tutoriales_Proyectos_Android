package com.example.miprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declarar componentes
    private EditText et1;
    private EditText et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //conectar la parte logica con la grafica
        //la clase R permite conectar ambas partes, es propia de Android
        et1 = (EditText) findViewById(R.id.txt_numero1);
        et2 = (EditText) findViewById(R.id.txt_numero2);
        tv1 = (TextView) findViewById(R.id.txt_resultado);
    }

    public void sumar(View view){
        //recuperar los numeros
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int numero1 = Integer.parseInt(valor1);
        int numero2 = Integer.parseInt(valor2);

        int suma = numero1 + numero2;
        //los valores se manejan como CADENAS DE TEXTO
        String resultado = String.valueOf(suma);
        tv1.setText(resultado);
    }
}