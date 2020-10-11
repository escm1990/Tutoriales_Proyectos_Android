package com.escm.lbn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DetalleActivity extends AppCompatActivity {

    private String urlPost = "";
    ProgressBar progressBar;
    Toolbar toolbar;
    WebView webView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        urlPost = getIntent().getStringExtra("url");
        //Toast.makeText(this, urlPost, Toast.LENGTH_SHORT).show();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        webView = (WebView) findViewById(R.id.detailView);

        setUpToolbar();
        navigationView = findViewById(R.id.idNavigationView); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(DetalleActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Toast.makeText(DetalleActivity.this, "Android", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        Toast.makeText(DetalleActivity.this, "Android", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view,url,favicon);
                //Toast.makeText(DetalleActivity.this, "Inicia carga de post", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                //Toast.makeText(DetalleActivity.this, "Post cargado", Toast.LENGTH_SHORT).show();
                String javaScript = "javascript: (function() {\n" +
                        "    var a = document.getElementsByTagName('header');\n" +
                        "    a[0].hidden = 'true';\n" +
                        "    a = document.getElementsByClassName('page_body');\n" +
                        "    a[0].style.padding = '0px';\n" +
                        "    var b = document.getElementsByClassName('post-footer-line post-footer-line-1');\n" +
                        "    b[0].hidden = 'true';\n" +
                        "    var c = document.getElementsByClassName('post-footer-line post-footer-line-2');\n" +
                        "    c[0].hidden = 'true';\n" +
                        "    var d = document.getElementsByClassName('sharing');\n" +
                        "    d[0].hidden = 'true';\n" +
                        "    d[1].hidden = 'true';\n" +
                        "    var x = document.getElementById('identityControlsHolder');\n" +
                        "    x.style.display = \"none\";\n" +
                        "    var y = document.getElementsByClassName('commentBodyContainer');\n" +
                        "    y[0].hidden = 'true';\n" +
                        "    var w = document.getElementsByClassName('sidebar-container container');\n" +
                        "    w[0].hidden = 'true';\n" +
                        "    var i = document.getElementsByClassName('blogger');\n" +
                        "    i[0].hidden = 'true';\n" +
                        "})()";

                webView.loadUrl(javaScript);
            }
        });
        webView.loadUrl(urlPost);
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}