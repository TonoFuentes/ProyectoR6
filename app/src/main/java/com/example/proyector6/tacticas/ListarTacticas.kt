package com.example.proyector6.tacticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import com.example.proyector6.SQLiteGestor
import com.example.proyector6.databinding.FragmentListarTacticasBinding
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.fragment_crear_tactica.*

/**
 * A simple [Fragment] subclass.
 * Use the [ListarTacticas.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListarTacticas : Fragment() {

    private var _binding: FragmentListarTacticasBinding? = null
    private lateinit var items: ArrayList<TacticasCard>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListarTacticasBinding.inflate(inflater, container, false)
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

        val recView = view.findViewById(R.id.RVListarTacticas) as RecyclerView
        recView.setHasFixedSize(true)
        val adaptador = TacticasCardAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}