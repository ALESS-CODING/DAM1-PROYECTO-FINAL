package pe.com.marbella.ui.producto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.impl.ProductoImpl
import pe.com.marbella.data.model.Producto
import javax.inject.Inject

@HiltViewModel
class ProductoViewModel @Inject constructor( private val productoImpl: ProductoImpl) : ViewModel() {

    private var _produtsList = MutableStateFlow<List<Producto>>(emptyList())
    val produtsList: StateFlow<List<Producto>> = _produtsList

    init {
        getAllProducts()
    }
    //obtener productos
    fun getAllProducts(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = productoImpl.getAllProducto()
                if(response != null){
                    _produtsList.value = response
                }
            }
        }
    }

    fun deleteByIdProducto (codigo: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                productoImpl.deleteByIdProducto(codigo)
                getAllProducts()
            }
        }
    }
}