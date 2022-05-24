package com.example.proyecto_final.Modelo

import androidx.lifecycle.ViewModel

class DatosView : ViewModel() {
    var objetos = mutableListOf<Objetos>()
    var lista_pedido = mutableListOf<Pedido>()
    lateinit var usuario:Usuarios
    fun meterdatos() {
        BBDD().mostrar_objetos(this)
    }

    fun añadir_cantidad(nombre: String, precio: Double, cantidad: Int) {
        lista_pedido.add(Pedido(nombre, precio, cantidad))
    }
    fun guardar_usuario(email:String){
        usuario = Usuarios(email)
    }
}