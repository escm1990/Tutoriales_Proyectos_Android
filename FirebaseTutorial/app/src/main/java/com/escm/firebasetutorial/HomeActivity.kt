package com.escm.firebasetutorial

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle : Bundle? = intent.extras
        val email : String? = bundle?.getString("email")
        val provider : String? = bundle?.getString("provider")
        setup(email ?:"", provider ?:"")

        //Guardar los datos del usuario que se ha autenticado, a nivel de session (persistir)
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider",provider)
        prefs.apply()

    }

    private fun setup(email: String, provider: String){
        title="Inicio"
        emailTextView.text = email
        providerTextView.text =  provider

        //boton logout
        logoutButton.setOnClickListener{

            //borrando las preferencias al dar logout de la app
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            //evaluando deslogueo con facebook
            if(provider == ProviderType.FACEBOOK.name){
                LoginManager.getInstance().logOut()
            }

            //desloguearse
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        forzarError.setOnClickListener {

            //Enviar información adicional
            FirebaseCrashlytics.getInstance().setUserId(email) //enviando usuario de la app
            FirebaseCrashlytics.getInstance().setCustomKey("provider",provider) //enviando informacion personalizada

            //usando crahslytics como log
            FirebaseCrashlytics.getInstance().log("Se ha pulsado el boton PULSAR ERROR")

            //Forzando un error
            //throw RuntimeException("Test Crash") // Force a crash
        }
    }
}