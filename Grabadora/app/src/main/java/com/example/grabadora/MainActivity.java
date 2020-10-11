package com.example.grabadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btn_recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_recorder = (Button) findViewById(R.id.btn_rec);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
    }

    public void Recorder(View view){
        if(grabacion == null){
            //definiendo la ruta de grabado
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            //utilizar el micrófono para grabar el audio
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            //definir el formato de salida
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //transformar el audio
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            //nombre del archivo
            grabacion.setOutputFile(archivoSalida);

            try{
                grabacion.prepare();
                grabacion.start(); //inicia a grabar el objeto preparado
            } catch(IOException e){
                Toast.makeText(this, "Error a inicar grabación", Toast.LENGTH_SHORT).show();
            }

            //cambiar la imagen del boton
            btn_recorder.setBackgroundResource(R.drawable.rec);

            Toast.makeText(this, "Grabando", Toast.LENGTH_SHORT).show();
        } else if(grabacion != null){
            //detener la grabación
            grabacion.stop();
            //finalizar la grabacion
            grabacion.release();
            //limpiando la grabación
            grabacion = null;
            //cambiar la imagen del boton
            btn_recorder.setBackgroundResource(R.drawable.stop_rec);

            Toast.makeText(this, "Grabación Finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    public void reproducir(View view){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        } catch(IOException e){
            Toast.makeText(this, "Error a reproducir grabación", Toast.LENGTH_SHORT).show();
        }

        mediaPlayer.start();
        Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }
}