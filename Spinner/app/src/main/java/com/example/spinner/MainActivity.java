package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;
    private Spinner spinner1;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.ed1);
        et2 = (EditText) findViewById(R.id.ed2);
        tv1 = (TextView) findViewById(R.id.tv1);
        spinner1 = (Spinner) findViewById(R.id.spinner);

        String [] opciones = {"Sumar","Restar","Multiplicar","Dividir"};
        //asignar el arreglo a un spinner
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_layout, opciones);
        spinner1.setAdapter(adapter);
    }

    public void calcular(View view){
        String text1 = et1.getText().toString();
        String text2 = et2.getText().toString();
        int val1 = Integer.parseInt(text1);
        int val2 = Integer.parseInt(text2);
        int res = 0;
        String resultado = "";

        String seleccion = spinner1.getSelectedItem().toString();

        if(seleccion.equalsIgnoreCase("Sumar")){
            res = val1+val2;
        } else if(seleccion.equalsIgnoreCase("Restar")){
            res = val1-val2;
        } else if(seleccion.equalsIgnoreCase("Multiplicar")){
            res = val1*val2;
        } else if(seleccion.equalsIgnoreCase("Dividir")){
            if(val2 == 0){
                res = 0;
                Toast.makeText(this, "No es posible realizar una división entre cero", Toast.LENGTH_SHORT).show();
            } else {
                res = val1/val2;
            }
        }

        resultado = String.valueOf(res);
        tv1.setText(resultado);
    }
}