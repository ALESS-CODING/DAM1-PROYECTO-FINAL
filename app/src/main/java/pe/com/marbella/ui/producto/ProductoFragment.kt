package pe.com.marbella.ui.producto

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.com.marbella.databinding.FragmentProductoBinding
import pe.com.marbella.ui.marca.RegistroMarca

class ProductoFragment : Fragment() {

    private var _binding: FragmentProductoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrarProducto.setOnClickListener {
            val intent = Intent(activity, RegistroProducto::class.java)
            intent.putExtra("IS_EDIT_MODE", false)
            startActivity(intent)
        }

        binding.btnActualizarProducto.setOnClickListener {
            val idProducto:Long = obtenerIdProductoSeleccionado()
            val intent = Intent(activity, RegistroProducto::class.java)
            intent.putExtra("IS_EDIT_MODE", true)
            intent.putExtra("ID_PRODUCTO", idProducto)
            startActivity(intent)
        }

        return root
    }

    private fun obtenerIdProductoSeleccionado(): Long {
        return 123L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}