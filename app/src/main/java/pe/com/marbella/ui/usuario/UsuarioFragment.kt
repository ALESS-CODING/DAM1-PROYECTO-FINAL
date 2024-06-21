package pe.com.marbella.ui.usuario

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.com.marbella.databinding.FragmentUsuarioBinding
import pe.com.marbella.ui.marca.RegistroMarca

class UsuarioFragment : Fragment() {

    private var _binding: FragmentUsuarioBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsuarioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrarUsuario.setOnClickListener {
            val intent = Intent(activity, RegistroUsuario::class.java)
            intent.putExtra("IS_EDIT_MODE", false)
            startActivity(intent)
        }

        binding.btnActualizarUsuario.setOnClickListener {
            val idUsuario:Long = obtenerIdUsuarioSeleccionado()
            val intent = Intent(activity, RegistroUsuario::class.java)
            intent.putExtra("IS_EDIT_MODE", true)
            intent.putExtra("ID_USUARIO", idUsuario)
            startActivity(intent)
        }

        return root
    }

    private fun obtenerIdUsuarioSeleccionado(): Long {
        return 123L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}