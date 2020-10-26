package com.escm.lbn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class ConviccionesActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convicciones);

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerConvicciones);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbarConvicciones);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewConvicciones); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(ConviccionesActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(ConviccionesActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(ConviccionesActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(ConviccionesActivity.this,FavoritosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.nav_facebook:
                        Uri uri = Uri.parse(Constants.url_facebook);
                        Intent intent3 = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(ConviccionesActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutConvicciones);
        toolbar = findViewById(R.id.toolbarConvicciones);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}