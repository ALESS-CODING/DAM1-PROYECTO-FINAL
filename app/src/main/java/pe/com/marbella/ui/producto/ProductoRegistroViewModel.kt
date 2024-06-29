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
import pe.com.marbella.data.impl.MarcaImpl
import pe.com.marbella.data.impl.ProductoImpl
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Producto
import javax.inject.Inject

@HiltViewModel
class ProductoRegistroViewModel @Inject constructor(
    private val productoImpl: ProductoImpl,
    private val marcaImpl: MarcaImpl
) : ViewModel() {

    private var _resultState = MutableStateFlow<ResultState<Producto>?> (null)
    val resultState: StateFlow<ResultState<Producto>?> = _resultState

    private var _marcaList = MutableStateFlow<List<Marca>> (emptyList())
    val marcaList: StateFlow<List<Marca>> = _marcaList

    init {
        getAllMarca()
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
                //val response =
            }
        }
    }

    //obteneer lista Unidad Medidad
    private fun getAllUnidadMedida (){
        viewModelScope.launch {
            withContext(Dispatchers.IO){

            }
        }
    }

}