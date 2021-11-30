package com.example.proyector6.mapas

import android.graphics.BitmapFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R

class MapasCardAdapter(var items: ArrayList<MapasCard>) : RecyclerView.Adapter<MapasCardAdapter.TarjViewHolder>() {
    lateinit var onClick : (View) -> Unit


    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView?
        val nombre: TextView

        init {
            imagen = itemView.findViewById(R.id.Imagenmapa)
            nombre = itemView.findViewById(R.id.Mapa)
        }

        fun bindCards(t: MapasCard) {
            val img = t.imagen
            val imgBmp = BitmapFactory.decodeByteArray(img, 0, img.size)
            imagen?.setImageBitmap(imgBmp)

            nombre.text = t.nombre
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.mapa_layout, viewGroup, false)
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