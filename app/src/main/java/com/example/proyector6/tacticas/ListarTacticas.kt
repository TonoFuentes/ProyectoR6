package com.example.proyector6.tacticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.example.proyector6.R
import com.example.proyector6.SQLiteGestor
import com.example.proyector6.databinding.FragmentCrearTacticaBinding
import com.example.proyector6.databinding.FragmentListarTacticasBinding
import kotlinx.android.synthetic.main.fragment_crear_tactica.*

/**
 * A simple [Fragment] subclass.
 * Use the [ListarTacticas.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListarTacticas : Fragment() {

    private var _binding: FragmentListarTacticasBinding? = null

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

        val tb = view.findViewById<Toolbar>(R.id.toolbarCrearTacticas)
        tb.setTitle("Listar Tacticas")


        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.readableDatabase

        val rs = bd.rawQuery("SELECT * FROM Mapas", null)





        rs.close()
        bd.close()
        bdg.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}