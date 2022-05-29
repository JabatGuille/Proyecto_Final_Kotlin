package com.example.proyecto_final.controlador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.Modelo.Pedidos
import com.example.proyecto_final.databinding.ContendorVerPedidoBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
class Contenedor_ver_pedidos : Fragment() {

    private var _binding: ContendorVerPedidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val db = FirebaseFirestore.getInstance()
    private val binding get() = _binding!!
    lateinit var miRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ContendorVerPedidoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Ver pedidos"
        var framgment = this
        GlobalScope.launch(Dispatchers.Main) {
            var pedidos: MutableList<Pedidos> = mutableListOf()
            var objetos: MutableList<Objetos> = mutableListOf()
            var keys: MutableList<String> = mutableListOf()
            var values: MutableList<String> = mutableListOf()
            var total = ""
            var fecha = ""
            db.collection("pedidos").get().addOnSuccessListener {
                for (item in it.documents) {
                    if (item.get("email")
                            .toString() == (activity as MainActivity).datosView.usuario.email
                    ) {
                        values.clear()
                        keys.clear()
                        objetos.clear()
                        var datos_BBDD = item.getData()
                        if (datos_BBDD != null) {
                            for (key in datos_BBDD.keys) {
                                keys.add(key.toString())
                            }
                            for (value in datos_BBDD.values) {
                                values.add(value.toString())
                            }
                            for (i in 0 until keys.size) {
                                if (keys.get(i) != "total" && keys.get(i) != "fecha" && keys.get(i) != "email") {
                                    objetos.add(Objetos(keys.get(i), values.get(i)))
                                } else {
                                    if (keys.get(i) == "total") {
                                        total = values.get(i)
                                    } else if (keys.get(i) == "fecha") {
                                        fecha = values.get(i)
                                    }
                                }
                            }
                            pedidos.add(Pedidos(fecha, objetos, total))
                        }
                    }
                }
            }
            delay(2000L)
            (activity as MainActivity).datosView.lista_pedidos_usuario = pedidos
            miRecyclerView = binding.frag3RecyclerView
            miRecyclerView.layoutManager = LinearLayoutManager(activity)
            miRecyclerView.adapter = Adatador_ver_pedidos(
                pedidos, framgment
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}