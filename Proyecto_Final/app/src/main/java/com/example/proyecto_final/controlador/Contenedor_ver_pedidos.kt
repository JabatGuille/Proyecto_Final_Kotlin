package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.databinding.ContendorVerPedidoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Contenedor_ver_pedidos : Fragment() {

    private var _binding: ContendorVerPedidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var miRecyclerView: RecyclerView
    var cantidades = mutableListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ContendorVerPedidoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Ver Pedidos"
        miRecyclerView = binding.frag3RecyclerView
        miRecyclerView.layoutManager = LinearLayoutManager(activity)
        miRecyclerView.adapter = Adatador_ver_pedidos((activity as MainActivity).datosView.objetos, this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}