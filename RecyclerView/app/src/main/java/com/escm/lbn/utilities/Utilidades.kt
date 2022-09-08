package com.escm.recyclerview.utilities

import android.content.Context
import android.widget.EditText
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.Throws

class Utilidades {

    companion object {
    /**
     * Validate editText.
     *
     * @param editText
     * @return
     */
    fun isValidEditText(editText: EditText, errorMessage: String?): Boolean {
        return if (editText.text.toString().trim { it <= ' ' }.length == 0) {
            editText.error = errorMessage
            false
        } else {
            editText.error = null
            true
        }
    }

    @Throws(IOException::class)
    fun leerJson(contexto: Context, archivo: String?): String {
        var contenido = ""
        var linea = ""
        var lector: BufferedReader? = null
        lector = BufferedReader(InputStreamReader(contexto.assets.open(archivo!!), "UTF-8"))
        while (lector.readLine().also { linea = it } != null) {
            contenido = contenido + linea
        }
        return contenido
    }

    fun twoDigitString(number: Int): String {
        if (number == 0) {
            return "00"
        }
        return if (number / 10 == 0) {
            "0$number"
        } else number.toString()
    }

    fun crearDuracionTexto(duracion: Int): String {
        var min = duracion / 1000 / 60
        val sec = duracion / 1000 % 60
        var sect = ""
        if (sec < 10) {
            sect = "0$sec"
        } else {
            sect += sec
        }
        var hour = 0
        if (min > 60) {
            hour = min / 60
            min = min % 60
        }
        var mint = ""
        if (min < 10) {
            mint += "0$min"
        } else {
            mint += min
        }
        return "$hour : $mint : $sect"
    }

    }
}