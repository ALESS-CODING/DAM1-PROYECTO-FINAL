package pe.com.marbella.ui.usuario

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
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.com.marbella.adaptadores.usuario.UsuarioAdapter
import pe.com.marbella.databinding.FragmentUsuarioBinding
import pe.com.marbella.ui.marca.RegistroMarca
@AndroidEntryPoint
class UsuarioFragment : Fragment() {
    private val usuarioViewModel by viewModels<UsuarioViewModel>()

    private var _binding: FragmentUsuarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioAdapter: UsuarioAdapter
    private var ID_USUARIO: Long = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUsuarioAdapter()
        initUIState()
    }

    private fun initUsuarioAdapter() {
        usuarioAdapter = UsuarioAdapter( onItemSelected = {
            //navegar a registro producto
            ID_USUARIO = it
        })
        binding.rvUsuario.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usuarioAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                usuarioViewModel.usuarioList.collect{
                    usuarioAdapter.actualizarUsuarioList(it)
                }
            }
        }
    }

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
            /*val idUsuario:Long = obtenerIdUsuarioSeleccionado()
            val intent = Intent(activity, RegistroUsuario::class.java)
            intent.putExtra("IS_EDIT_MODE", true)
            intent.putExtra("ID_USUARIO", idUsuario)
            startActivity(intent)*/
            if(ID_USUARIO == 0L){
                Toast.makeText(context, "Porfavor leccione un usuario", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            iniciarActualizarUsuario ()
        }

        return root
    }

    private fun iniciarActualizarUsuario() {
        findNavController().navigate(
            UsuarioFragmentDirections.actionNavUsuariosToRegistroUsuario(ID_USUARIO)
        )
    }

    private fun obtenerIdUsuarioSeleccionado(): Long {
        return 123L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}