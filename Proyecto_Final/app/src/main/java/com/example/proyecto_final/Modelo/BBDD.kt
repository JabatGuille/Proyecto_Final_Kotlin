package com.example.proyecto_final.Modelo

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
        for (objeto in listaObjetoComprado.values){
            total += objeto.cantidad * objeto.precio
        }
        var data = LocalDate.now()
        var hasmap = hashMapOf("fecha" to data.format(DateTimeFormatter.ofPattern("dd-MMM-yy")))
        listaObjetoComprado.values.forEach {
            if (it.cantidad > 0) {
                hasmap.put(it.nombre, it.cantidad.toString())
            }
            hasmap.put("email",email)
            hasmap.put("total",total.toString())
        }
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                db.collection("pedidos").document().set(hasmap)
            }

    }
}