package com.example.proyector6.tacticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import com.example.proyector6.SQLiteGestor
import com.example.proyector6.databinding.FragmentCrearTacticaBinding
import com.example.proyector6.mapas.MapasCard
import com.example.proyector6.mapas.MapasCardAdapter
import kotlinx.android.synthetic.main.fragment_crear_tactica.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCrearTactica.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCrearTactica : Fragment() {

    private var _binding: FragmentCrearTacticaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCrearTacticaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tb = view.findViewById<Toolbar>(R.id.toolbarCrearTacticas)
        tb.setTitle("Crear Tactica")


        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.writableDatabase


        val nombreTactica = nTactica.text
        val agente = nAgente.text
        val mapa = nMapa.text
        val descripcion = Descripcion.text

        aceptar.setOnClickListener {
            val rs = bd.execSQL("INSERT INTO Tacticas VALUES('$nombreTactica','$agente','$mapa','$descripcion')")
        }


        bd.close()
        bdg.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}