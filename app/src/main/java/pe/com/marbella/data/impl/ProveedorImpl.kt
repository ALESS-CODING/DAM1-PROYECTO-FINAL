package pe.com.marbella.data.impl
import android.util.Log
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Proveedor
import pe.com.marbella.data.services.ProveedorApiService
import pe.com.marbella.domain.repository.ProveedorRepository
import pe.com.marbella.util.DialogUtil
import javax.inject.Inject


class ProveedorImpl @Inject constructor( private val apiService: ProveedorApiService) : ProveedorRepository {

    override suspend fun getAllProveedor(): List<Proveedor>? {
        runCatching { apiService.getAllProveedor() }
            .onSuccess { return  it }
            .onFailure {
                Log.i("Error", "Error en listar provvedor ${it.message}")
            }
        return null
    }

    override suspend fun findById(codio: Long): Proveedor {
        TODO("Not yet implemented")
    }
}