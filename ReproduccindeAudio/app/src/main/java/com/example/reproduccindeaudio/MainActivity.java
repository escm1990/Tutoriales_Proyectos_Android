package com.example.reproduccindeaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //reproducir pequeñas pistas de audio (menos de 1MB) -  SoundPool
    Button play;
    SoundPool sp;
    int sonido_de_reproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Trabajando con la clase soundpool
        play = (Button) findViewById(R.id.button_play);
        //orden de parametros: maximo de reproducciones simultaneas, tipo de stream de audio, calidad de audio (no se implementa este ultimo, pero se solicita)
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        //cargar la pista a reproducir, los parametros son CONTEXTO, RUTA DEL ARCHIVO, PRIORIDAD (este ultimo no se usa pero se requere)
        sonido_de_reproduccion = sp.load(this,R.raw.sound_short,1);
    }

    public void audioSoundPool(View view){
        //reproducir la pista
        // el parametro loop tiene 3 valores,
        /* -1, siempre se reproduce
            0, solo una vez
            1, reproduce cada vez que termina
        */
        sp.play(sonido_de_reproduccion, 1,1,1,0,0);
    }

    public void audioMediaPlayer(View view){
        //se crea un objeto MediaPlayer, se le pasa el contexto y la ruta de la pista
        MediaPlayer mp = MediaPlayer.create(this,R.raw.sound_long);
        //iniciar la reproducción de la pista
        mp.start();
    }
}