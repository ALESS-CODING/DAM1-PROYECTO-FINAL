package pe.com.marbella.ui.marca

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
import pe.com.marbella.adaptadores.marca.MarcaAdapter
import pe.com.marbella.databinding.FragmentMarcaBinding
import pe.com.marbella.util.ToastUtil

@AndroidEntryPoint
class MarcaFragment : Fragment() {

    private val marcaViewModel by viewModels<MarcaViewModel>()
    private var _binding: FragmentMarcaBinding? = null
    private val binding get() = _binding!!
    //Marca adapter
    private lateinit var marcaAdapter: MarcaAdapter
    private var ID_MARCA: Long = -1L

    //objetos
    private val toastUtil = ToastUtil()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarcaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initUI()

        //Enabilitar los botones
        enableButtons()

        //Abrir interfaz e Reistro
        binding.btnRegistrarMarca.setOnClickListener {
            findNavController().navigate(
                MarcaFragmentDirections.actionNavMarcasToRegistroMarca(-1L, false)
            )
            enableButtons()
            ID_MARCA = -1L
        }

        //eliminar una marca
        binding.btnEliminarMarca.setOnClickListener {
            if(ID_MARCA == -1L){
                toastUtil.mensajeToast(context, "Por Favor seleccione una marca ")
                return@setOnClickListener
            }
            marcaViewModel.deleteByIdMarca(ID_MARCA)
            ID_MARCA = -1L
        }

        //abrir interfaz para atualizar una mara
        binding.btnActualizarMarca.setOnClickListener{
            if(ID_MARCA == -1L){
                toastUtil.mensajeToast(context, "Por favor seleccione una marca para")
                return@setOnClickListener
            }
            navegacionActualizaMarca()
        }

        return root
    }

    //ejecuta la funcion cada vez que muestar el farmento
    override fun onResume() {
        super.onResume()
        marcaViewModel.getAllMarca()
    }

    private fun initUI() {
        initMarcaAdapter()
        initUIState()
    }
    //iniciar lista Marca y actualizar adaptadorMarca
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                marcaViewModel.marcaList.collect{
                    //recibo lista de marca y actualiza
                    marcaAdapter.actualizarListaMarca(it)
                }
            }
        }
    }


    //Inicuializar marca adapater
    private fun initMarcaAdapter() {
        marcaAdapter = MarcaAdapter( onItemSelected = {
            //obtener el id de marca seleccionanda y habilitar el boton atualizar
            val btnAct = binding.btnActualizarMarca
            val btnElim = binding.btnEliminarMarca

            if(!btnElim.isEnabled || !btnAct.isEnabled){
                btnElim.isEnabled = true
                btnAct.isEnabled = true
            }

            ID_MARCA = it
        })

        binding.rvMarcaLista.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marcaAdapter
        }
    }


    //abrir activity update Producto pasando id marca
    private fun navegacionActualizaMarca() {
        findNavController().navigate(
            MarcaFragmentDirections.actionNavMarcasToRegistroMarca(ID_MARCA, true)
        )
        ID_MARCA = -1L
        enableButtons()
    }
    //enahblitar buttons
    private fun enableButtons (){
        binding.btnActualizarMarca.isEnabled = false
        binding.btnEliminarMarca.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}