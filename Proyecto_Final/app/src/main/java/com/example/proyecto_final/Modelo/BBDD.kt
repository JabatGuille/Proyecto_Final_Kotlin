package com.example.proyecto_final.Modelo

import android.util.Log
import com.example.proyecto_final.controlador.MainActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import java.time.LocalDateTime

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

    fun hacer_pedido(listaPedido: HashMap<String, Pedido>, email: String) {
        var data = LocalDateTime.now().toString()
        var hasmap = hashMapOf("fecha" to data)
        listaPedido.values.forEach {
            if (it.cantidad > 0) {
                hasmap.put(it.nombre, it.cantidad.toString())
            }
        }
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