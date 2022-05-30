package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.BBDD
import com.example.proyecto_final.R
import com.example.proyecto_final.databinding.ContendorVerPedidoBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
class Contenedor_ver_pedidos : Fragment() {

    private var _binding: ContendorVerPedidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var miRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ContendorVerPedidoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_ajustes)?.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Ver pedidos"
        val framgment = this
        GlobalScope.launch(Dispatchers.Main) {
            (activity as MainActivity).datosView.lista_pedidos_usuario =
                BBDD().sacar_pedidos((activity as MainActivity).datosView.usuario.email)
            val objetos = BBDD().sacar_precio()
            delay(3000L)
            if ((activity as MainActivity).datosView.lista_pedidos_usuario.size > 0) {
                (activity as MainActivity).datosView.precio = objetos
                miRecyclerView = binding.frag3RecyclerView
                miRecyclerView.layoutManager = LinearLayoutManager(activity)
                miRecyclerView.adapter = Adatador_ver_pedidos(
                    (activity as MainActivity).datosView.lista_pedidos_usuario, framgment
                )
            } else {
                Toast.makeText(activity, "No existen pedidos que mostrar", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}