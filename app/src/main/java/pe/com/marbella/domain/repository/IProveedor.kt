package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Proveedor

interface IProveedor {
    suspend fun getAllProveedor (): List<Proveedor>?
    suspend fun findByIdProveedor (codigo: Long): Proveedor?
    suspend fun saveProveedor (proveedor: Proveedor): Proveedor?
    suspend fun updateProveeor (codigo: Long, proveedor: Proveedor): Proveedor?
    suspend fun deleteByProveedor (codigo: Long)
}