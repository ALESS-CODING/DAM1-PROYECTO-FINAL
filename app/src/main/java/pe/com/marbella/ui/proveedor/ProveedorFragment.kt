package pe.com.marbella.ui.proveedor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.com.marbella.adaptadores.proveedor.ProveedorAdapter
import pe.com.marbella.databinding.FragmentProveedorBinding
import pe.com.marbella.ui.marca.RegistroMarca
@AndroidEntryPoint
class ProveedorFragment : Fragment() {

    private val proveedorViewModel by viewModels<ProveedorViewModel>()

    private var _binding: FragmentProveedorBinding? = null
    private val binding get() = _binding!!

    private lateinit var proveedorAdapter : ProveedorAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initProveedorAdapter()
        iniUIState()
    }

    private fun initProveedorAdapter() {
        proveedorAdapter = ProveedorAdapter()
        binding.rvProveedor.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = proveedorAdapter
        }
    }

    private fun iniUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                proveedorViewModel.proveedorList.collect{
                    proveedorAdapter.actualizarProveedorList(it)
                }
            }
        }
    }

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