package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.BBDD
import com.example.proyecto_final.R
import com.example.proyecto_final.databinding.ContendorBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_ajustes)?.isVisible = false
        menu.findItem(R.id.action_retorno)?.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Hacer Pedido"
        GlobalScope.launch(Dispatchers.Main) {
            val objetos = BBDD().sacar_objetos()
            delay(2000L)
            try {
                (activity as MainActivity).datosView.borrar_lista_pedido()
                miRecyclerView = binding.frag2RecyclerView
                miRecyclerView.layoutManager = LinearLayoutManager(activity)
                miRecyclerView.adapter = Adaptador(
                    objetos, (activity as MainActivity).datosView
                )
                binding.butcompra.setOnClickListener {
                    if ((activity as MainActivity).datosView.lista_objetoComprado.size > 0) {
                        BBDD().hacer_pedido(
                            (activity as MainActivity).datosView.lista_objetoComprado,
                            (activity as MainActivity).datosView.usuario.email
                        )
                        Toast.makeText(activity, "Compra realizada", Toast.LENGTH_SHORT).show()

                        findNavController().navigate(R.id.action_recyclerview_hacer_pedido_to_SecondFragment)
                    }
                }
                binding.butretroceder.setOnClickListener {
                    Toast.makeText(activity, "Cancelando compra", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_recyclerview_hacer_pedido_to_SecondFragment)
                }
            } catch (e: Exception) {
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}