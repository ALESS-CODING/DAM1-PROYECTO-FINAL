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
import pe.com.marbella.data.impl.ProveedorImpl
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Proveedor
import javax.inject.Inject
@HiltViewModel
class EntradaViewModel @Inject constructor(
    private val proveedorImpl: ProveedorImpl,
    private val productoImpl: ProductoImpl,
    private val movimientoImpl: DetalleMovimientoImpl,
): ViewModel () {

    //producto lista
    private var _productoList = MutableStateFlow<List<Producto>>(emptyList())
    val productoList : StateFlow<List<Producto>> = _productoList

    //proveedor lista
    private var _proveedorList = MutableStateFlow<List<Proveedor>> (emptyList())
    val proveedorList: StateFlow<List<Proveedor>> = _proveedorList

    //uardar un entrada con sus detalles
    private var _resultEntrada = MutableStateFlow<ResultState<Entrada>?>(null)
    val resultEntrada : StateFlow<ResultState<Entrada>?> = _resultEntrada

    init {
        getAllProducto()
        getAllProveedor()
    }
    //listar todos los productos
    private fun getAllProveedor() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = proveedorImpl.getAllProveedor()
                if(!response.isNullOrEmpty()){
                    _proveedorList.value = response
                }else{
                    _proveedorList.value = emptyList()
                }
            }
        }
    }

    //listar todos los productos
    private fun getAllProducto() {
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

    fun saveEntrada (entrada: Entrada){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = movimientoImpl.saveEntrada(entrada)
                if(response != null){
                    _resultEntrada.value = ResultState.Save(response)
                }else{
                    _resultEntrada.value = ResultState.Error("Error en el servidor al guardar una salida")
                }
            }
        }
    }

}