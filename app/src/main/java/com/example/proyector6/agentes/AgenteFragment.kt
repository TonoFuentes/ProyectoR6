package com.example.proyector6.agentes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
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
    private lateinit var items: ArrayList<ItemCard>


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tb = view.findViewById<Toolbar>(R.id.toolbarAgentes)
        tb.setTitle("Agentes")

        items = ArrayList()

        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.readableDatabase

        val rs = bd.rawQuery("SELECT * FROM Agentes", null)

        while (rs.moveToNext())
            items!!.add(ItemCard(rs.getBlob(2),rs.getString(0)))

        rs.close()
        bd.close()
        bdg.close()

//        val toolbar = view.findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        val recView = view.findViewById(R.id.recView) as RecyclerView
        recView.setHasFixedSize(true)
        val adaptador = ItemCardAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = GridLayoutManager(view.context,2)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}