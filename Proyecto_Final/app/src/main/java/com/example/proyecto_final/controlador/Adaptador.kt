package com.example.proyecto_final.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.R

class Adaptador(var datos: MutableList<Objetos>, val fragmento: Fragment) :
    RecyclerView.Adapter<Adaptador.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_hacer_pedido, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return datos.size
    }

}


