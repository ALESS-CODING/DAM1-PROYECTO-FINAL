package pe.com.marbella.ui.marca

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
import pe.com.marbella.adaptadores.marca.MarcaAdapter
import pe.com.marbella.databinding.FragmentMarcaBinding
import pe.com.marbella.util.ToastUtil
import javax.inject.Inject

@AndroidEntryPoint
class MarcaFragment  constructor() : Fragment() {


    private val marcaViewModel by viewModels<MarcaViewModel>()
    private var _binding: FragmentMarcaBinding? = null
    private val binding get() = _binding!!
    //Marca adapter
    private lateinit var marcaAdapter: MarcaAdapter
    private var ID_MARCA: Long = -1L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initMarcaAdapter()
        initUIState()
    }
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
            //obtener el id de marca seleccionanda
            ID_MARCA = it
        })

        binding.rvMarcaLista.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marcaAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarcaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegistrarMarca.setOnClickListener {
            /*val intent = Intent(activity, RegistroMarca::class.java)
            intent.putExtra("IS_EDIT_MODE", false)
            startActivity(intent)*/

            findNavController().navigate(
                MarcaFragmentDirections.actionNavMarcasToRegistroMarca(-1L, false)
            )
        }
        binding.btnEliminarMarca.setOnClickListener {
            if(ID_MARCA == -1L){
                Toast.makeText(context, "Por Favor seleccione una marca ", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            marcaViewModel.deleteByIdMarca(ID_MARCA)
        }

        /*
        binding.btnActualizarMarca.setOnClickListener {
            val idMarca:Long = obtenerIdMarcaSeleccionada()
            val intent = Intent(activity, RegistroMarca::class.java)
            intent.putExtra("IS_EDIT_MODE", true)
            intent.putExtra("ID_MARCA", idMarca)
            startActivity(intent)
        }*/

        binding.btnActualizarMarca.setOnClickListener{
            if(ID_MARCA == -1L){
                Toast.makeText(context, "Por favor seleccione una marca para ACTUALIZAR", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            startUpdateMarca()
        }

        return root
    }

    //abrir activity update Producto pasando id marca
    private fun startUpdateMarca() {
        findNavController().navigate(
            MarcaFragmentDirections.actionNavMarcasToRegistroMarca(ID_MARCA, true)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}