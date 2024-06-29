package pe.com.marbella.ui.proveedor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.impl.ProveedorImpl
import pe.com.marbella.data.model.Proveedor
import javax.inject.Inject

@HiltViewModel
class RegistroProveedorViewModel @Inject constructor(private val proveedorImpl: ProveedorImpl): ViewModel () {

    private var _stateResult = MutableStateFlow<ResultState<Proveedor>?> (null)
    val stateResult : StateFlow<ResultState<Proveedor>?> = _stateResult

    fun findByIdProveedor (codigo: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = proveedorImpl.findByIdProveedor(codigo)
                if(response != null){
                    _stateResult.value = ResultState.FindById(response)
                }else {
                    _stateResult.value = ResultState.Error("Error en el servidor al buscar un proveedor con id: $codigo")
                }
            }
        }
    }

    // funcion atualizar proveedor
    fun updateProveedor (codigo: Long, proveedor: Proveedor){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = proveedorImpl.updateProveeor(codigo, proveedor)
                if(response != null){
                    _stateResult.value = ResultState.Update(response)
                }else{
                    _stateResult.value = ResultState.Error("Error en el servidor al atualizar un proveedor con id: $codigo")
                }
            }
        }
    }
    //funcion eliminar un proveedor
    fun deleteByIdProveedor (codigo: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                proveedorImpl.deleteByProveedor(codigo)
                _stateResult.value = null
            }
        }
    }

    //funion guardar un proveedor
    fun saveProveedor (proveedor: Proveedor){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = proveedorImpl.saveProveedor(proveedor)
                if(response != null){
                    _stateResult.value = ResultState.Save(response)
                }else{
                    _stateResult.value = ResultState.Error("Error en el servidor al guardar el proveedor")
                }
            }
        }
    }
}