package pe.com.marbella.ui.proveedor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProveedorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No hay proveedores"
    }
    val text: LiveData<String> = _text
}