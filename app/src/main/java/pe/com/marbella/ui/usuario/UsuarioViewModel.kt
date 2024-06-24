package pe.com.marbella.ui.usuario

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.com.marbella.data.model.Usuario
import pe.com.marbella.data.providers.UsuarioProvider
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(val usuarioProvider: UsuarioProvider): ViewModel() {

    private var _usuarioList = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarioList : StateFlow<List<Usuario>> = _usuarioList

    init {
        _usuarioList.value = getAllUsers()
    }

    private fun getAllUsers(): List<Usuario> {
        return usuarioProvider.getAllUsuarios()
    }
}