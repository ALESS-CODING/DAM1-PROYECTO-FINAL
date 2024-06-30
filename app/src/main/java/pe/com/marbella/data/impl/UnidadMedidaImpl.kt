package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.UnidadMedida
import pe.com.marbella.data.services.UnidadMedidaApiService
import pe.com.marbella.domain.repository.IUnidadMedida
import javax.inject.Inject

class UnidadMedidaImpl @Inject constructor(private val unidadMedidaApiService: UnidadMedidaApiService): IUnidadMedida {
    override suspend fun getAllUnidadMedida(): List<UnidadMedida>? {
        runCatching { unidadMedidaApiService.getAll() }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR" ,"Error al listar unidad medida") }
        return null
    }

    override suspend fun findById(codigo: Long): UnidadMedida? {
        runCatching { unidadMedidaApiService.findById(codigo) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR" ,"Error al buscar unidad medida : id $codigo") }
        return null
    }

}