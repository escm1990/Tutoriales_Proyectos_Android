package com.escm.lbn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.escm.lbn.R;
import com.escm.lbn.popus.DoctrinaPopupActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class DoctrinaActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private AdView mAdView;
    Intent intentDoctrina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctrina);

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerDoctrina);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbarDoctrina);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewDoctrina); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(DoctrinaActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(DoctrinaActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(DoctrinaActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(DoctrinaActivity.this,FavoritosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.nav_contacto:
                        Intent intent7 = new Intent(DoctrinaActivity.this,SendMailActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(DoctrinaActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_youtube:
                        Intent intent8 = new Intent(DoctrinaActivity.this,YoutubeActivity.class);
                        startActivity(intent8);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutDoctrina);
        toolbar = findViewById(R.id.toolbarDoctrina);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void seleccion(View view){
        switch (view.getId()){
            case R.id.selectDoctrina1:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup1);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina2:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup2);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina3:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup3);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina4:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup4);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina5:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup5);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina6:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup6);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina7:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup7);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina8:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup8);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina9:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup9);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina10:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup10);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina11:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup11);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina12:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup12);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina13:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup13);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina14:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup14);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina15:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup15);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina16:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup16);
                startActivity(intentDoctrina);
                break;
            case R.id.selectDoctrina17:
                intentDoctrina= null;
                intentDoctrina = new Intent(this, DoctrinaPopupActivity.class);
                intentDoctrina.putExtra("layout", R.layout.activity_doctrina_popup17);
                startActivity(intentDoctrina);
                break;

        }
    }

}