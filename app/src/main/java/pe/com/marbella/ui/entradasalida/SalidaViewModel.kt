package pe.com.marbella.ui.entradasalida

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.impl.DetalleMovimientoImpl
import pe.com.marbella.data.impl.ProductoImpl
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Salida
import javax.inject.Inject

@HiltViewModel
class SalidaViewModel @Inject constructor(
    private val detalleMovimientoImpl: DetalleMovimientoImpl,
    private val productoImpl: ProductoImpl
): ViewModel() {

    private var _productoList  = MutableStateFlow<List<Producto>>(emptyList())
    val productoList : StateFlow<List<Producto>> = _productoList

    private var _resultSalida = MutableStateFlow<ResultState<Salida>?>(null)
    val resultSalida : StateFlow<ResultState<Salida>?> = _resultSalida

    init {
        getAllProductos()
    }

    private fun getAllProductos() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = productoImpl.getAllProducto()
                if(response != null){
                    _productoList.value = response
                }else{
                    _productoList.value = emptyList()
                }
            }
        }
    }

    fun saveSalida(salida: Salida){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = detalleMovimientoImpl.saveSalida(salida)
                if(response != null){
                    _resultSalida.value = ResultState.Save(response)
                }else{
                    _resultSalida.value = ResultState.Error("Error en el servidor al guardar la salida")
                }
            }
        }
    }


}