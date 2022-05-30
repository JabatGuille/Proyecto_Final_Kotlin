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
import com.example.proyecto_final.databinding.AjustesBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Ajustes : Fragment() {

    private var _binding: AjustesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AjustesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_ajustes)?.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Ajustes"

        binding.botonCambiarContraseya.setOnClickListener {
            binding.botonGuardarContraseya.visibility = View.VISIBLE
            binding.edittextContraseya.visibility = View.VISIBLE
            binding.edittextRepitacontraseya.visibility = View.VISIBLE
        }

        binding.botonGuardarContraseya.setOnClickListener {
            if (binding.edittextContraseya.text.toString().length >= 8) {
                if (binding.edittextContraseya.text.toString() == binding.edittextRepitacontraseya.text.toString()) {
                    BBDD().guardar_usuario(
                        (activity as MainActivity).datosView.usuario.email,
                        (activity as MainActivity).datosView.ecryptar_contraseya(binding.edittextContraseya.text.toString())
                    )
                } else {
                    binding.edittextContraseya.setText("")
                    binding.edittextRepitacontraseya.setText("")
                    Toast.makeText(
                        activity,
                        "Las contraseñas no coinciden",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                binding.edittextContraseya.setText("")
                binding.edittextRepitacontraseya.setText("")
                Toast.makeText(
                    activity,
                    "La contraseña corta debe tener minimo 8 de longitud",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnBorrarUsuario.setOnClickListener {
            BBDD().borrar_usuario((activity as MainActivity).datosView.usuario.email)
            findNavController().navigate(R.id.action_ajustes_to_thirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}