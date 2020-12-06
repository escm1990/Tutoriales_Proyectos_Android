package com.escm.lbn.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.escm.lbn.R
import com.escm.lbn.helpers.Constants
import com.escm.lbn.mailer.MainViewInterface
import com.escm.lbn.mailer.MainViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_send_mail.*

class SendMailActivity : AppCompatActivity(), MainViewInterface{

    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var drawerLayout: DrawerLayout? = null
    lateinit var navigationView: NavigationView
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_mail)

        //Barra de herramientas
        toolbar = findViewById<View>(R.id.toolbarSendMail) as Toolbar
        setUpToolbar()

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewSendMail) //menu

        this.navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this@SendMailActivity, PrincipalActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_doctrina -> {
                    val intent5 = Intent(this@SendMailActivity, DoctrinaActivity::class.java)
                    startActivity(intent5)
                    finish()
                }
                R.id.nav_convicciones -> {
                    val intent6 = Intent(this@SendMailActivity, ConviccionesActivity::class.java)
                    startActivity(intent6)
                    finish()
                }
                R.id.nav_favoritos -> {
                    val intent4 = Intent(this@SendMailActivity, FavoritosActivity::class.java)
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
                    val intent7 = Intent(this@SendMailActivity, SendMailActivity::class.java)
                    startActivity(intent7)
                    finish()
                }
                R.id.nav_about -> {
                    val intent2 = Intent(this@SendMailActivity, AboutActivity::class.java)
                    startActivity(intent2)
                    finish()
                }
            }
            false
        })

        obtenerDatosCorreo()
    }

    private fun setUpToolbar() {
        drawerLayout = findViewById<View>(R.id.drawerLayoutSendMail) as DrawerLayout
        toolbar = findViewById(R.id.toolbarSendMail)
        setSupportActionBar(toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
    }

    //lazy genera una instancia hasta el momento de USO, no al inicio de la activity
    private val viewModel by lazy{ ViewModelProviders.of(this).get(MainViewModel::class.java) }

    fun obtenerDatosCorreo(){
        btnEnviarCorreo.setOnClickListener {
            val nombre = sm_nombre.text.toString().trim()
            val email = sm_email.text.toString().trim()
            val comentarios = sm_comentarios.text.toString().trim()
            showProgress()
            if(nombre.isNotEmpty() && email.isNotEmpty() && comentarios.isNotEmpty()){
                //Solicitar al ViewModel que envíe la información a la BD de Firestone
                viewModel.almacenarRegistroCorreo(email,nombre,comentarios).observe(this, Observer {
                    if(it) {
                        Toast.makeText(this, "Correo enviado exitosamente", Toast.LENGTH_SHORT).show()
                        hideProgress()
                    }else{
                        Toast.makeText(this, "Ocurrió un problema al enviar correo", Toast.LENGTH_SHORT).show()
                        hideProgress()
                    }
                })
            } else {
                Toast.makeText(this, "Los campos NO pueden quedar vacíos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showProgress(){
        sm_progressBar.visibility = View.VISIBLE
        btnEnviarCorreo.isEnabled = false
    }

    override fun hideProgress(){
        sm_progressBar.visibility = View.GONE
        btnEnviarCorreo.isEnabled = true
    }


}