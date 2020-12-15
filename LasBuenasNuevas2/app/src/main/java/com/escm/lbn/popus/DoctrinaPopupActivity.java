package com.escm.lbn.popus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.escm.lbn.R;

public class DoctrinaPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle parameters = getIntent().getExtras();
        if(parameters != null && parameters.containsKey("layout"))
            setContentView(parameters.getInt("layout"));
        else
            setContentView(R.layout.activity_doctrina);

        DisplayMetrics medidaVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaVentana);

        int ancho = medidaVentana.widthPixels;
        int alto = medidaVentana.heightPixels;

        getWindow().setLayout((int)(ancho*0.80),(int)(alto*0.53));

    }
}