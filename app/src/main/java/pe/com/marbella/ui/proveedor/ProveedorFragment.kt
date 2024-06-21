package pe.com.marbella.ui.proveedor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.com.marbella.databinding.FragmentProveedorBinding
import pe.com.marbella.ui.marca.RegistroMarca

class ProveedorFragment : Fragment() {

    private var _binding: FragmentProveedorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProveedorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrarProveedor.setOnClickListener {
            val intent = Intent(activity, RegistroProveedor::class.java)
            intent.putExtra("IS_EDIT_MODE", false)
            startActivity(intent)
        }

        binding.btnActualizarProveedor.setOnClickListener {
            val idProveedor:Long = obtenerIdProveedorSeleccionado()
            val intent = Intent(activity, RegistroProveedor::class.java)
            intent.putExtra("IS_EDIT_MODE", true)
            intent.putExtra("ID_PROVEEDOR", idProveedor)
            startActivity(intent)
        }

        return root
    }

    private fun obtenerIdProveedorSeleccionado(): Long {
        return 123L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}