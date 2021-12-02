package com.example.proyector6.tacticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import com.example.proyector6.SQLiteGestor
import com.example.proyector6.databinding.FragmentTacticasBinding
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.fragment_tacticas.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TacticasFragment : Fragment() {

    private var _binding: FragmentTacticasBinding? = null
    private lateinit var items: ArrayList<TacticasCard>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTacticasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        items = ArrayList()

        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.readableDatabase

        val rs = bd.rawQuery("SELECT * FROM Tacticas", null)

        while (rs.moveToNext()) {
            val rsAgente = bd.rawQuery("SELECT * FROM Agentes WHERE Nombre like '${rs.getString(1)}'", null)
            val rsMapa = bd.rawQuery("SELECT * FROM Mapas WHERE Nombre like '${rs.getString(2)}'", null)

            rsAgente.moveToNext()
            rsMapa.moveToNext()

            items!!.add(TacticasCard(rs.getString(0),rs.getString(1),rsAgente.getBlob(2),rs.getString(2),rsMapa.getBlob(2),rs.getString(3)))

            rsAgente.close()
            rsMapa.close()
        }

        rs.close()
        bd.close()
        bdg.close()

        val recView = view.findViewById(R.id.recViewListarTacticas) as RecyclerView
        recView.setHasFixedSize(true)
        val adaptador = TacticasCardAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)


        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_tacticas_to_fragmentCrearTactica)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}