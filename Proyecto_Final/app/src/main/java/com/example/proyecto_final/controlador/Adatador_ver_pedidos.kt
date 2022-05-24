package com.example.proyecto_final.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.R


class Adatador_ver_pedidos(var datos: MutableList<Objetos>, val fragmento: Fragment) :
    RecyclerView.Adapter<Adatador_ver_pedidos.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var posicion:Int=-2
        var boton: Button =v.findViewById(R.id.boton_ver_pedido)

        init {
            boton.setOnClickListener {
                val bundle = bundleOf("id" to this.posicion)
                fragmento.findNavController().navigate(R.id.action_recyclerview_ver_pedidos_to_SecondFragment,bundle)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_hacer_pedido, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.posicion=position
    }

    override fun getItemCount(): Int {
        return datos.size
    }

}


