package com.example.proyector6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MapasFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var items: ArrayList<ItemCard>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        items = ArrayList()

        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.readableDatabase

        val rs = bd.rawQuery("SELECT * FROM Mapas", null)

        while (rs.moveToNext())
            items!!.add(ItemCard(rs.getBlob(2),rs.getString(0)))

        rs.close()
        bd.close()
        bdg.close()

//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        val recView = view.findViewById(R.id.recView) as RecyclerView
        recView.setHasFixedSize(true)
        val adaptador = ItemCardAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}