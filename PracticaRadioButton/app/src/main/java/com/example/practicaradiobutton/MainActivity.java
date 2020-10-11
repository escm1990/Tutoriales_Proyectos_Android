package com.example.practicaradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private RadioButton rb1, rb2, rb3, rb4;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        tv1 = (TextView) findViewById(R.id.tv1);

    }

    public void calcular(View view){

        String text1 = et1.getText().toString();
        String text2 = et2.getText().toString();
        int val1 = Integer.parseInt(text1);
        int val2 = Integer.parseInt(text2);
        int res = 0;
        String resultado = "";

        if(rb1.isChecked()){
            res = val1+val2;
        } else if(rb2.isChecked()){
            res = val1-val2;
        } else if(rb3.isChecked()){
            res = val1*val2;
        } else if(rb4.isChecked()){
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