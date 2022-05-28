package com.example.proyecto_final.Modelo

import androidx.lifecycle.ViewModel

class DatosView : ViewModel() {
    var lista_pedido = HashMap<String, Pedido>()
    lateinit var usuario: Usuarios

    fun añadir_cantidad(nombre: String, cantidad: Int, precio: Double) {
        lista_pedido.put(nombre, Pedido(nombre, cantidad,precio))
    }

    fun guardar_usuario(email: String) {
        usuario = Usuarios(email)
    }
    fun borrar_lista_pedido(){
        lista_pedido.clear()
    }
}