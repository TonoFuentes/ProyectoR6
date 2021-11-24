package com.example.proyector6

import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class ItemCardAdapter(var items: ArrayList<ItemCard>) : RecyclerView.Adapter<ItemCardAdapter.TarjViewHolder>() {
    lateinit var onClick : (View) -> Unit


    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView

        init {
            imagen = itemView.findViewById(R.id.ivImage)
        }

        fun bindTarjeta(t: ItemCard, onClick: (View) -> Unit) = with(itemView) {
            imagen.setImageResource(t.imagen)
            setOnClickListener { onClick(itemView) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_layout, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val itemCard = items[pos]
        viewHolder.bindTarjeta(itemCard, onClick)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}