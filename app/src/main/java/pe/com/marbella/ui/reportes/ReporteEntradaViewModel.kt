package pe.com.marbella.ui.reportes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.impl.ReporteImpl
import pe.com.marbella.data.model.Entrada
import javax.inject.Inject
@HiltViewModel
class ReporteEntradaViewModel @Inject constructor(private val reporteImpl: ReporteImpl) : ViewModel(){

    private var _reporteEntrada = MutableStateFlow<List<Entrada>>(emptyList())
    val reporteEntrada: StateFlow<List<Entrada>> = _reporteEntrada

    init {
        getAllReporteEntrada()
    }

    private fun getAllReporteEntrada() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = reporteImpl.reporteEntradaProucto()
                if(response != null){
                    _reporteEntrada.value = response
                }else{
                    _reporteEntrada.value = emptyList()
                }
            }
        }
    }


}