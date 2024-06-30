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
import pe.com.marbella.data.model.Producto
import javax.inject.Inject

@HiltViewModel
class ReporteStockViewModel @Inject constructor(private val reporteImpl: ReporteImpl) : ViewModel(){

    private var _stockProducto = MutableStateFlow<List<Producto>>(emptyList())
    val stockProducto: StateFlow<List<Producto>> = _stockProducto

    init {
        getAllProductoStockReporte()
    }

    private fun getAllProductoStockReporte() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = reporteImpl.reporteStockProucto()
                if(response != null){
                    _stockProducto.value = response
                }else{
                    _stockProducto.value = emptyList()
                }
            }
        }
    }
}