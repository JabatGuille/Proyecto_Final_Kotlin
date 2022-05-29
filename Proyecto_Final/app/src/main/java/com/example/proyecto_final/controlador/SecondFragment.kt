package com.example.proyecto_final.controlador

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.proyecto_final.R
import com.example.proyecto_final.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_ajustes->{
                findNavController().navigate(R.id.action_SecondFragment_to_ajustes2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Opciones"
        binding.btnHacerPedidos.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_recyclerview_hacer_pedido)

        }
        binding.btnVerPedidos.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_recyclerview_ver_pedidos)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}