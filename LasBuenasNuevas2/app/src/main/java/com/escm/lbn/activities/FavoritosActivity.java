package com.escm.lbn.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.escm.lbn.helpers.AdminSQLiteOpenHelper;
import com.escm.lbn.helpers.Constants;
import com.escm.lbn.entities.Favorito;
import com.escm.lbn.adapter.FavoritosAdapter;
import com.escm.lbn.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FavoritosActivity extends AppCompatActivity {

    ArrayList<Favorito> listaFavoritos;
    RecyclerView recyclerViewFavoritos;

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerFavoritos);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbarFavorito);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewFavorito); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(FavoritosActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(FavoritosActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(FavoritosActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(FavoritosActivity.this,FavoritosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.nav_contacto:
                        Intent intent7 = new Intent(FavoritosActivity.this,SendMailActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(FavoritosActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return false;
            }
        });

        listaFavoritos = new ArrayList<>();
        recyclerViewFavoritos =  (RecyclerView) findViewById(R.id.rvFavoritos);
        recyclerViewFavoritos.setLayoutManager(new LinearLayoutManager(this));

        consultarListaFavoritos();

        FavoritosAdapter adapter = new FavoritosAdapter(listaFavoritos);
        adapter.setOnClicListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context,PostDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url",listaFavoritos.get(recyclerViewFavoritos.getChildAdapterPosition(view)).getUrl());
                intent.putExtra("title",listaFavoritos.get(recyclerViewFavoritos.getChildAdapterPosition(view)).getTitulo());
                intent.putExtra("content",listaFavoritos.get(recyclerViewFavoritos.getChildAdapterPosition(view)).getContent());
                intent.putExtra("idPost",listaFavoritos.get(recyclerViewFavoritos.getChildAdapterPosition(view)).getCodigo());
                context.startActivity(intent);
            }
        });
        recyclerViewFavoritos.setAdapter(adapter);
    }

    private void consultarListaFavoritos() {
        //conectando con la BD
        AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this, Constants.bd_favoritos, null, 1);
        SQLiteDatabase BaseDeDatos = adminBD.getWritableDatabase(); //abrir base de datos en modo lectura/escritura

        Favorito favorito = null;

        Cursor cursor = BaseDeDatos.rawQuery("select codigo, titulo, url, contenido from favoritos order by titulo",null);
        while (cursor.moveToNext()){
            favorito = new Favorito();
            favorito.setCodigo(cursor.getString(0));
            favorito.setTitulo(cursor.getString(1));
            favorito.setUrl(cursor.getString(2));
            favorito.setContent(cursor.getString(3));

            listaFavoritos.add(favorito);
        }

        BaseDeDatos.close();
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutFavorito);
        toolbar = findViewById(R.id.toolbarFavorito);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}