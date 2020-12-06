package com.escm.lbn.mailer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel() {

    val useCase = UseCaseMail()
    fun almacenarRegistroCorreo(email:String,nombre:String,comentarios:String):LiveData<Boolean>{
        val data = MutableLiveData<Boolean>()
        useCase.almacenarRegistroCorreo(email,nombre,comentarios).observeForever(){
            data.postValue(it)
        }
        return data
    }

}