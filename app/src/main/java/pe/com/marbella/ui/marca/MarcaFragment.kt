package pe.com.marbella.ui.marca

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.com.marbella.databinding.FragmentMarcaBinding

class MarcaFragment : Fragment() {

    private var _binding: FragmentMarcaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarcaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrarMarca.setOnClickListener {
            val intent = Intent(activity, RegistroMarca::class.java)
            intent.putExtra("IS_EDIT_MODE", false)
            startActivity(intent)
        }

        binding.btnActualizarMarca.setOnClickListener {
            val idMarca:Long = obtenerIdMarcaSeleccionada()
            val intent = Intent(activity, RegistroMarca::class.java)
            intent.putExtra("IS_EDIT_MODE", true)
            intent.putExtra("ID_MARCA", idMarca)
            startActivity(intent)
        }

        return root
    }

    private fun obtenerIdMarcaSeleccionada(): Long {
        return 123L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}