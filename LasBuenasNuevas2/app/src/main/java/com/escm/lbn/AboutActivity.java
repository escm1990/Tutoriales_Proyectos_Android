package com.escm.lbn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button botonLinkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        botonLinkedin = (Button) findViewById(R.id.btn_linkedin);

        botonLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(Constants.utl_linkedin);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                finish();
            }
        });

    }
}