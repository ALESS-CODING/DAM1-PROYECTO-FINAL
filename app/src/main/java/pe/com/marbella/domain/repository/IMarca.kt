package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Marca

interface IMarca {

    suspend fun getAllMarca (): List<Marca>?
    suspend fun findByIdMarca (codigo: Long): Marca?
    suspend fun saveMarca (marca: Marca): Marca ?
    suspend fun deleteMarca(codigo: Long)
    suspend fun updateMarca (codigo: Long, marca: Marca): Marca?
}