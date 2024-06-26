package pe.com.marbella.ui.marca

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.MarcaState
import pe.com.marbella.data.services.MarcaApiService
import javax.inject.Inject

@HiltViewModel
class RegistroMarcaViewModel @Inject constructor(private val marcaApiService: MarcaApiService): ViewModel (){

    private var _stateMarca = MutableStateFlow<MarcaState> (MarcaState.Loading)
    val stateMarca : StateFlow<MarcaState> = _stateMarca

    fun findByIdMarca(idMarca: Long) {

        viewModelScope.launch { Dispatchers.IO
            _stateMarca.value = MarcaState.Loading
             val marca = marcaApiService.findById(idMarca)
            if(marca != null){
                _stateMarca.value = MarcaState.Success(marca)
            }else{
                _stateMarca.value = MarcaState.Error("Ocurrio un error")
            }
        }
    }

}