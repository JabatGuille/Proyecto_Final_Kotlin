package com.example.proyecto_final.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.Modelo.Pedidos
import com.example.proyecto_final.R


class Adatador_ver_pedidos(var datos: MutableList<Pedidos>, val fragmento: Fragment) :
    RecyclerView.Adapter<Adatador_ver_pedidos.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var posicion: Int = -2
        var total = ""
        var fecha: TextView = v.findViewById(R.id.texto_fecha)
        var boton: Button = v.findViewById(R.id.boton_ver_pedido)
        lateinit var pedido: Pedidos
        lateinit var objetos: MutableList<Objetos>

        init {
            boton.setOnClickListener {
                val bundle = bundleOf(
                    "id" to this.posicion,
                    "precio" to objetos
                )
                fragmento.findNavController()
                    .navigate(R.id.action_recyclerview_ver_pedidos_to_recyclerview_pedido, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_ver_pedidos, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fecha.setText(datos.get(position).fecha)
        holder.objetos = datos.get(position).objetos
        holder.total = datos.get(position).total
        holder.posicion = position
        holder.pedido = datos.get(position)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

}


