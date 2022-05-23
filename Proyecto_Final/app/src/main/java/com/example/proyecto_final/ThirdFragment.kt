package com.example.proyecto_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyecto_final.databinding.FragmentThirdBinding
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
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
                db.collection("users").document(binding.editTextEmail1.text.toString()).get()
                    .addOnSuccessListener {
                        if (binding.editTextEmail1.text.toString() == it.get("email") && binding.editTextPass1.text.toString() == it.get(
                                "password"
                            )
                        ) {
                            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment2)

                        }else{
                            print("Email o contraseña incorrectos")
                        }
                    }
            }else{
                print("Falta email o contraseña")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}