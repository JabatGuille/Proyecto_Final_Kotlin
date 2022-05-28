package com.example.proyecto_final.Modelo

class Pedidos(
    var fecha: String,
    var objetos: MutableList<Objetos> = mutableListOf(),
    var total: String
) {

}