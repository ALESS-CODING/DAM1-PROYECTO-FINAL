package pe.com.marbella.ui.producto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.com.marbella.data.model.Categoria
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.UnidadMedida
import pe.com.marbella.data.providers.ProductoProvider
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class ProductoViewModel @Inject constructor( val productoProvider: ProductoProvider) : ViewModel() {

    private var _produtsList = MutableStateFlow<List<Producto>>(emptyList())
    val produtsList: StateFlow<List<Producto>> = _produtsList

    init {
        _produtsList.value = getAllProducts()
    }
    //obtener productos
    private fun getAllProducts() : List<Producto> {
        return productoProvider.getAllsProducts()
    }
}