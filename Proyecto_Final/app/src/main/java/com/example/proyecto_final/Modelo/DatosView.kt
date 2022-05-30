package com.example.proyecto_final.Modelo

import androidx.lifecycle.ViewModel
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class DatosView : ViewModel() {
    var lista_objetoComprado = HashMap<String, ObjetoComprado>()
    lateinit var usuario: Usuarios
    var precio: HashMap<String, String> = hashMapOf()
    var lista_pedidos_usuario: MutableList<Pedidos> = mutableListOf()

    fun añadir_cantidad(nombre: String, cantidad: Int, precio: Double) {
        lista_objetoComprado.put(nombre, ObjetoComprado(nombre, cantidad, precio))
    }

    fun guardar_usuario(email: String) {
        usuario = Usuarios(email)
    }

    fun borrar_lista_pedido() {
        lista_objetoComprado.clear()
    }

    fun ecryptar_contraseya(pass: String): String {
        val md = MessageDigest.getInstance("MD5")
        val hashInByte = md.digest(pass.toByteArray(StandardCharsets.UTF_8))
        val sb = StringBuilder()
        for (b in hashInByte) {
            sb.append(String.format("%02x", b))
        }
        return sb.toString()
    }
}