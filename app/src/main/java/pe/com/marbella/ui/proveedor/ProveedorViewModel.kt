package pe.com.marbella.ui.proveedor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.impl.ProveedorImpl
import pe.com.marbella.data.model.Proveedor
import javax.inject.Inject

@HiltViewModel
class ProveedorViewModel  @Inject constructor( private val proveedorImpl: ProveedorImpl) : ViewModel() {

    private var _proveedorList = MutableStateFlow<List<Proveedor>>(emptyList())
    val proveedorList: StateFlow<List<Proveedor>>  = _proveedorList

    init {
        getAllProveedor()
    }
    fun getAllProveedor() {
        viewModelScope.launch { Dispatchers.IO
            val responde =  proveedorImpl.getAllProveedor()
            if(responde != null){
                _proveedorList.value = responde
            }
        }
    }

    //funion eliminar un provvedor
    fun deleteByIdProveedor (codigo: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                proveedorImpl.deleteByProveedor(codigo)
                getAllProveedor()
            }
        }
    }
}