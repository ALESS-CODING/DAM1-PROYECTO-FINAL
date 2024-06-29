package pe.com.marbella.ui.usuario

import android.content.Context
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
import pe.com.marbella.adaptadores.usuario.UsuarioAdapter
import pe.com.marbella.databinding.FragmentUsuarioBinding
import pe.com.marbella.util.ToastUtil

@AndroidEntryPoint
class UsuarioFragment : Fragment() {
    private val usuarioViewModel by viewModels<UsuarioViewModel>()
    private var _binding: FragmentUsuarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioAdapter: UsuarioAdapter
    private var ID_USUARIO: Long = -1L

    //objetos
    val toastUtil = ToastUtil()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentUsuarioBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initUI()
        //enabilitar los buttons
        enabledButtons()

        binding.btnRegistrarUsuario.setOnClickListener {
            iniciarAgregarUsuario()
        }

        binding.btnActualizarUsuario.setOnClickListener {
            if(ID_USUARIO == -1L){
                toastUtil.mensajeToast(requireContext(), "Por favor seleccione un usuario")
                return@setOnClickListener
            }
            iniciarActualizarUsuario ( ID_USUARIO)
            ID_USUARIO = -1L
        }

        //button eliminar usuario
        binding.btnEliminarUsuario.setOnClickListener {
            if(ID_USUARIO == -1L){
                toastUtil.mensajeToast(requireContext(), "Por favor seleccione un usuario")
                return@setOnClickListener
            }
            usuarioViewModel.deleteByIdUsuario(ID_USUARIO)
            enabledButtons()
            ID_USUARIO = -1L
        }
        return root
    }


    override fun onResume() {
        super.onResume()
        usuarioViewModel.getAllUsuario()
    }

    private fun initUI() {
        initUsuarioAdapter()
        initUIState()
    }

    private fun initUsuarioAdapter() {
        usuarioAdapter = UsuarioAdapter( onItemSelected = {
            //ativar los buttons
            val btnAct = binding.btnActualizarUsuario
            val btnElim = binding.btnEliminarUsuario

            if(!btnElim.isEnabled || !btnAct.isEnabled){
                btnElim.isEnabled = true
                btnAct.isEnabled = true
            }
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

    private fun iniciarActualizarUsuario(codigo: Long) {
        findNavController().navigate(
            UsuarioFragmentDirections.actionNavUsuariosToRegistroUsuario(codigo, true)
        )
        ID_USUARIO = -1L
        enabledButtons()
    }

    //navegar a interfaz Añadir usuario
    private fun iniciarAgregarUsuario() {
        findNavController().navigate(
            UsuarioFragmentDirections.actionNavUsuariosToRegistroUsuario(-1, false)
        )
        ID_USUARIO = -1L
        enabledButtons()
    }

    //enabilitar buttons
    private fun enabledButtons (){
        binding.btnEliminarUsuario.isEnabled = false
        binding.btnActualizarUsuario.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}