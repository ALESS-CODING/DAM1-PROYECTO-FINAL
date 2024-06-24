package pe.com.marbella.ui.marca

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
import pe.com.marbella.adaptadores.marca.MarcaAdapter
import pe.com.marbella.databinding.FragmentMarcaBinding
@AndroidEntryPoint
class MarcaFragment : Fragment() {

    private val marcaViewModel by viewModels<MarcaViewModel>()
    private var _binding: FragmentMarcaBinding? = null
    private val binding get() = _binding!!
    //Marca adapter
    private lateinit var marcaAdapter: MarcaAdapter

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
        marcaAdapter = MarcaAdapter()
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