package com.escm.lbn.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.escm.lbn.R
import com.escm.lbn.helpers.Constants
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_no_red.*

class NoRedActivity : AppCompatActivity() {

    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var drawerLayout: DrawerLayout? = null
    lateinit var navigationView: NavigationView
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_red)

        //Barra de herramientas
        toolbar = toolbarNoRed as Toolbar
        setUpToolbar()

        //Panel de navegacion lateral
        navigationView = idNavigationViewNoRed //menu

        this.navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_doctrina -> {
                    val intent5 = Intent(this, DoctrinaActivity::class.java)
                    startActivity(intent5)
                    finish()
                }
                R.id.nav_convicciones -> {
                    val intent6 = Intent(this, ConviccionesActivity::class.java)
                    startActivity(intent6)
                    finish()
                }
                R.id.nav_favoritos -> {
                    val intent4 = Intent(this, FavoritosActivity::class.java)
                    startActivity(intent4)
                    finish()
                }
                R.id.nav_facebook -> {
                    val uri = Uri.parse(Constants.url_facebook)
                    val intent3 = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent3)
                    finish()
                }
                R.id.nav_contacto -> {
                    val intent7 = Intent(this, SendMailActivity::class.java)
                    startActivity(intent7)
                    finish()
                }
                R.id.nav_about -> {
                    val intent2 = Intent(this, AboutActivity::class.java)
                    startActivity(intent2)
                    finish()
                }
            }
            false
        })

    }

    private fun setUpToolbar() {
        drawerLayout = drawerLayoutNoRed as DrawerLayout
        toolbar = toolbarNoRed
        setSupportActionBar(toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
    }

     fun botonNoRed(view: View){
        botonNoRedx.setOnClickListener {
            Toast.makeText(this, "Validando conexión a internet", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
}