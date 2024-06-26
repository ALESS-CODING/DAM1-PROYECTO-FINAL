package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.services.MarcaApiService
import pe.com.marbella.domain.repository.IMarca
import javax.inject.Inject

class MarcaImpl @Inject constructor(private val marcaApiService: MarcaApiService) : IMarca{
    override suspend fun getAllMarca(): List<Marca>? {
        runCatching { marcaApiService.getAllMarca() }
            .onSuccess { return it }
            .onFailure {
                Log.i("ERROR", "Ocurrio un error al listar Marca : ${it.message}")
            }
        return null
    }

    override suspend fun findByIdMarca(codigo: Long): Marca? {
        kotlin.runCatching { marcaApiService.findById(codigo) }
            .onSuccess { return it }
            .onFailure {  Log.i("ERROR", "Ocurrio un error al buscar Marca : ${it.message}") }
        return null
    }

    override suspend fun saveMarca(marca: Marca): Marca? {
        kotlin.runCatching { marcaApiService.saveMarca(marca) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "No se pudo guardar : ${it.message}") }
        return null
    }

    override suspend fun deleteMarca(codigo: Long) {
        kotlin.runCatching { marcaApiService.deleteMarca(codigo) }
    }

    override suspend fun updateMarca(codigo: Long, marca: Marca): Marca? {
        kotlin.runCatching { marcaApiService.updateMarca(codigo, marca) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "No se pudo guardar : ${it.message}") }
        return null
    }
}