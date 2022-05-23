package com.example.proyecto_final.Modelo

import androidx.navigation.fragment.findNavController
import com.example.proyecto_final.R
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

    fun login_usuario(email: String, pass: String): Boolean {
        var bol = false
        db.collection("users").document(email).get()
            .addOnSuccessListener {
                bol = email == it.get("email") && pass == it.get(
                    "password"
                )
            }
        return bol
    }
}