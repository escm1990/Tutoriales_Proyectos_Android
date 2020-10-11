package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private CheckBox chk1, chk2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.txt_valor1);
        et2 = (EditText) findViewById(R.id.txt_valor2);
        chk1 = (CheckBox) findViewById(R.id.check_sumar);
        chk2 = (CheckBox) findViewById(R.id.check_restar);
        tv1 = (TextView) findViewById(R.id.text_resultado);

    }

    public void calcular(View view){
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int val1 = Integer.parseInt(valor1);
        int val2 = Integer.parseInt(valor2);

        String resultado = "";

        if(chk1.isChecked() == true){
            int suma = val1 +  val2;
            resultado = "La suma es: "+suma+"\n";
        }

        if(chk2.isChecked() == true){
            int resta = val1 - val2;
            resultado = resultado + "La resta es: "+resta;
        }

        tv1.setText(resultado);

    }
}