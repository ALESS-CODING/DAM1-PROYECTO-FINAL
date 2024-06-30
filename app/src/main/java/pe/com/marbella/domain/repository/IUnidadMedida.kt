package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.UnidadMedida

interface IUnidadMedida {
    suspend fun getAllUnidadMedida (): List<UnidadMedida> ?
    suspend fun findById (codigo: Long): UnidadMedida?
}