package pe.com.marbella.ui.producto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
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
    private var ID_PRODUCTO: Long = -1L

    //una vez que  la vista ah sido reado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //desabilitar buttoons
        enableButtons()

        //abrir interfaz registrar producto
        binding.btnRegistrarProducto.setOnClickListener {
            navegarRegistrarInterfaz()
            ID_PRODUCTO = -1L
        }

        binding.btnActualizarProducto.setOnClickListener {
            if(ID_PRODUCTO == -1L){
                Toast.makeText(context, "Selccione un producto", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            navegarActualizarProductoInterfaz (ID_PRODUCTO)
            ID_PRODUCTO = -1L
        }

        binding.btnEliminarProducto.setOnClickListener {
            if(ID_PRODUCTO == -1L){
                Toast.makeText(context, "Selccione un producto", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            productoViewModel.deleteByIdProducto(ID_PRODUCTO)
            ID_PRODUCTO = -1L
        }

        return root
    }
    //se llama la funcion cada ves que se muestra el fragmento
    override fun onResume() {
        super.onResume()
        productoViewModel.getAllProducts()
    }


    //abrir interfaz actualizar producto
    private fun navegarActualizarProductoInterfaz(idProducto: Long) {
        findNavController().navigate(
            ProductoFragmentDirections.actionNavProductosToRegistroProducto(true, idProducto)
        )
        enableButtons()
    }

    // abrir interfaz agregar producto
    private fun navegarRegistrarInterfaz() {
        findNavController().navigate(
            ProductoFragmentDirections.actionNavProductosToRegistroProducto(false, -1L)
        )
        enableButtons()
    }


    private fun initUI() {
        initProductoAdapter()
        initUIState()

    }

    //inicializar adaptaer
    private fun initProductoAdapter() {
        //obtneer produto selecionnado
        productoAdapter = ProductoAdapter(onItemSelected = {
            val btnAct = binding.btnActualizarProducto
            val btnElim = binding.btnEliminarProducto

            if(!btnElim.isEnabled || !btnAct.isEnabled){
                btnElim.isEnabled = true
                btnAct.isEnabled = true
            }
            //actualizar id producto
            ID_PRODUCTO = it
        })

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

    // hinabilitar los botnoes
    private fun  enableButtons (){
        binding.btnActualizarProducto.isEnabled = false
        binding.btnEliminarProducto.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}