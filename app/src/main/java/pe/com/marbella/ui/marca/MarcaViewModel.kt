package pe.com.marbella.ui.marca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarcaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No hay marcas"
    }
    val text: LiveData<String> = _text
}