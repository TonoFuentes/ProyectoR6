package com.example.proyector6.tacticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.proyector6.R
import com.example.proyector6.databinding.FragmentTacticasBinding
import kotlinx.android.synthetic.main.content_main.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TacticasFragment : Fragment() {

    private var _binding: FragmentTacticasBinding? = null

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


        binding.crear.setOnClickListener {
            findNavController().navigate(R.id.action_nav_tacticas_to_fragmentCrearTactica)
        }

        binding.listar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_tacticas_to_listarTacticas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}