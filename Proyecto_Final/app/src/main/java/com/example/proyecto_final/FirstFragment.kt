package com.example.proyecto_final

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.proyecto_final.databinding.FragmentFirstBinding

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var url = "jdbc:mysql://localhost:8080/"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title = "Registro"
        binding.btnLogInFirst.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
            this.onDestroyView()



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun conexionBBDD(){
        var con: Connection? = null
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            con = DriverManager.getConnection(url, "root", "root")
            println("Conectando a BBDD")
            con.close()
        }catch (e : SQLException){
            println("1")
            println(e.errorCode)
            println("2")
            println(e.message)
            println("3")
            println(e.sqlState)
        }
    }
}