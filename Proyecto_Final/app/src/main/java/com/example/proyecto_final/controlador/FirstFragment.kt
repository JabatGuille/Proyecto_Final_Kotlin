package com.example.proyecto_final.controlador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyecto_final.Modelo.BBDD
import com.example.proyecto_final.R
import com.example.proyecto_final.databinding.FragmentFirstBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
class FirstFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title = "Registro"
        binding.btnLogInFirst.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
            this.onDestroyView()
        }
        binding.btnRegistroFirst.setOnClickListener {
            if (binding.editTextEmail.text.toString().isNotEmpty()) {
                if (binding.editTextPass.text.toString().isNotEmpty()) {
                    if (binding.editTextPass2.text.toString().isNotEmpty()) {
                        if (binding.editTextPass.text.toString() == binding.editTextPass2.text.toString()) {
                            if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmail.text.toString())
                                    .matches()
                            ) {
                                var bol = false
                                var email = binding.editTextEmail.text.toString()
                                var pass = binding.editTextPass.text.toString()
                                GlobalScope.launch(Dispatchers.Main) {
                                    db.collection("users")
                                        .document(email).get()
                                        .addOnSuccessListener {
                                            if (it.get("email") != null) {
                                                var asda = it.get("email")
                                                if (asda == email) {
                                                    bol = true
                                                }
                                            } else {
                                                bol = false
                                            }
                                        }
                                    delay(3000L)
                                    if (bol) {
                                        binding.editTextEmail.setText("")
                                        Log.d("", "Email ya existe")
                                    } else {
                                        print("Usuario creado")
                                        BBDD().guardar_usuario(email, pass)
                                        findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
                                    }
                                }
                            } else {
                                print("Email no valido")
                                Log.d("", "Email no valido")
                            }
                        } else {
                            binding.editTextPass.setText("")
                            binding.editTextPass2.setText("")
                            print("Contraseñas no coinciden")
                        }
                    } else {
                        print("Falta repetir contraseña")
                    }
                } else {
                    println("Falta contraseña")
                }

            } else {
                println("Falta email")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





