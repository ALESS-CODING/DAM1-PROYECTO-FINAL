package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Categoria

interface ICategoria {
    suspend fun getAllCategoria (): List<Categoria>?
    suspend fun findByIdCategoria (codigo: Long): Categoria?
}