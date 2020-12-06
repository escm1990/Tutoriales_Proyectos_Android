package com.escm.lbn.mailer

import androidx.lifecycle.LiveData

class UseCaseMail {

    val dao = DAOSendMail()
    //retornará un LiveData de tipo Boolean
    fun almacenarRegistroCorreo(email:String,nombre:String,comentarios:String):LiveData<Boolean> = dao.almacenarRegistroCorreo(email,nombre,comentarios)
}