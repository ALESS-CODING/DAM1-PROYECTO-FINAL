package pe.com.marbella.ui.producto

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pe.com.marbella.adaptadores.producto.ProductoAdapter
import pe.com.marbella.databinding.FragmentProductoBinding
@AndroidEntryPoint
class ProductoFragment : Fragment() {

    private val productoViewModel by viewModels<ProductoViewModel>()
    private var _binding: FragmentProductoBinding? = null
    private val binding get() = _binding!!

    private lateinit var productoAdapter: ProductoAdapter

    //una vez que  la vista ah sido reado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initProductoAdapter()
        initUIState()

    }

    //inicializar adaptaer
    private fun initProductoAdapter() {
        productoAdapter = ProductoAdapter()
        binding.lvProducto.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productoAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productoViewModel.produtsList.collect {
                    productoAdapter.updateProductsList(it)
                }
            }
        }
    }

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