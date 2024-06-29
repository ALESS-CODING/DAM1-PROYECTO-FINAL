package pe.com.marbella.ui.proveedor

import android.content.Intent
import android.os.Bundle
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
import pe.com.marbella.adaptadores.proveedor.ProveedorAdapter
import pe.com.marbella.databinding.FragmentProveedorBinding
@AndroidEntryPoint
class ProveedorFragment : Fragment() {

    private val proveedorViewModel by viewModels<ProveedorViewModel>()

    private var _binding: FragmentProveedorBinding? = null
    private val binding get() = _binding!!

    private lateinit var proveedorAdapter : ProveedorAdapter
    private var ID_PROVEEDOR: Long = -1L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentProveedorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Enabilirar los botones
        binding.btnEliminarProveedor.isEnabled = false
        binding.btnActualizarProveedor.isEnabled = false

        binding.btnRegistrarProveedor.setOnClickListener {
            iniciarAgregarProvvedor()
            ID_PROVEEDOR = -1L
        }
        //button actualizar
        binding.btnActualizarProveedor.setOnClickListener {
            if(ID_PROVEEDOR == -1L){
                Toast.makeText(context, "Seleccione un proveedor porfavor para actualizar", Toast.LENGTH_LONG ).show()
                return@setOnClickListener
            }
            //iniciar activity Actualizar proveedor pasando el codigo
            iniciarActualizarProveedor(ID_PROVEEDOR)
            ID_PROVEEDOR = -1L
        }

        binding.btnEliminarProveedor.setOnClickListener {
            if(ID_PROVEEDOR != -1L){
                proveedorViewModel.deleteByIdProveedor(ID_PROVEEDOR)
                return@setOnClickListener
            }
            Toast.makeText(context, "Seleccione un proveedor para eliminar", Toast.LENGTH_LONG ).show()
        }

        return root
    }

    // atualizar listado cada vez ingresar a fragment
    override fun onResume() {
        super.onResume()
        proveedorViewModel.getAllProveedor()
    }

    private fun initUI() {
        initProveedorAdapter()
        iniUIState()
    }

    //iniializar proveeor aapter
    private fun initProveedorAdapter() {
        proveedorAdapter = ProveedorAdapter(onItemSelected = {
            //obtener el id de proveedor y habilitar los botones
            val btnAct = binding.btnActualizarProveedor
            val btnElim = binding.btnEliminarProveedor

            if(!btnElim.isEnabled || !btnAct.isEnabled){
                btnElim.isEnabled = true
                btnAct.isEnabled = true
            }
            ID_PROVEEDOR = it
        })
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
    //funion para abrir interfaz agregar
    private fun iniciarAgregarProvvedor() {
        findNavController().navigate(
            ProveedorFragmentDirections.actionNavProveedoresToRegistroProveedor(-1L, false)
        )
        enableButtons()
    }
    //funcion abrir interfaz atualiazr
    private fun iniciarActualizarProveedor(codigo: Long) {
        findNavController().navigate(
            ProveedorFragmentDirections.actionNavProveedoresToRegistroProveedor(codigo, true)
        )
        enableButtons()
    }

    //Enabilitar los botones
    private fun enableButtons (){
        binding.btnEliminarProveedor.isEnabled = false
        binding.btnActualizarProveedor.isEnabled = false
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}