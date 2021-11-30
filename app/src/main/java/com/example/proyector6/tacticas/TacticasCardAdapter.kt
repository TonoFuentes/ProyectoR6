package com.example.proyector6.tacticas

import android.graphics.BitmapFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import org.w3c.dom.Text

class TacticasCardAdapter(var items: ArrayList<TacticasCard>) : RecyclerView.Adapter<TacticasCardAdapter.TarjViewHolder>() {
    lateinit var onClick : (View) -> Unit


    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombreTactica: TextView
        val nombreAgente: TextView
        val nombreMapa: TextView
        val descripcion: TextView

        init {
            nombreTactica = itemView.findViewById(R.id.nombreTactica)
            nombreAgente = itemView.findViewById(R.id.nombreAgente)
            nombreMapa = itemView.findViewById(R.id.nombreMapa)
            descripcion = itemView.findViewById(R.id.descTactica)
        }

        fun bindCards(t: TacticasCard) {
            nombreTactica.text = t.nTactica
            nombreMapa.text = t.nMapa
            nombreAgente.text = t.nAgente
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.listar_layout, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val itemCard = items[pos]
        viewHolder.bindCards(itemCard)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}