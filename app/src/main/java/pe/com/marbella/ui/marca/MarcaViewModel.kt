package pe.com.marbella.ui.marca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.providers.MarcaProvider
import javax.inject.Inject

@HiltViewModel
class MarcaViewModel @Inject constructor(val marcaProvider: MarcaProvider) : ViewModel() {

    private var _marcaList = MutableStateFlow<List<Marca>> (emptyList())
    val marcaList : StateFlow<List<Marca>> = _marcaList

    init {
        _marcaList.value = getAllMarca()
    }

    //obtener lista de marca
    private fun getAllMarca(): List<Marca> {
        return marcaProvider.getAllMarca()
    }


}