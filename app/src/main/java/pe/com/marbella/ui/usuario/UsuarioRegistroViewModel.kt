package pe.com.marbella.ui.usuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.impl.UsuarioImpl
import pe.com.marbella.data.model.Usuario
import javax.inject.Inject

@HiltViewModel
class UsuarioRegistroViewModel @Inject constructor(private val usuarioImpl: UsuarioImpl): ViewModel (){

    private var _stateResult = MutableStateFlow<ResultState<Usuario>?> (null)
    val stateResult: StateFlow<ResultState<Usuario>?> = _stateResult

    //funion busar Usuario
    fun findByIdUsuario (codigo: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = usuarioImpl.findByIdUsuario(codigo)

                if(response != null){
                    _stateResult.value = ResultState.FindById(response)
                }else{
                    _stateResult.value = ResultState.Error("Error en el servidor al buscar usuario id : $codigo")
                }
            }
        }
    }

    //Funcion guardar usuario
    fun saveUsuario (usuario: Usuario){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = usuarioImpl.saveUsuario(usuario)
                if(response != null){
                    _stateResult.value = ResultState.Save(response)
                }else{
                    _stateResult.value = ResultState.Error("Error en el servidor al guardar el usuario")
                }
            }
        }
    }

    //Funcion guardar usuario
    fun updateUsuario (codigo: Long, usuario: Usuario){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = usuarioImpl.updateUsuario(codigo, usuario)
                if(response != null){
                    _stateResult.value = ResultState.Update(response)
                }else{
                    _stateResult.value = ResultState.Error("Error en el servidor al atualizar el usuario")
                }
            }
        }
    }

}