package com.example.proyecto_final.controlador

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
            binding.textviewContraseya.visibility = View.VISIBLE
            binding.textviewContraseya2.visibility = View.VISIBLE
            binding.edittextContraseya.visibility = View.VISIBLE
            binding.edittextRepitacontraseya.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}