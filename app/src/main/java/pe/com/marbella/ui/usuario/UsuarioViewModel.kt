package pe.com.marbella.ui.usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.impl.UsuarioImpl
import pe.com.marbella.data.model.Usuario
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(private val usuarioImpl: UsuarioImpl): ViewModel() {

    private var _usuarioList = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarioList : StateFlow<List<Usuario>> = _usuarioList

    init {
        getAllUsuario()
    }
    fun getAllUsuario() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = usuarioImpl.findAllUsuario()
                if(response != null){
                    _usuarioList.value = response
                }else{
                    _usuarioList.value = emptyList()
                }
            }
        }
    }
    fun deleteByIdUsuario (codigo: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                usuarioImpl.deleteUsuario(codigo)
                getAllUsuario()
            }
        }
    }
}