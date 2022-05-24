package com.example.proyecto_final.Modelo

import androidx.lifecycle.ViewModel

class DatosView : ViewModel() {
    var objetos = mutableListOf<Objetos>()

    fun meterdatos() {
        BBDD().mostrar_objetos(this)
        }
}