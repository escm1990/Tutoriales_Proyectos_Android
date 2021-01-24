package com.escm.lbn.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.escm.lbn.blogger.BloggerAPI;
import com.escm.lbn.blogger.Item;
import com.escm.lbn.adapter.PostAdapter;
import com.escm.lbn.blogger.PostList;
import com.escm.lbn.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    PostAdapter adapter;
    List<Item> items = new ArrayList<>();
    boolean isScrolling = false, isFiltrando = false;
    int currentItems, totalItems, scrollOutItems;
    String token = "";
    SpinKitView progress;
    private AdView mAdView;
    ImageButton btnFiltroBuscar;
    EditText txtFiltroBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerPrincipal);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //otras variables
        recyclerView = (RecyclerView) findViewById(R.id.postList);
        manager = new LinearLayoutManager(this);
        adapter = new PostAdapter(this, items);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        progress = (SpinKitView) findViewById(R.id.spin_kit);
        btnFiltroBuscar = (ImageButton) findViewById(R.id.btn_FiltroBuscar);
        txtFiltroBuscar = (EditText) findViewById(R.id.filtroBuscar);

        setUpToolbar();
        navigationView = findViewById(R.id.idNavigationView); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(PrincipalActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(PrincipalActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(PrincipalActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(PrincipalActivity.this, FavoritosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.nav_contacto:
                        Intent intent7 = new Intent(PrincipalActivity.this,SendMailActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(PrincipalActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return false;
            }
        });

        //endless scroll
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    obtenerDatosPost();
                }
            }
        });

        //Carga la lista de post obtenidos
        obtenerDatosPost();

        //buscar post por filtro de búsqueda
        btnFiltroBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filtro = txtFiltroBuscar.getText().toString().trim();
                if(TextUtils.isEmpty(filtro)){
                    obtenerDatosPost();
                } else {
                    buscarDatosPostFiltrados(filtro);
                }
            }
        });

    }

    //Carga la información de todos los post disponible en el blog
    private void obtenerDatosPost(){
        String url = BloggerAPI.url +"?key=" + BloggerAPI.key;
        if(token != ""){
            url = url + "&pageToken="+token;
        }
        if(token == null){
            return;
        }
        progress.setVisibility(View.VISIBLE);
        Call<PostList> postList = BloggerAPI.getService().getPostLIst(url);
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                token = list.getNextPageToken();
                items.addAll(list.getItems());
                adapter.notifyDataSetChanged();
                //recyclerView.setAdapter(new PostAdapter(PrincipalActivity.this,list.getItems()));
                //Toast.makeText(PrincipalActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(PrincipalActivity.this, "Error al cargar lista de post", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PrincipalActivity.this,NoRedActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Lista todos los post que coincidan con el criterio de búsqueda.
    private void buscarDatosPostFiltrados(String filtro) {
        isFiltrando = true;
        String url = BloggerAPI.urlFiltro +filtro+"&key=" + BloggerAPI.key;
        if(token != ""){
            url = url + "&pageToken="+token;
        }
        if(token == null){
            return;
        }
        Log.d("MAIN_TAG", "buscarDatosPostFiltrados: "+url);
        progress.setVisibility(View.VISIBLE);
        Call<PostList> postList = BloggerAPI.getService().getPostLIst(url);
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                token = list.getNextPageToken();
                items.addAll(list.getItems());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(new PostAdapter(PrincipalActivity.this,list.getItems()));
                //Toast.makeText(PrincipalActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                //Toast.makeText(PrincipalActivity.this, "Error al cargar lista de post", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PrincipalActivity.this,NoRedActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}