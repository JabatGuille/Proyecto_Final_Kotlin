package com.example.proyecto_final.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.R


class Adatador_pedido(var datos: MutableList<Objetos>, var precio: HashMap<String, String>) :
    RecyclerView.Adapter<Adatador_pedido.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var ojeto: TextView = v.findViewById(R.id.texto_objeto_comprado)
        var cantidad: TextView = v.findViewById(R.id.texto_objeto_cantidad)
        var precio_text: TextView = v.findViewById(R.id.texto_objeto_precio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_pedido, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ojeto.setText(datos.get(position).nombre)
        holder.cantidad.setText(datos.get(position).precio)
        holder.precio_text.setText(precio.get(datos.get(position).nombre))
    }

    override fun getItemCount(): Int {
        return datos.size
    }

}


