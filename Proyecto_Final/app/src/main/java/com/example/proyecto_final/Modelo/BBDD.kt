package com.example.proyecto_final.Modelo

import com.example.proyecto_final.controlador.MainActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

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
        var bol = true
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                bol = email == it.get("email") && pass == it.get(
                    "password"
                )
            }
        return bol
    }

    fun ver_pedidos() {}
    fun hacer_pedido() {}
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