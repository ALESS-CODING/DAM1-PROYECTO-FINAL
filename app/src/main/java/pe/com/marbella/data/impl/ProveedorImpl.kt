package pe.com.marbella.data.impl
import android.util.Log
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.data.services.ProveedorApiService
import pe.com.marbella.domain.repository.IProveedor
import javax.inject.Inject


class ProveedorImpl @Inject constructor( private val apiService: ProveedorApiService):IProveedor {
    override suspend fun getAllProveedor(): List<Proveedor>? {
        runCatching { apiService.getAllProveedor() }
            .onSuccess { return  it }
            .onFailure {
                Log.i("Error", "Error en listar provvedor ${it.message}")
            }
        return null
    }
    override suspend fun findByIdProveedor (codigo: Long): Proveedor? {
        runCatching { apiService.findById(codigo)}
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al buscar Prov : $it") }
        return null
    }

    override suspend fun saveProveedor(proveedor: Proveedor): Proveedor? {
        runCatching { apiService.save(proveedor) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al guardar proveedor : $it") }
        return null
    }

    override suspend fun updateProveeor(codigo: Long, proveedor: Proveedor): Proveedor? {
        runCatching { apiService.update(codigo, proveedor) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al atualizar proveedor : $it") }
        return null
    }

    override suspend fun deleteByProveedor(codigo: Long) {
        runCatching { apiService.deleById(codigo) }
            .onSuccess {  }
            .onFailure { Log.i("ERROR", "Error al eliminar proveedor : $it") }
    }
}