package com.example.proyecto_final.Modelo

import android.util.Log
import com.example.proyecto_final.controlador.MainActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import java.time.LocalDateTime

class BBDD {

    private val db = FirebaseFirestore.getInstance()

    fun guardar_usuario(email: String, pass: String): Boolean {
        var bol = false

        db.collection("users").document(email).get()
            .addOnSuccessListener {
                if (email == it.get("email") && pass == it.get("password")
                ) {
                    bol = true
                } else {
                    db.collection("users")
                        .document(email)
                        .set(
                            hashMapOf(
                                "email" to email,
                                "password" to pass
                            )
                        )
                    bol = false
                }

            }
        return bol
    }

    fun login_usuario(email: String, pass: String): Boolean {
        var bol: Boolean = true
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                var bola = email == it.get("email") && pass == it.get(
                    "email"
                )
                bol = bola
            }
        return bol
    }

    fun ver_pedidos() {

    }

    fun hacer_pedido(listaPedido: MutableList<Pedido>, email: String) {
        var data = LocalDateTime.now().toString()
        var hasmap = hashMapOf("fecha" to data)
        listaPedido.forEach{
            hasmap.put(it.nombre, it.cantidad.toString())
        }
        hasmap.put(listaPedido[listaPedido.size-1].nombre, listaPedido[listaPedido.size-1].cantidad.toString())
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                db.collection("pedidos").document(email + data).set(hasmap)

            }


    }


    fun mostrar_objetos(datosView: DatosView) {
        db.collection("objetos").get().addOnSuccessListener {
            for (item in it.documents) {
                val objeto = Objetos(
                    item.get("nombre") as String, item.getDouble("precio") as Double
                )
                datosView.objetos.add(objeto)
            }
        }
    }
}