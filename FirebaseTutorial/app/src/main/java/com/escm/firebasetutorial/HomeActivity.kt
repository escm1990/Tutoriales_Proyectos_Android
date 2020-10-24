package com.escm.firebasetutorial

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {

    //Crear constante referenciando a la base de datos de FIRESTONE
    private val db = FirebaseFirestore.getInstance()

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

        //Recuperar los datos del remote config
        forzarError.visibility = View.INVISIBLE
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val showErrorButton = Firebase.remoteConfig.getBoolean("show_error_button")
                val errorButtonText = Firebase.remoteConfig.getString("error_button_text")

                if(showErrorButton){
                    forzarError.visibility = View.VISIBLE
                }
                forzarError.text = errorButtonText
            }
        }
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

        //FIRESTONE
        //trabajando con los botones para manipular la base de datos Firestone
        //las colecciones son las estructuras de datos donde se almacenaran valores en la BD
        saveButton.setOnClickListener{
            //el documento va a ir asociado al correo del usuario logueado, con esto se logra que
            //tener un documento por usuario y facilmente accesible independiente del dispositivo
            db.collection("users").document(email).set(
                hashMapOf("provider" to provider,
                          "address" to addressTextView.text.toString(),
                           "phone" to phoneTextView.text.toString())
            )
        }

        getButton.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener{
                addressTextView.setText(it.get("address") as String?)
                phoneTextView.setText(it.get("phone") as String?)
            }
        }

        deleteButton.setOnClickListener{
            db.collection("users").document(email).delete()
        }
    }
}