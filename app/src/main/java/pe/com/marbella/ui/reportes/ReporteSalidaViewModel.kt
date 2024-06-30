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
import pe.com.marbella.data.model.Salida
import javax.inject.Inject
@HiltViewModel
class ReporteSalidaViewModel @Inject constructor(private val reporteImpl: ReporteImpl): ViewModel () {

    private var _reporteSalida = MutableStateFlow<List<Salida>> (emptyList())
    val reporteSalida: StateFlow<List<Salida>> = _reporteSalida
    init {
        getAllReporte()
    }

    private fun getAllReporte() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = reporteImpl.reporteSalidaProucto()
                if(response != null){
                    _reporteSalida.value = response
                }else{
                    _reporteSalida.value = emptyList()
                }
            }
        }
    }

}