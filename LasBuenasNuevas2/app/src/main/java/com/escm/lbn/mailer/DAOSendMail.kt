package com.escm.lbn.mailer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class DAOSendMail {

    //retornará un LiveData de tipo Boolean
    fun almacenarRegistroCorreo(email:String,nombre:String,comentarios:String): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        val correo = Correo(email,nombre,comentarios)
        //llamando a la BD Firestone
        FirebaseFirestore.getInstance()
                .collection("correos")
                .add(correo)
                .addOnSuccessListener {
                    data.value = true
                }.addOnFailureListener{
                    data.value = false
                }

        return data
    }
}