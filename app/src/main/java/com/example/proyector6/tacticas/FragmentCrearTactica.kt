package com.example.proyector6.tacticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyector6.R
import com.example.proyector6.SQLiteGestor
import com.example.proyector6.databinding.FragmentCrearTacticaBinding
import com.example.proyector6.mapas.MapasCard
import com.example.proyector6.mapas.MapasCardAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
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

        val nombreAgentes = ArrayList<String>()
        val nombreMapas = ArrayList<String>()
        val snackbar = Snackbar.make(view,"Tactica Guardada Correctamente", LENGTH_SHORT)

        var bdg: SQLiteGestor? = null
        bdg = SQLiteGestor(view.context, "ProyectoR6.sqlite")
        val bd = bdg.writableDatabase
        val bdLeer = bdg.readableDatabase


        val rsAgentes = bdLeer.rawQuery("SELECT * FROM Agentes", null)
        val rsMapas = bdLeer.rawQuery("SELECT * FROM Mapas", null)

        while (rsAgentes.moveToNext())
            nombreAgentes.add(rsAgentes.getString(0))

        while (rsMapas.moveToNext())
            nombreMapas.add(rsMapas.getString(0))

        rsAgentes.close()
        rsMapas.close()

        val aptAgentes = ArrayAdapter<String> (view.context,android.R.layout.simple_dropdown_item_1line,nombreAgentes)
        val aptMapas = ArrayAdapter<String> (view.context,android.R.layout.simple_dropdown_item_1line,nombreMapas)

        nAgente.setAdapter(aptAgentes)
        nAgente.threshold = 1

        nMapa.setAdapter(aptMapas)
        nMapa.threshold = 1

        aceptar.setOnClickListener {
            val nombreTactica = nTactica.text
            val agente = nAgente.text
            val mapa = nMapa.text
            val descripcion = Descripcion.text

            bd.execSQL("INSERT INTO Tacticas VALUES('$nombreTactica','$agente','$mapa','$descripcion')")

            bd.close()
            bdg.close()

            snackbar.show()

            findNavController().navigate(R.id.action_fragmentCrearTactica_to_nav_tacticas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}