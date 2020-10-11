package com.example.concatenarpalabras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText p1;
    private EditText p2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = (EditText) findViewById(R.id.palabra1);
        p2 = (EditText) findViewById(R.id.palabra2);
        tv1 = (TextView) findViewById(R.id.resultado);
    }

    public void concatenar(View view){
        String palabra1 = p1.getText().toString();
        String palabra2 = p2.getText().toString();
        String resultado = palabra1+" "+palabra2;

        tv1.setText(resultado);

    }
}