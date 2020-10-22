package com.escm.lbn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class PostDetailActivity extends AppCompatActivity {

    String urlPost;
    String titlePost;
    String contentPost;

    TextView txtTitle;
    HtmlTextView visorHtml;

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button botonCompartir;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        botonCompartir = (Button) findViewById(R.id.btn_share1);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationView1); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(PostDetailActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_about:
                        Toast.makeText(PostDetailActivity.this, "Acerca de", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        //CARGAR CONTENIDO DEL POST
        urlPost = getIntent().getStringExtra("url");
        titlePost =  getIntent().getStringExtra("title");
        contentPost = getIntent().getStringExtra("content");

        txtTitle = (TextView) findViewById(R.id.txtTitlePost);
        visorHtml = (HtmlTextView) findViewById(R.id.html_text);

        txtTitle.setText(titlePost);
        visorHtml.setHtml(contentPost,new HtmlHttpImageGetter(visorHtml));

        //boton compartir
        botonCompartir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
                compartir.setType("text/plain");
                String mensaje = "Quiero compartir contigo las buenas nuevas: "+urlPost;
                compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "Buenas Nuevas");
                compartir.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
                startActivity(Intent.createChooser(compartir, "Compartir vía"));
            }
        });
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}