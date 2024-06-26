package pe.com.marbella.ui.marca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.providers.MarcaProvider
import pe.com.marbella.data.services.MarcaApiService
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MarcaViewModel @Inject constructor(private val marcaApiService: MarcaApiService) : ViewModel() {

    private var _marcaList = MutableStateFlow<List<Marca>> (emptyList())
    val marcaList : StateFlow<List<Marca>> = _marcaList

    init {
        getAllMarca()
    }

    //obtener lista de marca
    private fun getAllMarca() {
        viewModelScope.launch {
            val response = marcaApiService.getAllMarca()
            if(response != null){
                _marcaList.value = marcaApiService.getAllMarca()
            }

        }
    }

    fun deleteByIdMarca(idMarca: Long) {
        viewModelScope.launch {
            marcaApiService.deleteMarca(idMarca)
            getAllMarca()
        }
    }


}