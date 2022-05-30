package com.example.proyecto_final.Modelo

import com.example.proyecto_final.controlador.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BBDD {

    private val db = FirebaseFirestore.getInstance()

    fun guardar_usuario(email: String, pass: String) {
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                db.collection("users")
                    .document(email)
                    .set(
                        hashMapOf(
                            "email" to email,
                            "password" to pass
                        )
                    )
            }
    }

    fun hacer_pedido(listaObjetoComprado: HashMap<String, ObjetoComprado>, email: String) {
        var total = 0.0
        for (objeto in listaObjetoComprado.values) {
            total += objeto.cantidad * objeto.precio
        }
        var data = LocalDate.now()
        var hasmap = hashMapOf("fecha" to data.format(DateTimeFormatter.ofPattern("dd-MMM-yy")))
        listaObjetoComprado.values.forEach {
            if (it.cantidad > 0) {
                hasmap.put(it.nombre, it.cantidad.toString())
            }
            hasmap.put("email", email)
            hasmap.put("total", total.toString())
        }
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                db.collection("pedidos").document().set(hasmap)
            }

    }

    fun borrar_usuario(email: String) {
        db.collection("users").document(email).delete().addOnSuccessListener {
        }
    }

    fun sacar_objetos(): MutableList<Objetos> {
        var objetos: MutableList<Objetos> = mutableListOf()
        db.collection("objetos").get().addOnSuccessListener {
            for (item in it.documents) {
                var precio = item.getDouble("precio") as Double
                val objeto = Objetos(
                    item.get("nombre") as String, precio.toString()
                )
                objetos.add(objeto)
            }
        }
        return objetos
    }

    fun sacar_pedidos(email: String): MutableList<Pedidos> {
        var pedidos: MutableList<Pedidos> = mutableListOf()
        var objetos_comprados: MutableList<Objetos> = mutableListOf()
        var keys: MutableList<String> = mutableListOf()
        var values: MutableList<String> = mutableListOf()
        var total = ""
        var fecha = ""
        db.collection("pedidos").get().addOnSuccessListener {
            for (item in it.documents) {
                if (item.get("email")
                        .toString() == email
                ) {
                    values.clear()
                    keys.clear()
                    objetos_comprados.clear()
                    val datos_BBDD = item.getData()
                    if (datos_BBDD != null) {
                        for (key in datos_BBDD.keys) {
                            keys.add(key.toString())
                        }
                        for (value in datos_BBDD.values) {
                            values.add(value.toString())
                        }
                        for (i in 0 until keys.size) {
                            if (keys.get(i) != "total" && keys.get(i) != "fecha" && keys.get(i) != "email") {
                                objetos_comprados.add(Objetos(keys.get(i), values.get(i)))
                            } else {
                                if (keys.get(i) == "total") {
                                    total = values.get(i)
                                } else if (keys.get(i) == "fecha") {
                                    fecha = values.get(i)
                                }
                            }
                        }
                        pedidos.add(Pedidos(fecha, objetos_comprados, total))
                    }
                }
            }
        }
        return pedidos
    }

    fun sacar_precio(): HashMap<String, String> {
        var clave: MutableList<String> = mutableListOf()
        var valor: MutableList<String> = mutableListOf()
        var objetos: HashMap<String, String> = hashMapOf()
        db.collection("objetos").get().addOnSuccessListener {
            for (item in it.documents) {
                val datos_BBDD = item.getData()
                if (datos_BBDD != null) {
                    var contador = 0
                    for (value in datos_BBDD.values) {
                        if (contador == 0) {
                            contador++
                            valor.add(value.toString())
                        } else {
                            contador = 0
                            clave.add(value.toString())
                        }
                    }
                    for (i in 0 until clave.size) {
                        objetos.put(clave.get(i), valor.get(i))
                    }
                }
            }
        }
        return objetos
    }

    fun comprobar_usuario(email: String, pashash: String): MutableList<String> {
        var usuarios: MutableList<String> = mutableListOf()
        db.collection("users").get().addOnSuccessListener {
            for (item in it.documents) {

                var emailBBDD = item.get("email")
                var passBBDD = item.get("password")
                if (emailBBDD == email) {
                    if (pashash == passBBDD) {

                        usuarios.add(item.get("email") as String)
                    }
                }
            }
        }
        return usuarios
    }
}