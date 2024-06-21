package pe.com.marbella.ui.producto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No hay productos"
    }
    val text: LiveData<String> = _text
}