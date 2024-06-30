package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.Categoria
import pe.com.marbella.data.services.CategoriaApiService
import pe.com.marbella.domain.repository.ICategoria
import javax.inject.Inject

class CategoriaImpl @Inject constructor(private val categoriaApiService: CategoriaApiService): ICategoria{
    override suspend fun getAllCategoria(): List<Categoria>? {
        runCatching { categoriaApiService.getAllCategoria() }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al listar Cateoria") }
        return null
    }

    override suspend fun findByIdCategoria(codigo: Long): Categoria? {
        runCatching { categoriaApiService.findByIdCategoria(codigo) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al buscar Cateoria : id $codigo") }
        return null
    }


}