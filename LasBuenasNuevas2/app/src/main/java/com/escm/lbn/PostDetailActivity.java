package com.escm.lbn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class PostDetailActivity extends AppCompatActivity {

    String urlPost;
    String titlePost;
    String contentPost;
    String idPost;

    TextView txtTitle;
    HtmlTextView visorHtml;

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button botonCompartir, botonFavoritos;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        botonCompartir = (Button) findViewById(R.id.btn_share1);
        botonFavoritos = (Button) findViewById(R.id.btn_favorite);
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
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(PostDetailActivity.this,FavoritosActivity.class);
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
                        Intent intent2 = new Intent(PostDetailActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return false;
            }
        });

        //CARGAR CONTENIDO DEL POST
        urlPost = getIntent().getStringExtra("url");
        titlePost =  getIntent().getStringExtra("title");
        contentPost = getIntent().getStringExtra("content");
        idPost = getIntent().getStringExtra("idPost");

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

        buscarFavorito();

        //definiendo el background del botón favoritos
        if(buscarFavorito()){
            botonFavoritos.setBackgroundResource(R.drawable.favorito_checked);
        }else{
            botonFavoritos.setBackgroundResource(R.drawable.favorito_unchecked);
        }

        //accion del boton favoritos
        botonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buscarFavorito()){
                    registrarFavorito();
                    botonFavoritos.setBackgroundResource(R.drawable.favorito_checked);
                }else{
                    eliminarFavorito();
                    botonFavoritos.setBackgroundResource(R.drawable.favorito_unchecked);
                }
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

    //Guardar favoritos
    private void registrarFavorito(){
        try {
            AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this, Constants.bd_favoritos, null, 1);
            SQLiteDatabase BaseDeDatos = adminBD.getWritableDatabase(); //abrir base de datos en modo lectura/escritura

            ContentValues registro = new ContentValues();
            registro.put("codigo", idPost);
            registro.put("titulo", titlePost);
            registro.put("url", urlPost);
            registro.put("contenido",contentPost);

            BaseDeDatos.insert("favoritos", null, registro);
            BaseDeDatos.close();

            Toast.makeText(this, "Favorito guardado exitosamente", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(this, "Ha ocurrido un error al intentar guardar el favorito", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean buscarFavorito(){
        boolean existeFavorito = false;
        String codigoEncontrado = "";

        AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this, Constants.bd_favoritos, null, 1);
        SQLiteDatabase BaseDeDatos = adminBD.getWritableDatabase(); //abrir base de datos en modo lectura/escritura

        if(!idPost.isEmpty()) {
            Cursor cursor = BaseDeDatos.rawQuery("select codigo from favoritos where codigo = " + idPost, null);
            codigoEncontrado = (cursor.moveToFirst() ? cursor.getString(0) : "");

            if(codigoEncontrado.equalsIgnoreCase(idPost)){
                existeFavorito = true;
            }else{
                existeFavorito = false;
            }
        }
        BaseDeDatos.close();
        return existeFavorito;
    }


    private void eliminarFavorito() {
        AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this, Constants.bd_favoritos, null, 1);
        SQLiteDatabase BaseDeDatos = adminBD.getWritableDatabase(); //abrir base de datos en modo lectura/escritura
        if(!idPost.isEmpty()) {
            //eliminar un valor en sql, se utiliza un metodo que devuelve un entero con los registros eliminados
            //se le pasa el nombre de la tabla y la clausula where con los parámetros
            int cantidad = BaseDeDatos.delete("favoritos","codigo="+idPost,null);
            BaseDeDatos.close();

            if(cantidad==1){
                Toast.makeText(this, "Favorito eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Ha ocurrido un error al intentar eliminar el favorito", Toast.LENGTH_SHORT).show();
            }

        }
    }
}