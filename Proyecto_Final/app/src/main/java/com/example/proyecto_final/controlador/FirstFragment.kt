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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

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
                                if (BBDD().guardar_usuario(
                                        binding.editTextEmail.text.toString(),
                                        binding.editTextPass.text.toString()
                                    )
                                ) {
                                    print("Email ya existe")
                                    binding.editTextEmail.setText("")
                                } else {
                                    print("Usuario creado")
                                    findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
                                }
                            } else {
                                print("Email no valido")
                                Log.d("","Email no valido")
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





