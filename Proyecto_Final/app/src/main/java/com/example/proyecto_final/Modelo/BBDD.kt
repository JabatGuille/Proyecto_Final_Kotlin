package com.example.proyecto_final.Modelo

import com.google.firebase.firestore.FirebaseFirestore

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
}