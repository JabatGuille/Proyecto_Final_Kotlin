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
import com.example.proyecto_final.databinding.FragmentThirdBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
class ThirdFragment : Fragment() {

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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_ajustes)?.isVisible = false
        menu.findItem(R.id.action_retorno)?.isVisible = false
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
                val email = binding.editTextEmail1.text.toString()
                val pass = binding.editTextPass1.text.toString()
                val pashash = (activity as MainActivity).datosView.ecryptar_contraseya(pass)
                GlobalScope.launch(Dispatchers.Main) {
                    val usuarios = BBDD().comprobar_usuario(email, pashash)
                    delay(2000L)
                    try {
                        if (usuarios.size == 1) {
                            (activity as MainActivity).datosView.guardar_usuario(email)
                            binding.editTextPass1.setText("")
                            binding.editTextEmail1.setText("")
                            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment2)
                        } else {
                            binding.editTextEmail1.setText("")
                            binding.editTextPass1.setText("")
                            Toast.makeText(
                                activity,
                                "Email o contraseña incorrectos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {

                    }
                }
            } else {
                Toast.makeText(activity, "Falta email o contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}