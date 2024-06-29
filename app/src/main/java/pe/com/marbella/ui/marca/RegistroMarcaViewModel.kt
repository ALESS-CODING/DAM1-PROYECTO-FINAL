package pe.com.marbella.ui.marca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.impl.MarcaImpl
import pe.com.marbella.data.model.Marca
import javax.inject.Inject

@HiltViewModel
class RegistroMarcaViewModel @Inject constructor(private val marcaImpl: MarcaImpl): ViewModel (){


    private var _resultState = MutableStateFlow<ResultState<Marca>?> (null)
    val resultState: StateFlow<ResultState<Marca>?> = _resultState

    fun findByIdMarca(idMarca: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                //_resultState.value = ResultState.Loading(null)
                val response = marcaImpl.findByIdMarca(idMarca)
                if(response != null){
                    _resultState.value = ResultState.FindById(response)
                }else{
                    _resultState.value = ResultState.Error("Error en el servidor al buscar marca : $idMarca")
                }
            }

        }
    }
    //funcion para guardar una marca
    fun saveMarca (marca: Marca){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = marcaImpl.saveMarca(marca)
                if(response != null){
                    _resultState.value = ResultState.Save(response)
                }else{
                    _resultState.value = ResultState.Error("Error en el servidor al guardar marca ")
                }
            }
        }
    }
    //funcion para actualizar una marca
    fun updateMarca (codigo: Long, marca: Marca){
        viewModelScope.launch {
           withContext(Dispatchers.IO){
               val response =  marcaImpl.updateMarca(codigo, marca)
               if(response != null){
                   _resultState.value = ResultState.Update(response)
               }else{
                   _resultState.value = ResultState.Error("Error en el servidor al guardar marca ")
               }
           }
        }
    }

}