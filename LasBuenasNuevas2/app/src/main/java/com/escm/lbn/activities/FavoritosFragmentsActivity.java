package com.escm.lbn.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.escm.lbn.entities.Favorito;
import com.escm.lbn.helpers.Constants;
import com.escm.lbn.R;
import com.escm.lbn.fragments.DetalleFavoritoFragment;
import com.escm.lbn.fragments.FavoritoFragment;
import com.escm.lbn.interfaces.IComunicaFragmentsFavoritos;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class FavoritosFragmentsActivity extends AppCompatActivity implements IComunicaFragmentsFavoritos {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private AdView mAdView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //variable del fragment detalle
    DetalleFavoritoFragment detalleFavoritoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_fragments);

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerFavoritosX);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewFavoritoX); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(FavoritosFragmentsActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(FavoritosFragmentsActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(FavoritosFragmentsActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(FavoritosFragmentsActivity.this, FavoritosFragmentsActivity.class);
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
                        Intent intent2 = new Intent(FavoritosFragmentsActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return false;
            }
        });

        //cargar fragment principal en la actividad
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedorFragment,new FavoritoFragment());
        fragmentTransaction.commit();
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutFavoritoX);
        toolbar = findViewById(R.id.toolbarFavoritoX);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void enviarFavorito(Favorito favorito) {
        //gracias a haber implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        // o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detalleFavoritoFragment = new DetalleFavoritoFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto",favorito);
        detalleFavoritoFragment.setArguments(bundleEnvio);

        //CArgar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detalleFavoritoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }

}