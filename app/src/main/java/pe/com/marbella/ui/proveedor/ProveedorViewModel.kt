package pe.com.marbella.ui.proveedor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.data.providers.ProveedorProvider
import javax.inject.Inject

@HiltViewModel
class ProveedorViewModel  @Inject constructor(val proveedorProvider: ProveedorProvider) : ViewModel() {

    private var _proveedorList = MutableStateFlow<List<Proveedor>>(emptyList())
    val proveedorList: StateFlow<List<Proveedor>>  = _proveedorList

    init {
        _proveedorList.value = getAllProveedor()
    }

    private fun getAllProveedor(): List<Proveedor> {
        return proveedorProvider.getAllProveedor()
    }
}