package com.example.reproductordeaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button play_pause, btn_repetir;
    MediaPlayer mp = null;
    ImageView iv;

    //variables para determinar la repetición y la posición de una canción
    int repetir = 2, posicion = 0;
    //aca se almacenarán las pistas de audio
    MediaPlayer vectorMP [] = new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = (Button) findViewById(R.id.btn_play);
        btn_repetir = (Button) findViewById(R.id.btn_repeat);
        iv = (ImageView) findViewById(R.id.imageView);

        crearListaReproduccion();
    }

    public void playPause(View view){
        //saber si una pista de audio se está reproduciendo
        if (vectorMP[posicion].isPlaying()){
            vectorMP[posicion].pause(); //si la pista se está reproduciendo, la canción se tiene que pausar
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        }else{
            vectorMP[posicion].start(); //si la pista no se está reproduciendo, la canción se tiene que iniciar
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View view){
        // si no es vacío, es porque hay una reproducción en curso
        if(vectorMP[posicion] != null){
            vectorMP[posicion].stop();

            crearListaReproduccion();
            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();

        }
    }

    public void repeat(View view){
        //saber si una pista de audio se está reproduciendo
        if (repetir == 1){
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No Repeat", Toast.LENGTH_SHORT).show();
            vectorMP[posicion].setLooping(false); //impedir que se repita la pista
            repetir = 2;
        }else{
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repeat", Toast.LENGTH_SHORT).show();
            vectorMP[posicion].setLooping(true); //impedir que se repita la pista
            repetir = 1;
        }
    }

    public void next(View view){
        //verifique si el indice está en la posición cero
        if(posicion < vectorMP.length -1){
            //verifica si el reproductor se está reproduciendo
            if(vectorMP[posicion].isPlaying()){
                vectorMP[posicion].stop();
                posicion++;
                vectorMP[posicion].start();
                //cambio de portada
                switch (posicion){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            } else {
                posicion++;
                //cambio de portada
                switch (posicion){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            }
        } else {
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void preview(View view){
        if(posicion>=1){
            //verificar si hay una reproducción en curso
            if(vectorMP[posicion].isPlaying()){
                vectorMP[posicion].stop();
                crearListaReproduccion(); //puede que se pierda la canción que estaba reproduciéndose, por eso se crea la lista, para preservar esa posición
                posicion--;

                //cambio de portada
                switch (posicion){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }

                vectorMP[posicion].start();
            } else {
                posicion--;

                //cambio de portada
                switch (posicion){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            }

        }else{
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void crearListaReproduccion(){
        vectorMP[0] = MediaPlayer.create(this, R.raw.race);
        vectorMP[1] = MediaPlayer.create(this, R.raw.sound);
        vectorMP[2] = MediaPlayer.create(this, R.raw.tea);
    }
}