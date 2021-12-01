package com.example.proyector6.agentes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import com.example.proyector6.SQLiteGestor
import com.example.proyector6.databinding.FragmentAgentesBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AgenteFragment : Fragment() {

    private var _binding: FragmentAgentesBinding? = null
    private lateinit var itemsTodos: ArrayList<ItemCard>
    private lateinit var itemsAtacantes: ArrayList<ItemCard>
    private lateinit var itemsDefensores: ArrayList<ItemCard>


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAgentesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("Recycle")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instanciacion del arraylist de items
        itemsTodos = ArrayList()
        itemsAtacantes = ArrayList()
        itemsDefensores = ArrayList()


        //Variables de conexion con la base de datos
        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.readableDatabase //Sentencia para leer de la BDD


        //Sentencias para hacer una consulta mediante SQL
        val rs = bd.rawQuery("SELECT * FROM Agentes", null)


        //moveToNext es importante usarlo primero para poder ir pasando entre los diferentes datos de las filas
        while (rs.moveToNext())
            itemsTodos!!.add(ItemCard(rs.getBlob(2),rs.getString(0),rs.getString(1)))


        //Distribucion de los agentes
        for (agente in itemsTodos){
            if (agente.bando.equals("Atacante")) {
                itemsAtacantes.add(agente)
            } else {
                itemsDefensores.add(agente)
            }
        }


        //Sentencias para rellenar el recView con los datos de la BDD
        val recView = view.findViewById(R.id.recViewAgentes) as RecyclerView
        recView.setHasFixedSize(true)
        var adaptador:ItemCardAdapter
        adaptador = ItemCardAdapter(itemsTodos)

        recView.adapter = adaptador
        recView.layoutManager = GridLayoutManager(view.context,2)
        adaptador.onClick = {
            findNavController().navigate(R.id.action_nav_agentes_to_informacionAgente)
        }


        //Declaracion de una toolbar y sus menus
        val tb = view.findViewById<Toolbar>(R.id.toolbarAgentes)
        tb.setTitle("Agentes")
        //setSupportActionBar(toolbar)
        tb.inflateMenu(R.menu.menu_agentes)

        tb.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                (R.id.action_todos) -> {
                    adaptador = ItemCardAdapter(itemsTodos)
                    recView.adapter = adaptador
                    recView.layoutManager = GridLayoutManager(view.context,2)
                    adaptador.onClick = {
                        findNavController().navigate(R.id.action_nav_agentes_to_informacionAgente)
                    }
                }
                (R.id.action_atacantes) -> {
                    adaptador = ItemCardAdapter(itemsAtacantes)
                    recView.adapter = adaptador
                    recView.layoutManager = GridLayoutManager(view.context,2)
                    adaptador.onClick = {
                        findNavController().navigate(R.id.action_nav_agentes_to_informacionAgente)
                    }
                }
                (R.id.action_defensores) -> {
                    adaptador = ItemCardAdapter(itemsDefensores)
                    recView.adapter = adaptador
                    recView.layoutManager = GridLayoutManager(view.context,2)
                    adaptador.onClick = {
                        findNavController().navigate(R.id.action_nav_agentes_to_informacionAgente)
                    }
                }
            }
            true
        }


        //Cierre de la conexion con la BDD
        rs.close()
        bd.close()
        bdg.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}