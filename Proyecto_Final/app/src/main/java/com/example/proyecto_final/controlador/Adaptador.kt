package com.example.proyecto_final.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.Modelo.DatosView
import com.example.proyecto_final.Modelo.Objetos
import com.example.proyecto_final.R

class Adaptador(
    var datos: MutableList<Objetos>,
    var datosView: DatosView
) :
    RecyclerView.Adapter<Adaptador.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var nombre: TextView = v.findViewById(R.id.textObjeto)
        var cantidad: EditText = v.findViewById(R.id.editTextCantidad)
        var boton: Button = v.findViewById(R.id.btn_añadir)
        var posicion = -1
        var precio: Double = 0.00

        init {

            boton.setOnClickListener {
                if (cantidad.text.toString() != "") {
                    datosView.ayadir_cantidad(
                        datos[posicion].nombre,
                        cantidad.text.toString().toInt(),
                        precio

                    )
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_hacer_pedido, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombre = datos[position].nombre + " " + datos[position].precio + "€"
        holder.nombre.setText(nombre)
        holder.posicion = position
        holder.precio = datos[position].precio.toDouble()

    }

    override fun getItemCount(): Int {
        return datos.size
    }

}


