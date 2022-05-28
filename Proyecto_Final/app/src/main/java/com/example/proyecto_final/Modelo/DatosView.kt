package com.example.proyecto_final.Modelo

import androidx.lifecycle.ViewModel

class DatosView : ViewModel() {
    var lista_pedido = HashMap<String, Pedido>()
    lateinit var usuario: Usuarios

    fun añadir_cantidad(nombre: String, precio: Double, cantidad: Int) {
        lista_pedido.put(nombre, Pedido(nombre, precio, cantidad))
    }

    fun guardar_usuario(email: String) {
        usuario = Usuarios(email)
    }
    fun borrar_lista_pedido(){
        lista_pedido.clear()
    }
}