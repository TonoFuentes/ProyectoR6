package com.example.proyector6.tacticas

import android.graphics.BitmapFactory
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import org.w3c.dom.Text

class TacticasCardAdapter(var items: ArrayList<TacticasCard>) : RecyclerView.Adapter<TacticasCardAdapter.TarjViewHolder>() {
//    lateinit var onClick : (View) -> Unit
    //lateinit var onLongClick : (View) -> Unit


    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {

        val nombreTactica: TextView
        val nombreAgente: TextView
        val imgAgente: ImageView
        val nombreMapa: TextView
        val imgMapa: ImageView
        val descripcion: TextView

        init {
            nombreTactica = itemView.findViewById(R.id.nombreTactica)
            nombreAgente = itemView.findViewById(R.id.nombreAgente)
            imgAgente = itemView.findViewById(R.id.imgAgente)
            nombreMapa = itemView.findViewById(R.id.nombreMapa)
            imgMapa = itemView.findViewById(R.id.imgMapa)
            descripcion = itemView.findViewById(R.id.descTactica)
            itemView.setOnCreateContextMenuListener(this)
        }

        fun bindCards(t: TacticasCard) {
            nombreTactica.text = t.nTactica
            nombreMapa.text = t.nMapa
            nombreAgente.text = t.nAgente
            descripcion.text = t.descripcion

            val agente = t.imgAgente
            val imgBmpAgente = BitmapFactory.decodeByteArray(agente, 0, agente.size)
            imgAgente.setImageBitmap(imgBmpAgente)

            val mapa = t.imgMapa
            val imgBmpMapa = BitmapFactory.decodeByteArray(mapa, 0, mapa.size)
            imgMapa.setImageBitmap(imgBmpMapa)

            //setOnClickListener { onLongClick(itemView) }
        }

        override fun onCreateContextMenu(contextMenu: ContextMenu, view: View, contextMenuInfo: ContextMenu.ContextMenuInfo?) {
            contextMenu.add(0, 0, adapterPosition, "Editar")     //groupId, itemId, order, title
            contextMenu.add(0, 1, adapterPosition, "Eliminar")
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.listar_layout, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val itemCard = items[pos]
        viewHolder.bindCards(itemCard)

//        viewHolder.itemView.setOnLongClickListener {
//            when (item.itemId) {
//                (R.id.action_copiar) -> {
//                    items.add(viewHolder.adapterPosition, TacticasCard(itemCard.imagen,itemCard.titulo))
//                    notifyItemInserted(viewHolder.adapterPosition)}
//                (R.id.action_eliminar) -> {
//                    items.removeAt(viewHolder.adapterPosition)
//                    notifyItemRemoved(viewHolder.adapterPosition)
//                }
//            }
//            return true
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}