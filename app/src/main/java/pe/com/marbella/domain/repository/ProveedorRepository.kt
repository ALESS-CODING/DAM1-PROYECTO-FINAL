package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Marca
import pe.com.marbella.data.model.Proveedor

interface ProveedorRepository {
    suspend fun getAllProveedor (): List<Proveedor>?
    suspend fun findById (codio: Long): Proveedor
}