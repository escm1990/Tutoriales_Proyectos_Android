package com.escm.lbn.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.escm.lbn.helpers.Constants;
import com.escm.lbn.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    Button botonLinkedin;

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerAcercaDe);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewAbout); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(AboutActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(AboutActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(AboutActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(AboutActivity.this,FavoritosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.nav_facebook:
                        Uri uri = Uri.parse(Constants.url_facebook);
                        Intent intent3 = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_contacto:
                        Intent intent7 = new Intent(AboutActivity.this,SendMailActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(AboutActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return false;
            }
        });

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

        //probar crashlitycs
        Button crashButton = (Button) findViewById(R.id.buttonCrash);
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutAbout);
        toolbar = findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}