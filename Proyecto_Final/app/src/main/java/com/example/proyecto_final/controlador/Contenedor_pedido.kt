package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.Pedidos
import com.example.proyecto_final.R
import com.example.proyecto_final.databinding.ContenedorPedidoBinding
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Contenedor_pedido : Fragment() {

    private var _binding: ContenedorPedidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val db = FirebaseFirestore.getInstance()
    private val binding get() = _binding!!
    lateinit var miRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContenedorPedidoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_ajustes)?.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Ver Pedido"
        val pedido: Pedidos = (activity as MainActivity).datosView.lista_pedidos_usuario.get(
            arguments?.getInt("id") ?: -1
        )
        binding.textoTotal.setText("Total: " + pedido.total)
        miRecyclerView = binding.fragment4
        miRecyclerView.layoutManager = LinearLayoutManager(activity)
        miRecyclerView.adapter = Adatador_pedido(
            pedido.objetos,(activity as MainActivity).datosView.precio
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}