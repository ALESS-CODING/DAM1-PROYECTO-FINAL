package pe.com.marbella.ui.proveedor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.com.marbella.data.impl.ProveedorImpl
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.data.providers.ProveedorProvider
import javax.inject.Inject

@HiltViewModel
class ProveedorViewModel  @Inject constructor( private val proveedorImpl: ProveedorImpl) : ViewModel() {

    private var _proveedorList = MutableStateFlow<List<Proveedor>>(emptyList())
    val proveedorList: StateFlow<List<Proveedor>>  = _proveedorList

    init {
        getAllProveedor()
    }
    private fun getAllProveedor() {
        viewModelScope.launch {
            var responde =  proveedorImpl.getAllProveedor()
            if(responde != null){
                _proveedorList.value = responde!!
            }
        }
    }


}