package com.example.proyector6.agentes

import android.graphics.BitmapFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R

class ItemCardAdapter(var items: ArrayList<ItemCard>) : RecyclerView.Adapter<ItemCardAdapter.TarjViewHolder>() {
    lateinit var onClick : (View) -> Unit


    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView
        val text: TextView

        init {
            imagen = itemView.findViewById(R.id.ivImage)
            text = itemView.findViewById(R.id.nombre)
        }

        fun bindCards(t: ItemCard) {
            val img = t.imagen
            val imgBmp = BitmapFactory.decodeByteArray(img, 0, img.size)
            imagen.setImageBitmap(imgBmp)

            text.text = t.nombre
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.agente_layout, viewGroup, false)
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