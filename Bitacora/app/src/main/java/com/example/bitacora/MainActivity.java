package com.example.bitacora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText ed1;
    private String nombreArchivo = "bitacora.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.ed1);
        String archivos [] = fileList();

        if(archivoExiste(archivos,nombreArchivo)){
            try  {
                InputStreamReader archivo = new InputStreamReader(openFileInput(nombreArchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String bitacoraCompleta = null;

                while (linea != null){
                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivo.close();
                ed1.setText(bitacoraCompleta);

            }catch(IOException e){
                Toast.makeText(this, "No sé que hacer", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean archivoExiste(String archivos [], String nombreArchivo) {

        for (int i = 0; i < archivos.length; i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;

        return false;
    }

    public void guardar(View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nombreArchivo, Activity.MODE_PRIVATE));
            archivo.write(ed1.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e){

        }
        Toast.makeText(this, "Bitacora guardada exitosamente", Toast.LENGTH_SHORT).show();
    }

}