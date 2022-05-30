package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.proyecto_final.Modelo.BBDD
import com.example.proyecto_final.R
import com.example.proyecto_final.databinding.FragmentFirstBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_ajustes)?.isVisible = false
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
                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.editTextEmail.text.toString())
                                .matches()
                        ) {
                            if (binding.editTextPass.text.toString().length >= 8) {
                                if (binding.editTextPass.text.toString() == binding.editTextPass2.text.toString()) {
                                    val email = binding.editTextEmail.text.toString()
                                    val pass = binding.editTextPass.text.toString()
                                    val pashash =
                                        (activity as MainActivity).datosView.ecryptar_contraseya(
                                            pass
                                        )
                                    GlobalScope.launch(Dispatchers.Main) {
                                        val usuarios = BBDD().comprobar_email(email)
                                        delay(2000L)
                                        try {
                                            if (usuarios.size == 1) {
                                                binding.editTextEmail.setText("")
                                                Toast.makeText(
                                                    activity, "Email ya existe",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {
                                                Toast.makeText(
                                                    activity,
                                                    "Usuario creado",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                BBDD().guardar_usuario(email, pashash)
                                                findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
                                            }
                                        } catch (e: Exception) {
                                        }

                                    }
                                } else {
                                    binding.editTextPass.setText("")
                                    binding.editTextPass2.setText("")
                                    Toast.makeText(
                                        activity,
                                        "Contraseñas no coinciden",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                binding.editTextPass.setText("")
                                binding.editTextPass2.setText("")
                                Toast.makeText(
                                    activity,
                                    "La contraseña corta debe tener minimo 8 de longitud",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(activity, "Email no valido", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(activity, "Falta repetir contraseña", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(activity, "Falta contraseña", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(activity, "Falta email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





