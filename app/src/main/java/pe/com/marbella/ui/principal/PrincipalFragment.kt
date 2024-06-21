package pe.com.marbella.ui.principal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.com.marbella.databinding.FragmentPrincipalBinding
import pe.com.marbella.ui.entradasalida.RegistrarEntrada
import pe.com.marbella.ui.entradasalida.RegistrarSalida
import pe.com.marbella.ui.marca.RegistroMarca

class PrincipalFragment : Fragment() {
    private var _binding: FragmentPrincipalBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrarEntrada.setOnClickListener {
            val intent = Intent(activity, RegistrarEntrada::class.java)
            startActivity(intent)
        }

        binding.btnRegistrarSalida.setOnClickListener {
            val intent = Intent(activity, RegistrarSalida::class.java)
            startActivity(intent)
        }

        return root
    }
}