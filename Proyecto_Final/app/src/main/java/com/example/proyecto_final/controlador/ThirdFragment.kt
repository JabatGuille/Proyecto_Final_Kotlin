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
import com.example.proyecto_final.databinding.FragmentThirdBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
class ThirdFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Inicio de sesión"
        binding.btnRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
        }
        binding.btnLogIn.setOnClickListener {
            if (binding.editTextEmail1.text.toString()
                    .isNotEmpty() && binding.editTextPass1.text.toString().isNotEmpty()
            ) {
                var bol = false
                var email = binding.editTextEmail1.text.toString()
                var pass = binding.editTextPass1.text.toString()
                GlobalScope.launch(Dispatchers.Main) {
                    db.collection("users")
                        .document(email).get()
                        .addOnSuccessListener {
                            if (it.get("email") != null) {
                                var asda = it.get("email")
                                var pasdwa = it.get("password")
                                if (asda == email) {
                                    if (pasdwa == pass) {
                                        bol = false
                                    }
                                }
                            } else {
                                bol = true
                            }
                        }
                    delay(3000L)
                    if (bol) {
                        binding.editTextEmail1.setText("")
                        binding.editTextPass1.setText("")
                        Log.d("", "Email o contraseña incorrectos")
                    } else {
                        findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment2)
                    }
                }
            } else {
                print("Falta email o contraseña")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}