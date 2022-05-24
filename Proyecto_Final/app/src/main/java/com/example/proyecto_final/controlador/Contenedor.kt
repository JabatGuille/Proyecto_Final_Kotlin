package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.BBDD
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.databinding.ContendorBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Contenedor : Fragment() {

    private var _binding: ContendorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var miRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ContendorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        miRecyclerView = binding.frag2RecyclerView
        miRecyclerView.layoutManager = LinearLayoutManager(activity)
        miRecyclerView.adapter = Adaptador((activity as MainActivity).datosView.objetos, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}