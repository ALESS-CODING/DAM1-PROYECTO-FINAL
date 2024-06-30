package pe.com.marbella.ui.producto

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.marbella.data.ResultState
import pe.com.marbella.data.impl.CategoriaImpl
import pe.com.marbella.data.impl.MarcaImpl
import pe.com.marbella.data.impl.ProductoImpl
import pe.com.marbella.data.impl.UnidadMedidaImpl
import pe.com.marbella.data.model.Categoria
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.UnidadMedida
import javax.inject.Inject

@HiltViewModel
class ProductoRegistroViewModel @Inject constructor(
    private val productoImpl: ProductoImpl,
    private val marcaImpl: MarcaImpl,
    private val categoriaImpl: CategoriaImpl,
    private val unidadMedidaImpl: UnidadMedidaImpl
) : ViewModel() {

    private var _resultState = MutableStateFlow<ResultState<Producto>?> (null)
    val resultState: StateFlow<ResultState<Producto>?> = _resultState

    //marca
    private var _marcaList = MutableStateFlow<List<Marca>> (emptyList())
    val marcaList: StateFlow<List<Marca>> = _marcaList

    private var _categoriaList = MutableStateFlow<List<Categoria>> (emptyList())
    val categoriaList : StateFlow<List<Categoria>> = _categoriaList

    //unidad medida
    private var _unidadMedidaList = MutableStateFlow<List<UnidadMedida>>(emptyList())
    val unidadMedidaList : StateFlow<List<UnidadMedida>> = _unidadMedidaList

    //unidad medida
    private var _productoList = MutableStateFlow<List<Producto>>(emptyList())
    val productoList : StateFlow<List<Producto>> = _productoList

    init {
        getAllMarca()
        getAllProductos()
        getAllCateoria()
        getAllUnidadMedida()
    }

    fun findByIdProducto (codigo: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = productoImpl.findByIdProducto(codigo)
                if(response != null){
                    _resultState.value = ResultState.FindById(response)
                }else{
                    _resultState.value = ResultState.Error("Error en el servidor al buscar un producto id $codigo")
                }
            }
        }
    }

    //uardar producto
    fun saveProducto (producto: Producto){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = productoImpl.saveProducto(producto)
                if(response != null){
                    _resultState.value = ResultState.Save(response)
                }else{
                    _resultState.value = ResultState.Error("Error en el servidor al guardar un producto ")
                }
            }
        }
    }

    //atualizar producto
    fun updateProducto (codigo: Long, producto: Producto){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = productoImpl.updateProducto(codigo, producto)
                if(response != null){
                    _resultState.value = ResultState.Update(response)
                }else{
                    _resultState.value = ResultState.Error("Error en el servidor al atualizar un producto ")
                }
            }
        }
    }
    private fun getAllProductos (){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val respose = productoImpl.getAllProducto()
                if(respose != null){
                    _productoList.value = respose
                }else{
                    _productoList.value = emptyList()
                }
            }
        }
    }


    //obtner lista marca
    private fun getAllMarca (){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = marcaImpl.getAllMarca()
                if(response != null){
                    _marcaList.value = response
                }else{
                    _marcaList.value = emptyList()
                }
            }

        }

    }

    //obtener lista ateoria
    private fun getAllCateoria (){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = categoriaImpl.getAllCategoria()
                if(response != null){
                    _categoriaList.value = response
                }else{
                    _categoriaList.value = emptyList()
                }
            }
        }
    }

    //obteneer lista Unidad Medidad
    private fun getAllUnidadMedida (){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = unidadMedidaImpl.getAllUnidadMedida()
                if(response != null){
                    _unidadMedidaList.value = response
                }else{
                    _unidadMedidaList.value = emptyList()
                }
            }
        }
    }

}