// Ejercicio 28: Adaptador para el RecyclerView
package com.example.hilosejecucinsensores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class RegistroData(
    val pasos: Int,
    val nivelMovimiento: String,
    val orientacion: String
)

class RegistroAdapter(private val lista: List<RegistroData>) : RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPasos: TextView = view.findViewById(R.id.tvPasosItem)
        val tvMovimiento: TextView = view.findViewById(R.id.tvMovimientoItem)
        val tvOrientacion: TextView = view.findViewById(R.id.tvOrientacionItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_registro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.tvPasos.text = "Pasos registrados: ${item.pasos}"
        holder.tvMovimiento.text = "Nivel movimiento: ${item.nivelMovimiento}"
        holder.tvOrientacion.text = "Orientación: ${item.orientacion}"
    }

    override fun getItemCount(): Int = lista.size
}
